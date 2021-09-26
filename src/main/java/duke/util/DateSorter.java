package duke.util;

import java.util.Comparator;

import duke.task.Deadline;

public class DateSorter implements Comparator<Deadline> {
    @Override
    public int compare(Deadline deadline1, Deadline deadline2) {
        return deadline1.getBy().compareTo(deadline2.getBy());
    }
}
