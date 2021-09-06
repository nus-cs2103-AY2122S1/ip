package duke.tasktype;

/**
 * Task interface.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public interface Task {

    String markComplete();

    String getStatusIcon();

    String getDescription();

    String getTypeIcon();

    String createData();

    String getTaskType();

    boolean isDone();
}
