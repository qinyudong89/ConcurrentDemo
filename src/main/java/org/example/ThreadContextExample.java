package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程上下文信息传递
 *
 * @Author : Morgan.Qin
 * @create 2023/7/19 8:31
 */
public class ThreadContextExample {
    private static ThreadLocal<String> context = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 主线程设置上下文信息
        context.set("Hi: Morgan Qin");
        System.out.println("current thread id: " + Thread.currentThread().getId() + ", context: " + context.get());

        // 提交任务到线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            // 子线程获取上下文信息
            String user = context.get();
            System.out.println("current thread id: " + Thread.currentThread().getId() + ", context: " + user);
        });

        // 关闭线程池
        executor.shutdown();
    }
}
