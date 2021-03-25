/**
 *
 * OrderServiceImpl.java, Dec 6, 2019 thaopm
 * 
 */
package com.pmt.bookstore.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmt.bookstore.domain.Book;
import com.pmt.bookstore.domain.CartItem;
import com.pmt.bookstore.domain.Order;
import com.pmt.bookstore.domain.ShippingAddress;
import com.pmt.bookstore.domain.ShoppingCart;
import com.pmt.bookstore.domain.User;
import com.pmt.bookstore.repository.OrderRepository;
import com.pmt.bookstore.service.CartItemService;
import com.pmt.bookstore.service.OrderService;

/**
 * @author thaopm
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;

	/* synchronized: suppose you're checking out the order, you'll modify the number of stock of books 
	 in DB. Meanwhile, another customer checking out the same book -> thread is raising
	 other people can't modify until the lock is release	
	*/
	@Override
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
			String shippingMethod, User user) {
		Order order = new Order();
		order.setOrderStatus("created");
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		return order;
	}

	@Override
	public Order findOne(Long id) {
		return orderRepository.findOne(id);
	}

}
