package duke.task;

import duke.util.DukeDateTime;

/**
 * A class implements the Timestampable interface if it has a <code>DukeDateTime</code> field.
 */
public interface Timestampable {
    /**
     * Returns whether the caller's <code>DukeDateTime</code> field represents the same date as the specified date.
     */
    public boolean onSameDayAs(DukeDateTime date);
}
