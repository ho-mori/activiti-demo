package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Task4 のビジネスロジックを実行するクラスです。
 */
@Component
public class Task4Delegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String input = (String) execution.getVariable("task3Out");
        String result = "Task4 processed " + input;
        System.out.println(result);
        execution.setVariable("task4Out", result);
    }
}
