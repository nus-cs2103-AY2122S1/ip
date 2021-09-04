package duke.util.sort;

import duke.tasks.Task;

import java.util.Comparator;

public class SortByName implements Comparator<Task> {

    @Override
    public int compare(Task a, Task b) {
        return a.getName().compareTo(b.getName());
    }
}
