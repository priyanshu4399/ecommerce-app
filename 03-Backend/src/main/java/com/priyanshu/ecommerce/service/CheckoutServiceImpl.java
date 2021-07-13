package com.priyanshu.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priyanshu.ecommerce.dao.CustomerRepository;
import com.priyanshu.ecommerce.dto.Purchase;
import com.priyanshu.ecommerce.dto.PurchaseResponse;
import com.priyanshu.ecommerce.entity.Customer;
import com.priyanshu.ecommerce.entity.Order;
import com.priyanshu.ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{

	private CustomerRepository customerRepository ;
	
	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		//retrieve the order info from dto
		Order order = purchase.getOrder();
		
		//generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		//populate order with orderItem
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		//populate order with billingAdddress and shipping Address
		order.setBilllingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		//populate customer with order
		Customer customer = purchase.getCustomer();
		
		//check if this is an existing customer
		String theEmail = customer.getEmail();
		
		Customer customerFromDB = customerRepository.findByEmail(theEmail);
		
		if(customerFromDB != null) {
			//we found them ... lets assign them accordingly
			customer = customerFromDB;
		}
		
		customer.add(order);
		
		//save to the database
		customerRepository.save(customer);
		
		//return a response
		return new PurchaseResponse(orderTrackingNumber);
	
	}

	private String generateOrderTrackingNumber() {
		
		//genearte a random UUID number (UUID version 4)
		return UUID.randomUUID().toString();
	}

}
