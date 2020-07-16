package com.ocean.mvc.service;

/**
 * 功能描述
 *
 * @author chy
 * @date 2020/7/15 0015
 */
public class TestInvoke {

    public static void main(String[] args) {
        String s = HttpConnectionService.getInstance().get("https://bet-api.gbank.team/api/bet/sport/list?country=ke");
        System.out.println(s);
    }

}
