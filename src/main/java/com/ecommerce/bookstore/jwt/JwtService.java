package com.ecommerce.bookstore.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
 * A sample JWT program to demonstrate create and validate a jwt token
 */
public class JwtService {

	private static final String SECRET_KEY = "kdIDNK73Jhu&jj#$gRekld09kdIDNK73JhukdIDNK73Jhu";

	
	public static void main(String[] args) throws Exception {

		JwtService service = new JwtService();  
		
		//create jwt
		CreateJwtRequest createJwtRequest = new CreateJwtRequest();
		createJwtRequest.setSubject("dmohanraj1804@gmail.com");
		CreateJwtResponse createJwtResponse = service.createJwt(createJwtRequest);
		
		
		//verify jwt
		VerifyJwtRequest verifyJwtRequest = new VerifyJwtRequest();
		verifyJwtRequest.setToken(createJwtResponse.getToken());
		
		
		
		VerifyJwtResponse verifyJwtResponse = service.verifyJwt(verifyJwtRequest);
		System.out.println(verifyJwtResponse.getPayload());
		
	}
	
	public CreateJwtResponse createJwt(CreateJwtRequest createJwtRequest) throws Exception {


		byte[] sharedSecret = new byte[32];
		sharedSecret = SECRET_KEY.getBytes();
		
		// Create HMAC signer
		JWSSigner signer = new MACSigner(sharedSecret);
	
		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
		    .subject(createJwtRequest.getSubject())
		    .issuer("https://cts.com")
		    .expirationTime(new Date(new Date().getTime() + 1200 * 1000))// 1200 seconds
		    .issueTime(new Date(new Date().getTime()))
		    .claim("firstName", "Mohanraj")
		    .claim("lastName", "Dhanasekar")
		    .build();
	
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
	
		// Apply the HMAC protection
		signedJWT.sign(signer);
	

		String jwt = signedJWT.serialize();
		
		System.out.println("jwt token = " + jwt);
		
		CreateJwtResponse createJwtResponse = new CreateJwtResponse();
		createJwtResponse.setToken(jwt);
		
		return createJwtResponse;
		
		
	

	}
	
	public VerifyJwtResponse verifyJwt(VerifyJwtRequest verifyJwtRequest) throws Exception {


		byte[] sharedSecret = new byte[32];
		sharedSecret = SECRET_KEY.getBytes();
		
		// On the consumer side, parse the JWS and verify its HMAC
		SignedJWT signedJWT = SignedJWT.parse(verifyJwtRequest.getToken());
	
		JWSVerifier verifier = new MACVerifier(sharedSecret);
		
		boolean verify = signedJWT.verify(verifier);
		
		System.out.println("verified = " + verify);
		
		VerifyJwtResponse verifyJwtResponse = new VerifyJwtResponse();
		verifyJwtResponse.setStatus(true);
		
		//payload
		Map<String, String> payloadMap = new HashMap<String, String>();
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        Map<String,Object> claims = jwtClaimsSet.getClaims();
        claims.forEach((key, value) -> {
            payloadMap.put(key, String.valueOf(value));
        });
        
		verifyJwtResponse.setPayload(payloadMap);
		return verifyJwtResponse;
	

	}



}
