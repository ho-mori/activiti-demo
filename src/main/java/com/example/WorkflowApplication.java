package com.example;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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
        // Set an authenticated user in the security context so that
        // Activiti event listeners can resolve the current principal.
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "user",
                        null,
                        java.util.List.of(new SimpleGrantedAuthority("ROLE_USER"))));

        runtimeService.startProcessInstanceByKey("helloUser");
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println("Completing task: " + task.getName());
            taskService.complete(task.getId());
        });
        System.out.println("Process completed");

        // Clear the security context now that the work is done.
        SecurityContextHolder.clearContext();
    }
}
