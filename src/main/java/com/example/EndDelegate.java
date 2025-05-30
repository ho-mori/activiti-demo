package com.example;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * プロセス終了時に実行されるクラスです。
 */
@Component
public class EndDelegate implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        System.out.println("End event - EndDelegate");
    }
}
