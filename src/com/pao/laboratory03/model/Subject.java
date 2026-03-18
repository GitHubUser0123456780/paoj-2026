package com.pao.laboratory03.model;

public enum Subject {
    PAOJ("Programare avansata pe obiecte",6){
        @Override
        public String toString(){
            return("PAOJ (" + getName() + ", " + getCredits() + ")\n");
        }
    },
    BD("Baze de date", 4){
        @Override
        public String toString(){
            return("BD (" + getName() + ", " + getCredits() + ")\n");
        }
    },
    SO("Sisteme de operare", 5){
        @Override
        public String toString(){
            return("SO (" + getName() + ", " + getCredits() + ")\n");
        }
    },
    RC("Retele calculatoare", 5){
        @Override
        public String toString(){
            return("RC (" + getName() + ", " + getCredits() + ")\n");
        }
    };
    private String fullName;
    private int credits;
    private Subject(String fullName, int credits){
        this.fullName = fullName;
        this.credits = credits;
    }
    public String getName(){
        return fullName;
    }
    public int getCredits(){
        return credits;
    }
    public abstract String toString();
}
