package com.wangchangyang.config;

import java.util.concurrent.TimeUnit;

import feign.RetryableException;
import feign.Retryer;

public class FeignRetryerConfig implements Retryer {

	@Override
	public void continueOrPropagate(RetryableException e) {
		throw e;
	}

	@Override
	public Retryer clone() {
		return new Retryer.Default(50, TimeUnit.SECONDS.toMillis(2), 2);
	}
	
}
