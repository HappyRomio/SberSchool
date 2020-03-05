package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.CashMachine;
import com.sbt.homework.les1Basic.ConsoleHelper;
import com.sbt.homework.les1Basic.CurrencyManipulator;
import com.sbt.homework.les1Basic.CurrencyManipulatorFactory;
import com.sbt.homework.les1Basic.exception.InterruptOperationException;
import com.sbt.homework.les1Basic.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH +"withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {

            boolean isCorrect = false;
            String sum = "";
            ConsoleHelper.writeMessage(res.getString("before"));
            String code = ConsoleHelper.askCurrencyCode();
            CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
            while (!isCorrect) {
                try{
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                sum = ConsoleHelper.readString();
                if (sum.equals("0") ) {
                    isCorrect = false;
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (sum.matches("[0-9]{1,20}")) {
                    isCorrect = true;
                } else {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    continue;
                }
                if (!manipulator.isAmountAvailable(Integer.parseInt(sum))) {
                    isCorrect = false;
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    continue;
                }
                Integer summa = 0;
                for (Map.Entry<Integer, Integer> entry : manipulator.withdrawAmount(Integer.parseInt(sum)).entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                    summa += entry.getKey() + entry.getValue();
                }
                ConsoleHelper.writeMessage(String.format(res.getString("not.enough.money"),summa, code));
            } catch (NotEnoughMoneyException e) {
                    isCorrect = false;
                }
        }

    }
}
