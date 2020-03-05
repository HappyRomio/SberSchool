package com.sbt.homework.les1Basic;

import com.sbt.homework.les1Basic.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"common_en");

    public static String askCurrencyCode() throws InterruptOperationException {
        String result = "";
        while(result.length()!=3) {
            System.out.println(res.getString("choose.currency.code"));
            result = readString();
            if(result.length()!=3) {
                System.out.println(res.getString("invalid.data"));
            }
        }
        return result.toUpperCase();
    }

    public static Operation askOperation() throws InterruptOperationException {
        while(true) {
            System.out.println(res.getString("choose.operation"));
            System.out.println(res.getString("operation.INFO"));
            System.out.println(res.getString("operation.DEPOSIT"));
            System.out.println(res.getString("operation.WITHDRAW"));
            System.out.println(res.getString("operation.EXIT"));
            try {
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch (IllegalArgumentException e) {
                System.out.println(res.getString("invalid.data"));
            }
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        boolean valid = false;
        String[] result = null;
        while(!valid) {
            System.out.println(res.getString("choose.denomination.and.count.format"));
            result = readString().split(" ");
            if(result.length==2) {
                if (result[0].matches("[0-9]{1,20}") && result[1].matches("[0-9]{1,20}") && !(result[0].equals("0") || result[1].equals("0"))) {
                    valid = true;
                } else {
                    System.out.println(res.getString("invalid.data"));
                }
            } else {
                System.out.println(res.getString("invalid.data"));
            }
        }

            return result;
    }

    public static void writeMessage(String message)
    {
            System.out.println(message);
    }


    public static String readString() throws InterruptOperationException {
        try {
            String result = bis.readLine();
            if (result.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
            return result;
        }catch (IOException e){
            return null;
        }

    }

    public static void printExitMessage(){
        System.out.println(res.getString("the.end"));
    }
}
