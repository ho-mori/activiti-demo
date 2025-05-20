package com.example;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {
    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public WorkflowService(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    /**
     * Run workflow with all logic in this method.
     */
    public String runDirect() {
        authenticate();
        runtimeService.startProcessInstanceByKey("helloUser");
        taskService.createTaskQuery().list().forEach(task -> {
            taskService.complete(task.getId());
        });
        SecurityContextHolder.clearContext();
        return "Process completed: direct";
    }

    /**
     * Run workflow and delegate task completion to a private method.
     */
    public String runWithHelperMethod() {
        authenticate();
        runtimeService.startProcessInstanceByKey("helloUser");
        completeTasks();
        SecurityContextHolder.clearContext();
        return "Process completed: helper method";
    }

    private void completeTasks() {
        taskService.createTaskQuery().list().forEach(task -> taskService.complete(task.getId()));
    }

    /**
     * Run workflow and delegate task completion to a separate class.
     */
    public String runWithTaskCompleter(TaskCompleter taskCompleter) {
        authenticate();
        runtimeService.startProcessInstanceByKey("helloUser");
        taskCompleter.completeTasks(taskService);
        SecurityContextHolder.clearContext();
        return "Process completed: separate class";
    }

    private void authenticate() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "user",
                        null,
                        java.util.List.of(new SimpleGrantedAuthority("ROLE_USER"))));
    }
}
