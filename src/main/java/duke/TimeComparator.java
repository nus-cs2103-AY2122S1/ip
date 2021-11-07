package duke;

import java.util.Comparator;

public class TimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        if (task1 instanceof Deadline && task2 instanceof Deadline) {
            Deadline firstTask = (Deadline) task1;
            Deadline secondTask = (Deadline) task2;
            return firstTask.getLocalDateTime().compareTo(secondTask.getLocalDateTime());
        } else if (task1 instanceof Event && task2 instanceof Event) {
            Event firstTask = (Event) task1;
            Event secondTask = (Event) task2;
            return firstTask.getLocalDateTime().compareTo(secondTask.getLocalDateTime());
        } else if (task1 instanceof Event && task2 instanceof Deadline) {
            Event firstTask = (Event) task1;
            Deadline secondTask = (Deadline) task2;
            return firstTask.getLocalDateTime().compareTo(secondTask.getLocalDateTime());
        } else {
            Deadline firstTask = (Deadline) task1;
            Event secondTask = (Event) task2;
            return firstTask.getLocalDateTime().compareTo(secondTask.getLocalDateTime());
        }
    }

}
