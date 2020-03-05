package com.sbt.homework.les1Basic;

public enum Operation {
    LOGIN(0),
    INFO(1),
    DEPOSIT(2),
    WITHDRAW(3),
    EXIT(4);

    private int ord;

    Operation(int i){
        ord = i;
    }


    public static Operation getAllowableOperationByOrdinal(Integer i){
        switch(i){
            case 1:
                return Operation.INFO;
            case 2:
                return Operation.DEPOSIT;
            case 3:
                return Operation.WITHDRAW;
            case 4:
                return Operation.EXIT;
                default:
                    throw new IllegalArgumentException();
        }
    }

    }
