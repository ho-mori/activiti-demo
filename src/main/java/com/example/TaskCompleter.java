package com.example;

import org.activiti.engine.TaskService;
import org.springframework.stereotype.Component;

@Component
public class TaskCompleter {
    public void completeTasks(TaskService taskService) {
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println("Completing task: " + task.getName());
            taskService.complete(task.getId());
        });
    }
}
