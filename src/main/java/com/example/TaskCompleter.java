package com.example;

import org.activiti.engine.TaskService;
import org.springframework.stereotype.Component;

/**
 * タスク完了処理をまとめたヘルパークラスです。
 */
@Component
public class TaskCompleter {
    /**
     * すべてのタスクを完了させます。
     */
    public void completeTasks(TaskService taskService) {
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println("Completing task: " + task.getName());
            taskService.complete(task.getId());
        });
    }
}
