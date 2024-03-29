package com.dangchph33497.fpoly.lab8_md18306_ph33497.Models;

import java.util.ArrayList;

public class GHNCancelRequest {
    private ArrayList<String> order_codes;
    public GHNCancelRequest() {
    }

    public GHNCancelRequest(ArrayList<String> order_codes) {
        this.order_codes = order_codes;
    }

    public ArrayList<String> getOrder_codes() {
        return order_codes;
    }

    public void setOrder_codes(ArrayList<String> order_codes) {
        this.order_codes = order_codes;
    }
}
