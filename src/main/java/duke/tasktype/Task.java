package duke.tasktype;

/**
 * Task interface.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public interface Task {

    void markComplete();

    String getStatusIcon();

    String getDescription();

    String getTypeIcon();

    String createData();
}
