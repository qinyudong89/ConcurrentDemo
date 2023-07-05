package org.example;

/**
 * 通过InheritableThreadLocal 实现父子线程之间值传递（传递的值是浅直拷贝，存在线程安全问题）
 * @Author : Morgan.Qin
 * @create 2023/7/5 8:32
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContextPropagationExample {
    private static ThreadLocal<String> context = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 设置上下文信息
        context.set("Test Context");

        // 提交一个任务到线程池
        executor.submit(() -> {
            String taskContext = context.get(); // 获取上下文信息
            System.out.println("Executing task with context: " + taskContext);
        });

        // 关闭线程池
        executor.shutdown();
    }
}
