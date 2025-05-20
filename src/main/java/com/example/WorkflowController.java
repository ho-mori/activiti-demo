package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController {
    private final WorkflowService workflowService;
    private final TaskCompleter taskCompleter;

    public WorkflowController(WorkflowService workflowService, TaskCompleter taskCompleter) {
        this.workflowService = workflowService;
        this.taskCompleter = taskCompleter;
    }

    @GetMapping("/workflow/direct")
    public String direct() {
        return workflowService.runDirect();
    }

    @GetMapping("/workflow/helper")
    public String helperMethod() {
        return workflowService.runWithHelperMethod();
    }

    @GetMapping("/workflow/separate-class")
    public String separateClass() {
        return workflowService.runWithTaskCompleter(taskCompleter);
    }
}
