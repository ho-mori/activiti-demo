package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * プロセス開始時に実行されるクラスです。
 */
@Component
public class StartDelegate implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("Start event - StartDelegate");
        // 初期入力値を設定
        execution.setVariable("task1In", "startData");
    }
}
