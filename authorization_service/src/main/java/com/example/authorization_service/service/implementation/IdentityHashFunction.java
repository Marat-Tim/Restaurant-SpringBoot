package com.example.authorization_service.service.implementation;

import com.example.authorization_service.service.abstraction.HashFunction;
import org.springframework.stereotype.Service;

@Service
public class IdentityHashFunction implements HashFunction {
    @Override
    public String hash(String arg) {
        return arg;
    }
}
