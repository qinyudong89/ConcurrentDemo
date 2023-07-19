package org.example;

/**
 * @Author : Morgan.Qin
 * @create 2023/7/19 8:46
 */

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransmittableThreadLocalExample {
    private static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        // 设置上下文信息
        context.set("Test Context");

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 提交第一个任务到线程池
        executor.submit(() -> {
            String taskContext = context.get(); // 获取上下文信息
            System.out.println("Executing task 1 with context: " + taskContext);

            // 修改上下文信息
            context.set("Updated Context");

            // 提交第二个任务到线程池
            executor.submit(() -> {
                String nestedTaskContext = context.get(); // 获取上下文信息
                System.out.println("Executing nested task with context: " + nestedTaskContext);
            });
        });

        // 关闭线程池
        executor.shutdown();
    }
}
