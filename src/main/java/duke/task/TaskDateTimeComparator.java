package duke.task;

import java.util.Comparator;

public class TaskDateTimeComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        return task1.compareTo(task2);
    }

}
