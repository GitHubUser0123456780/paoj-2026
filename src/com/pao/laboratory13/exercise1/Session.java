package com.pao.laboratory13.exercise1;

public class Session {
    private int historyCount = 0;
    private SessionState state = SessionState.INIT;

    public int getHistoryCount(){
        return historyCount;
    }
    public SessionState getState(){
        return state;
    }

    public void setHistoryCount(int historyCount){
        this.historyCount = historyCount;
    }
    public void setState(SessionState state){
        this.state = state;
    }
}
