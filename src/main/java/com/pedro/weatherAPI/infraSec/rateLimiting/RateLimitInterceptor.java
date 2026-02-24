package com.pedro.weatherAPI.infraSec.rateLimiting;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final Bucket bucket;

    public RateLimitInterceptor(){
        bucket = Bucket.builder().addLimit( Bandwidth.builder().capacity(10L).refillGreedy(2L, Duration.ofSeconds(20)).build()).build();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if(probe.isConsumed()){
            return true;
        }
        else {
            throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS, "- Token bucket is empty, please wait a moment.");

        }
    }
}
