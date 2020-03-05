package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.CashMachine;
import com.sbt.homework.les1Basic.ConsoleHelper;
import com.sbt.homework.les1Basic.CurrencyManipulator;
import com.sbt.homework.les1Basic.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute() {
        if(CurrencyManipulatorFactory.getAllCurrencyManipulators().size()==0){
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }else {
            ConsoleHelper.writeMessage(res.getString("before"));
            for (CurrencyManipulator entry : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                if (entry.hasMoney()) {
                    ConsoleHelper.writeMessage(entry.getCurrencyCode() + " - " + entry.getTotalAmount());
                } else {
                    ConsoleHelper.writeMessage(res.getString("no.money"));
                }
            }
        }
    }
}
