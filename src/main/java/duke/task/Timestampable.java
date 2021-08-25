package duke.task;

import duke.util.DukeDateTime;

public interface Timestampable {
    public boolean onSameDayAs(DukeDateTime date);
}
