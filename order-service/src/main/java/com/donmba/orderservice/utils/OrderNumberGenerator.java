package com.donmba.orderservice.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.security.SecureRandom;


//BAD, creates duplicates. Fix this tomorrow
public class OrderNumberGenerator {
    private static final SecureRandom random = new SecureRandom();


    public static synchronized String generateRandomStringWithDate() {

        long randomPart = random.nextLong();

        UUID uuid = UUID.randomUUID();

        return uuid.toString() + "-" + randomPart;
    }
}
