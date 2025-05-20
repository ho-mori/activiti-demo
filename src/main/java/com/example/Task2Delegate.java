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
        System.out.println("Executing Task 2 - Task2Delegate");
    }
}
