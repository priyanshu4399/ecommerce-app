package com.priyanshu.ecommerce.service;

import com.priyanshu.ecommerce.dto.Purchase;
import com.priyanshu.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
