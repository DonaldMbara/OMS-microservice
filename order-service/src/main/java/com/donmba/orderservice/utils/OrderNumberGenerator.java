package com.donmba.orderservice.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final AtomicInteger sequence = new AtomicInteger(0);


    public static synchronized String generateRandomStringWithDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());

        int sequenceNumber = sequence.getAndIncrement();

        StringBuilder sb = new StringBuilder(currentDate);
        sb.append(String.format("%04d", sequenceNumber));

        SecureRandom random = new SecureRandom();
        int LENGTH = 15;
        for (int i = 0; i < LENGTH - currentDate.length() - 4; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
