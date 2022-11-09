package com.feignclient.config.decoders;

import org.apache.http.HttpStatus;

import com.feignclient.exception.ShipnodeCustomException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ShipnodeErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder = new Default();
	
	@Override
	public Exception decode(String methodKey, Response response) {
		 
		try {
			if(response.status() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				System.out.println("Getting 5xx error while getting data ");
				throw new ShipnodeCustomException("Getting 5xx while calling ims");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}

}
