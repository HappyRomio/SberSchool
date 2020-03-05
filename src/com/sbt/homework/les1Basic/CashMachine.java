package com.sbt.homework.les1Basic;

import com.sbt.homework.les1Basic.command.CommandExecutor;
import com.sbt.homework.les1Basic.exception.InterruptOperationException;
import com.sbt.homework.les1Basic.exception.NotEnoughMoneyException;

import java.util.Locale;

public class CashMachine {

    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";


    public static void main(String... args) throws NotEnoughMoneyException{
       try {
           Locale.setDefault(Locale.ENGLISH);
           Operation operation;
           operation = Operation.LOGIN;
           CommandExecutor.execute(operation);
           do {
               operation = ConsoleHelper.askOperation();
               CommandExecutor.execute(operation);
           } while (operation != Operation.EXIT);

       }catch (InterruptOperationException e){
           ConsoleHelper.printExitMessage();
       }

    }

}
