/**
 *
 * CheckoutController.java, Dec 3, 2019 thaopm
 * 
 */
package com.pmt.bookstore.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmt.bookstore.domain.CartItem;
import com.pmt.bookstore.domain.Order;
import com.pmt.bookstore.domain.ShippingAddress;
import com.pmt.bookstore.domain.ShoppingCart;
import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.domain.UserShipping;
import com.pmt.bookstore.service.CartItemService;
import com.pmt.bookstore.service.OrderService;
import com.pmt.bookstore.service.ShippingAddressService;
import com.pmt.bookstore.service.ShoppingCartService;
import com.pmt.bookstore.service.UserService;
import com.pmt.bookstore.service.UserShippingService;
import com.pmt.bookstore.utility.MailConstructor;

/**
 * @author thaopm
 *
 */
@Controller
public class CheckoutController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private CartItemService cartItemService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	private ShippingAddress shippingAddress = new ShippingAddress();
	
	@RequestMapping("/checkout")
	public String checkout(
			@RequestParam("id") Long cartId,
			@RequestParam(value="missingRequiredField", required=false) boolean missingRequiredField,
			Model model, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		
		if(cartId != user.getShoppingCart().getId()) {
			return "badRequestPage";
		}
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
		
		if(cartItemList.size() == 0) {
			model.addAttribute("emptyCart", true);
			return "forward:/shoppingCart/cart";
		}
		
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getInStockNumber() < cartItem.getQty()) {
				model.addAttribute("notEnoughStock", true);
				return "forward:/shoppingCart/cart";
			}
		}
		
		List<UserShipping> userShippingList = user.getUserShippingList();
		
		model.addAttribute("userShippingList", userShippingList);
		
		if(userShippingList.size() == 0) {
			model.addAttribute("emptyShippingList", true);
		} else {
			model.addAttribute("emptyShippingList", false);
		}
		
		ShoppingCart shoppingCart = user.getShoppingCart(); 
		
		for(UserShipping userShipping : userShippingList) {
			if(userShipping.isUserShippingDefault()) {
				shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			}
		}
		
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", user.getShoppingCart());
		
		model.addAttribute("/classActiveShipping", true);
		
		if(missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
		}
		
		return "checkout";
	}
	
	
	@RequestMapping("/setShippingAddress")
	public String setShippingAddress(
			@RequestParam("userShippingId") Long userShippingId,
			Principal principal, Model model
			) {
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.findById(userShippingId);
		
		if(userShipping.getUser().getId() != user.getId()) {
			return "badRequestPage";
		} else {
			shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			
			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			
			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", user.getShoppingCart());
			
			List<UserShipping> userShippingList = user.getUserShippingList();
			
			model.addAttribute("userShippingList", userShippingList);
			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("classActiveShipping", true);
			
		
			model.addAttribute("emptyShippingList", false);
			
			
			return "checkout";
		}
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkoutPost(
			@ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
			@ModelAttribute("shippingMethod") String shippingMethod,
			Principal principal,
			Model model
			) {
		ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("cartItemList", cartItemList);
		
		if(shippingAddress.getShippingAddressStreet().isEmpty() ||
				shippingAddress.getShippingAddressCity().isEmpty() ||
				shippingAddress.getShippingAddressState().isEmpty() ||
				shippingAddress.getShippingAddressName().isEmpty() ||
				shippingAddress.getShippingAddressZipcode().isEmpty()) {
			return "redirect:/checkout?id="+shoppingCart.getId()+"&missingRequiredField=true";
		}
		
		User user = userService.findByUsername(principal.getName());
		Order order = orderService.createOrder(shoppingCart, shippingAddress, shippingMethod, user);
		
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate;
		
		if(shippingMethod.equals("groundShipping")) {
			estimatedDeliveryDate = today.plusDays(5);
		}else {
			estimatedDeliveryDate = today.plusDays(3);
		}
		
		model.addAttribute("estimateDeliveryDate", estimatedDeliveryDate);
		
		return "orderSubmittedPage";
	}
}
