package com.donmba.apigateway.config.interceptor;

import lombok.Data;

@Data
public class BearerTokenWrapper {

    private String token;
}
