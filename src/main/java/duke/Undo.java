package duke;

public class Undo {
    /**
     * Undoes the last command.
     *
     * @return output after undo-ing the latest task.
     * @throws DukeException if no more tasks can be undone.
     */
    public String undo() throws DukeException {
        String output;
        if (Duke.stateSize() == 1) {
            throw new DukeException("No more tasks left to undo.");
        } else {
            Duke.deleteLastState();
            output = "Got it. I have undone the task.\n";
            output += Duke.getLatestState().printList();
            return output;
        }
    }
}
