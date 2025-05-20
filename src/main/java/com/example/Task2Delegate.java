package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class Task2Delegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Executing Task 2 - Task2Delegate");
    }
}
