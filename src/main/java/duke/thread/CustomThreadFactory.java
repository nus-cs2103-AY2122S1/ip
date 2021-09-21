package duke.thread;

import java.util.concurrent.ThreadFactory;

/**
 * This is the CustomThreadFactory class that creates
 * thread based on the priority.
 */
public class CustomThreadFactory implements ThreadFactory {
    private int priority = Thread.NORM_PRIORITY;

    /**
     * Constructs a CustomThreadFactory object.
     */
    public CustomThreadFactory() {
    }

    /**
     * Constructs a CustomThreadFactory object.
     *
     * @param priority Thread priority: MIN_PRIORITY, NORM_PRIORITY, MAX_PRIORITY.
     */
    public CustomThreadFactory(int priority) {
        this.priority = priority;
    }

    /**
     * Constructs a Thread object based on the priority.
     */
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        return t;
    }
}
