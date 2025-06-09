package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Task1 のビジネスロジックを担当するクラスです。
 */
@Component
public class Task1Delegate implements JavaDelegate {
    @Override
    /**
     * BPMN の serviceTask から呼び出されます。
     */
    public void execute(DelegateExecution execution) {
        String input = (String) execution.getVariable("task1In");
        String result = "Task1 processed " + input;
        System.out.println(result);
        execution.setVariable("task1Out", result);
    }
}
