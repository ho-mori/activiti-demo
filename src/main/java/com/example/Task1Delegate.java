package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class Task1Delegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Executing Task 1 - Task1Delegate");
    }
}
