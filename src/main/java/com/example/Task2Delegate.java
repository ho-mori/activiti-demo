package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Task2 のビジネスロジックを実行するクラスです。
 */
@Component
public class Task2Delegate implements JavaDelegate {
    @Override
    /**
     * serviceTask から実行されるメソッドです。
     */
    public void execute(DelegateExecution execution) {
        String input = (String) execution.getVariable("task1Out");
        String result = "Task2 processed " + input;
        System.out.println(result);
        execution.setVariable("task2Out", result);
    }
}
