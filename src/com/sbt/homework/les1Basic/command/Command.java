package com.sbt.homework.les1Basic.command;

import com.sbt.homework.les1Basic.exception.InterruptOperationException;
import com.sbt.homework.les1Basic.exception.NotEnoughMoneyException;

interface Command {
    void execute() throws InterruptOperationException, NotEnoughMoneyException;
}
