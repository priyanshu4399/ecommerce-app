package com.priyanshu.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
	//lombok @Data will generate constructor for only final fields or use @NonNull above the field
	private final String orderTrackingNumber;
}
