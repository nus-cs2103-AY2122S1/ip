package duke.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    public int compare(Task firstTask, Task secondTask) {
        return firstTask.compareTo(secondTask);
    }
}
