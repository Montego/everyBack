package com.every.every.service.util;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class GeneratorOfUniqueAcceptCode {
    public static String generateAcceptCode(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
