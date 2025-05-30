package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

/**
 * 各タスク処理をまとめたクラスです。
 */
@Component
public class WorkflowTasks {
    public void task1(DelegateExecution execution) {
        System.out.println("Executing Task 1 - WorkflowTasks.task1");
    }

    public void task2(DelegateExecution execution) {
        System.out.println("Executing Task 2 - WorkflowTasks.task2");
    }

    public void task3(DelegateExecution execution) {
        System.out.println("Executing Task 3 - WorkflowTasks.task3");
    }

    public void task4(DelegateExecution execution) {
        System.out.println("Executing Task 4 - WorkflowTasks.task4");
    }

    public void task5(DelegateExecution execution) {
        System.out.println("Executing Task 5 - WorkflowTasks.task5");
    }
}
