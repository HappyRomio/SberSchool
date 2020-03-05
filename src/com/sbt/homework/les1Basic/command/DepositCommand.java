package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.CashMachine;
import com.sbt.homework.les1Basic.ConsoleHelper;
import com.sbt.homework.les1Basic.CurrencyManipulatorFactory;
import com.sbt.homework.les1Basic.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle( CashMachine.RESOURCE_PATH + "deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currency = ConsoleHelper.askCurrencyCode();
        String [] digits = ConsoleHelper.getValidTwoDigits(currency);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency).addAmount(Integer.parseInt(digits[0]),Integer.parseInt(digits[1]));
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(digits[0]) * Integer.parseInt(digits[1]), currency));
    }
}
