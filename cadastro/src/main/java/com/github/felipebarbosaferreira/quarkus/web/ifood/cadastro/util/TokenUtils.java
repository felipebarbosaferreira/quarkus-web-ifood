package com.github.felipebarbosaferreira.quarkus.web.ifood.cadastro.util;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

public class TokenUtils {
	
	public String generateToken() {
		JwtClaimsBuilder builder1 = Jwt.claims("/JWTProprietarioClaims.json");
		return builder1.sign("privateKey.pem");
	}
}
