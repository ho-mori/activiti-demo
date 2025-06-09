package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Task5 のビジネスロジックを実行するクラスです。
 */
@Component
public class Task5Delegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String input = (String) execution.getVariable("task4Out");
        String result = "Task5 processed " + input;
        System.out.println(result);
        execution.setVariable("task5Out", result);
    }
}
