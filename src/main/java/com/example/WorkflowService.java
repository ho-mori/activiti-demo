package com.example;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * ワークフローの実行ロジックを提供するサービスクラスです。
 */
@Service
public class WorkflowService {
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final RepositoryService repositoryService;
    private final String bpmnPath;

    /**
     * 各種 Activiti サービスを注入します。
     */
    public WorkflowService(RuntimeService runtimeService,
                           TaskService taskService,
                           RepositoryService repositoryService,
                           @Value("${workflow.bpmn-path:src/main/resources/processes/hello-user.bpmn20.xml}") String bpmnPath) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
        this.repositoryService = repositoryService;
        this.bpmnPath = bpmnPath;
    }

    /**
     * すべての処理を一つのメソッドで行います。
     */
    public String runDirect() {
        authenticate();
        System.out.println("Starting process: direct");
        runtimeService.startProcessInstanceByKey("helloUser");
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println("Completing task: " + task.getName());
            taskService.complete(task.getId());
        });
        SecurityContextHolder.clearContext();
        return "Process completed: direct";
    }

    /**
     * タスク完了をプライベートメソッドに委譲します。
     */
    public String runWithHelperMethod() {
        authenticate();
        System.out.println("Starting process: helper method");
        runtimeService.startProcessInstanceByKey("helloUser");
        completeTasks();
        SecurityContextHolder.clearContext();
        return "Process completed: helper method";
    }

    /** タスクを順に完了させる内部メソッド */
    private void completeTasks() {
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println("Completing task: " + task.getName());
            taskService.complete(task.getId());
        });
    }

    /**
     * タスク完了処理を別クラスに委譲します。
     */
    public String runWithTaskCompleter(TaskCompleter taskCompleter) {
        authenticate();
        System.out.println("Starting process: separate class");
        runtimeService.startProcessInstanceByKey("helloUser");
        taskCompleter.completeTasks(taskService);
        SecurityContextHolder.clearContext();
        return "Process completed: separate class";
    }

    /**
     * 毎回 XML をデプロイし、実行後に削除します。
     */
    public String runWithDynamicDeployment() {
        authenticate();
        System.out.println("Starting process: dynamic deployment");
        try (java.io.InputStream in = new java.io.FileInputStream(bpmnPath)) {
            Deployment deployment = repositoryService.createDeployment()
                    .addInputStream("hello-user.bpmn20.xml", in)
                    .deploy();

            runtimeService.startProcessInstanceByKey("helloUser");
            completeTasks();

            repositoryService.deleteDeployment(deployment.getId(), true);
            return "Process completed: dynamic deployment";
        } catch (java.io.IOException e) {
            throw new RuntimeException("Failed to load BPMN file: " + bpmnPath, e);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    /**
     * 簡易的な認証情報を設定します。
     */
    private void authenticate() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "user",
                        null,
                        java.util.List.of(new SimpleGrantedAuthority("ROLE_USER"))));
    }
}
