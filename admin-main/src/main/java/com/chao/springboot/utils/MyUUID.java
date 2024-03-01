package com.chao.springboot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MyUUID {

    public static String  createUUID(){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
        Random random=new Random();
        int rand = random.nextInt(10000);
        return formattedDateTime+rand;
    }
}
