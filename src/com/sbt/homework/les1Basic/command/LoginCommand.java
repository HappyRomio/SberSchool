package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.CashMachine;
import com.sbt.homework.les1Basic.ConsoleHelper;
import com.sbt.homework.les1Basic.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");


    @Override
    public void execute() throws InterruptOperationException{

         boolean isCorrect = false;
        ConsoleHelper.writeMessage(res.getString("before"));
         while(!isCorrect){
             ConsoleHelper.writeMessage(res.getString("specify.data"));
             String cardNum = ConsoleHelper.readString();
             if(!(cardNum.matches("^[0-9]{12,12}$") && !cardNum.equals("0"))){
                 ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                 continue;
             }
             String pin = ConsoleHelper.readString();
             if(!(pin.matches("^[0-9]{4,4}$") && !pin.equals("0"))){
                 ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                 continue;
             }

             if(validCreditCards.containsKey(cardNum)){
                 if(validCreditCards.getString(cardNum).equals(pin)){
                     ConsoleHelper.writeMessage(String.format(res.getString("success.format"),cardNum));
                     isCorrect = true;
                 } else {
                     isCorrect = false;
                     ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),cardNum));
                     ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"),cardNum));
                     ConsoleHelper.writeMessage(String.format(res.getString("try.again.with.details"),cardNum));
                     continue;
                 }
             } else {
                 ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),cardNum));
                 ConsoleHelper.writeMessage(String.format(res.getString("try.again.or.exit"),cardNum));
                 ConsoleHelper.writeMessage(String.format(res.getString("try.again.with.details"),cardNum));
                 isCorrect = false;
                 continue;
             }
         }

    }
}
