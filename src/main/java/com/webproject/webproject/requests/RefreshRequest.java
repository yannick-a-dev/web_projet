package com.webproject.webproject.requests;

import lombok.Data;

@Data
public class RefreshRequest {

	private Long userId;
	private String refreshToken;
}
