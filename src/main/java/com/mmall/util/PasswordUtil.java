package com.mmall.util;

import java.util.Date;
import java.util.Random;

/**
 * permission
 * com.mmall.util
 * Created by Zhang Chen
 * 4/25/2018
 */
public class PasswordUtil {

    public final static String[] letters = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n", "p", "q",
            "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "A", "B", "C", "D", "E",
            "F", "G", "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z",
    };

    public final static String[] nums = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String randomPassword() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for(int i = 0; i < length; i++) {
            if(flag) {
                stringBuffer.append(nums[random.nextInt(nums.length)]);
            }
            else {
                stringBuffer.append(letters[random.nextInt(letters.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }

//    public static void main(String[] args) throws Exception{
//        System.out.println(randomPassword());
//        Thread.sleep(100);
//        System.out.println(randomPassword());
//        Thread.sleep(100);
//        System.out.println(randomPassword());
//        Thread.sleep(100);
//        System.out.println(randomPassword());
//    }

}
