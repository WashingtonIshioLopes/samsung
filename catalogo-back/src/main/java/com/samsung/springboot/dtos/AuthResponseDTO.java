package com.samsung.springboot.dtos;

public class AuthResponseDTO {

	private Long id_user;
	private String token;

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

	public AuthResponseDTO(String token, Long id_user) {
		super();
		this.id_user = id_user;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public Long getUser() {
		return id_user;
	}

}
