package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.payloads.auth.AuthenticationRequest;
import com.sahilsahudev.Blogging.payloads.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(AuthenticationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
