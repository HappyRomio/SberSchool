package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.Operation;
import com.sbt.homework.les1Basic.exception.InterruptOperationException;
import com.sbt.homework.les1Basic.exception.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {

    private static final Map<Operation, Command> allKnownCommandsMap;
    static {
        allKnownCommandsMap = new HashMap<>();
        allKnownCommandsMap.put(Operation.INFO, new InfoCommand());
        allKnownCommandsMap.put(Operation.DEPOSIT, new DepositCommand());
        allKnownCommandsMap.put(Operation.WITHDRAW, new WithdrawCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.LOGIN, new LoginCommand());
    }
    private CommandExecutor(){}


    public static final void execute(Operation operation) throws InterruptOperationException, NotEnoughMoneyException {
        allKnownCommandsMap.get(operation).execute();
    }


}
