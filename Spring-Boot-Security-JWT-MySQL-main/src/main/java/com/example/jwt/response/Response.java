package com.example.jwt.response;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = -1L;
	private final Object response;

	public Response(Object response) {
		this.response = response;
	}

	public Object getToken() {
		return this.response;
	}
}
