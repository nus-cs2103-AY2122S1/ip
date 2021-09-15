package duke;

public class Undo {
    public String undo() throws DukeException {
        System.out.println("reached undo");
        String output;
        System.out.println("undo size: " + Duke.stateSize());
        if (Duke.stateSize() == 0) {
            throw new DukeException("No more tasks left to undo.");
        } else {
            Duke.state.pop();
            output = "Got it. I have undone the task.";
            output += Duke.getLatestState().printList();
            return output;
        }
    }
}
