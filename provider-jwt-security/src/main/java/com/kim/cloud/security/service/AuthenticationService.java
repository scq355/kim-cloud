package com.kim.cloud.security.service;

import com.kim.cloud.security.common.JwtAuthenticationResponse;
import com.kim.cloud.security.common.SignUpRequest;
import com.kim.cloud.security.common.SigninRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
