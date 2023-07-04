/**
 * @Author : Morgan.Qin
 * @create 2023/7/4 7:30
 */

import org.example.ContextAwareRunnable;
import org.example.ContextAwareThreadPoolExecutor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ContextAwareThreadPoolExecutorTest {
    @Test
    public void testContextAwareThreadPoolExecutor() throws InterruptedException {
        ContextAwareThreadPoolExecutor executor = new ContextAwareThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS);

        // 设置上下文信息
        executor.setContext("Test Context");

        // 提交一个任务到线程池
        MyTask task = new MyTask();
        task.setContext(executor.getContext());  // 将线程池的上下文信息传递给任务
        executor.execute(task);

        // 关闭线程池
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
    }

    private static class MyTask implements ContextAwareRunnable {
        private String context;

        @Override
        public void setContext(String context) {
            this.context = context;
        }

        @Override
        public String getContext() {
            return context;
        }

        @Override
        public void run() {
            // 执行任务逻辑
            System.out.println("Executing task with context: " + context);
        }
    }
}
