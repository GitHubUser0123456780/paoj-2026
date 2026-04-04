package com.pao.laboratory07.exercise1;

public enum OrderState {
    PLACED,
    PROCESSED,
    SHIPPED,
    DELIVERED,
    CANCELED;
    public OrderState next(){
        return values()[this.ordinal()+1];
    }
    public OrderState previous(){
        return values()[this.ordinal()-1];
    }
}
