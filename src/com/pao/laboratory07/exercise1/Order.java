package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.CannotCancelFinalOrderException;
import com.pao.laboratory07.exercise1.exceptions.CannotRevertInitialOrderStateException;
import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;

public class Order{
    OrderState state;
    public Order(OrderState state){
        this.state = state;
    }
    public void nextState(){
        if (state == OrderState.DELIVERED){
            throw new OrderIsAlreadyFinalException();
        }
        else state = state.next();
        System.out.println("Order state updated to: " + state);
    }
    public void cancel(){
        if (state == OrderState.DELIVERED || state == OrderState.CANCELED)
            throw new CannotCancelFinalOrderException();
        else state = OrderState.CANCELED;
        System.out.println("Order has been canceled.");
    }
    public void undoState(){
        if (state == OrderState.PLACED)
            throw new CannotRevertInitialOrderStateException();
        else state = state.previous();
        System.out.println("Order state reverted to: " + state);
    }
}