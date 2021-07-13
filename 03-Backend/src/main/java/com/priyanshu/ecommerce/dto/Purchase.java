package com.priyanshu.ecommerce.dto;

import java.util.Set;

import com.priyanshu.ecommerce.entity.Address;
import com.priyanshu.ecommerce.entity.Customer;
import com.priyanshu.ecommerce.entity.Order;
import com.priyanshu.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {
	
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;
}
