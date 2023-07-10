package com.sahilsahudev.Blogging.services;

import com.sahilsahudev.Blogging.payloads.AuthenticationRequest;
import com.sahilsahudev.Blogging.payloads.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(AuthenticationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
