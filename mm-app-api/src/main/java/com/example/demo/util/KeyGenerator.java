package com.example.demo.util;

import java.util.Base64;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class KeyGenerator {
	public static void main(String[] args) {
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("Generated Base64 Key: " + base64Key);
	}
}

//MO5Qo0j4RhVNyGpbQ+JOBibW2+XSMD0vKQ8Nux02Oe3o43aJe0SFJTtzjoku/f1a0tY+Nx2HzOfBNigHfLLgFg==