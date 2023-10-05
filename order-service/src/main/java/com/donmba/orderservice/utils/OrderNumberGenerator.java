package com.donmba.orderservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumberGenerator {

    static Random random = new Random();

    public static String generateOrderNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTimestamp = dateFormat.format(new Date());

        int randomExtra = random.nextInt(9999) ;
        String orderNumber = String.format("%04d", randomExtra);

        return currentTimestamp + "-" + orderNumber;
    }
}
