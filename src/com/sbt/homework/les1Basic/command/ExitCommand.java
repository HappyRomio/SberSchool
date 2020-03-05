package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.CashMachine;
import com.sbt.homework.les1Basic.ConsoleHelper;
import com.sbt.homework.les1Basic.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String answer  = ConsoleHelper.readString();
        if(answer.equalsIgnoreCase("y")){
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }
    }
}
