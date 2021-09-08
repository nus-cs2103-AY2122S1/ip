package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

public interface Revertible {
    String revert(Tasklist tasks, Storage storage, Ui ui) throws DukeException;
}
