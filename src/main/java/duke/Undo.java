package duke;

public class Undo {
    public String undo() throws DukeException {
        String output;
        if (Duke.stateSize() == 1) {
            throw new DukeException("No more tasks left to undo.");
        } else {
            Duke.deleteLastState();
            TaskList t2 = Duke.getLatestState();
            output = "Got it. I have undone the task.\n";
            output += Duke.getLatestState().printList();
            return output;
        }
    }
}
