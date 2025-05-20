package com.example;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkflowApplication implements CommandLineRunner {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public WorkflowApplication(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @Override
    public void run(String... args) {
        runtimeService.startProcessInstanceByKey("helloUser");
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println("Completing task: " + task.getName());
            taskService.complete(task.getId());
        });
        System.out.println("Process completed");
    }
}
