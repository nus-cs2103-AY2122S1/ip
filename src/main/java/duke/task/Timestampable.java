package duke.task;

import duke.util.DukeDateTime;

/**
 * A class implements the Timestampable interface if it has a <code>DukeDateTime</code> field.
 */
public interface Timestampable {
    /**
     * Returns whether the caller's <code>DukeDateTime</code> field represents the same date.
     * @param date the date to be matched
     * @return true if the dates are equal
     */
    public boolean onSameDayAs(DukeDateTime date);
}
