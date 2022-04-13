package duke.util.sort;

import java.util.Comparator;

import duke.tasks.Task;

public class SortByType implements Comparator<Task> {

    @Override
    public int compare(Task a, Task b) {
        return a.getMarker().compareTo(b.getMarker());
    }
}
