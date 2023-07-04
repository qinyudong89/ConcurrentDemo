package org.example;

/**
 * @Author : Morgan.Qin
 * @create 2023/7/4 7:29
 */
public interface ContextAwareRunnable extends Runnable {
    void setContext(String context);
    String getContext();
}

