package duke.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This is the CustomRejectedExecutionHandler class that
 * handles the case when all threads are busy.
 */
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    /**
     * Executes default policy.
     *
     * @param runnable Runnable.
     * @param executor ThreadPoolExecutor.
     */
    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {

    }
}
