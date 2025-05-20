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
        System.out.println("Executing Task 1 - Task1Delegate");
    }
}
