package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ワークフローを実行するための REST コントローラーです。
 */
@RestController
public class WorkflowController {
    private final WorkflowService workflowService;
    private final TaskCompleter taskCompleter;

    /**
     * コンストラクターで必要な依存を受け取ります。
     */
    public WorkflowController(WorkflowService workflowService, TaskCompleter taskCompleter) {
        this.workflowService = workflowService;
        this.taskCompleter = taskCompleter;
    }

    /**
     * すべての処理をこのメソッド内で行うバージョン
     */
    @GetMapping("/workflow/direct")
    public String direct() {
        return workflowService.runDirect();
    }

    /**
     * ヘルパーメソッドを利用するバージョン
     */
    @GetMapping("/workflow/helper")
    public String helperMethod() {
        return workflowService.runWithHelperMethod();
    }

    /**
     * 別クラスに処理を委譲するバージョン
     */
    @GetMapping("/workflow/separate-class")
    public String separateClass() {
        return workflowService.runWithTaskCompleter(taskCompleter);
    }

    /**
     * BPMN XML を動的に読み込むバージョン
     */
    @GetMapping("/workflow/reload")
    public String reload() {
        return workflowService.runWithDynamicDeployment();
    }
}
