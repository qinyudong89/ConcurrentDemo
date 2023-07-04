package org.example;

/**
 * @Author : Morgan.Qin
 * @create 2023/7/4 7:22
 */
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ContextAwareThreadPoolExecutor extends ThreadPoolExecutor {
    private ThreadLocal<String> context = new ThreadLocal<>();

    public ContextAwareThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>());
    }

    public void setContext(String value) {
        context.set(value);
    }

    public String getContext() {
        return context.get();
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        String value = context.get();
        // 将上下文信息传递给执行任务的线程
        ((ContextAwareRunnable) runnable).setContext(value);
        super.beforeExecute(thread, runnable);
    }
}
