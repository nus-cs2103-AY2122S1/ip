package duke.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Thread pool initialization instructions.
 *
 * corePoolSize ---- 1
 * maximumPoolSize ---- 1
 * keepAliveTime The maximum survival time of idle threads
 *               exceeding the number of corePoolSize in the thread pool ---- 0 + TimeUnit
 * TimeUnit keepAliveTime ---- TimeUnit.MINUTES
 * workQueue ---- new ArrayBlockingQueue<>(3) ==== 3 capacity blocking queue
 * threadFactory ---- new CustomThreadFactory()
 * rejectedExecutionHandler When the number of submitted tasks exceeds the sum of
 *                          maximumPoolSize + workQueue, means when the 4th task is submitted
 *                          (the previous threads have not been executed,
 *                          sleep(100) is used in this test method),
 *                          the task will be handed by RejectedExecutionHandler
 *
 * The is the CustomThreadPool class that handles ThreadPool.
 */
public class CustomThreadPool {
    private static final int CORE_POOL_SIZE = 1;
    private static final int MAXIMUM_POOL_SIZE = 1;
    private static final long KEEP_ALIVE_TIME = 0L;

    private final ExecutorService mExecutorService;

    /**
     * Constructs a CustomThreadPool object.
     *
     * @param poolSize ThreadPool size.
     */
    public CustomThreadPool(int poolSize) {
        mExecutorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(poolSize),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler()
        );
    }

    /**
     * Constructs a CustomThreadPool object.
     *
     * @param poolSize ThreadPool size.
     * @param priority ThreadPool priority.
     */
    public CustomThreadPool(int poolSize, int priority) {
        mExecutorService = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(poolSize),
                new CustomThreadFactory(priority),
                new CustomRejectedExecutionHandler()
        );
    }

    /**
     * Executes Thread.
     *
     * @param runnable Runnable object.
     */
    public void execute(Runnable runnable) {
        if (mExecutorService.isShutdown()) {
            return;
        }
        mExecutorService.execute(runnable);
    }

    /**
     * Returns status of ThreadPool ExecutorService.
     * @return ThreadPool ExecutorService status.
     */
    public boolean isShutdown() {
        return mExecutorService.isShutdown();
    }

    /**
     * Releases ThreadPool ExecutorService.
     */
    public void release() {
        if (isShutdown()) {
            return;
        }
        mExecutorService.shutdown();
    }
}
