package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Task3 のビジネスロジックを実行するクラスです。
 */
@Component
public class Task3Delegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String input = (String) execution.getVariable("task2Out");
        String result = "Task3 processed " + input;
        System.out.println(result);
        execution.setVariable("task3Out", result);
    }
}
