package duke;

public class Undo {
    public String undo() throws DukeException {
        System.out.println("reached undo");
        String output;
        System.out.println("undo size: " + Duke.stateSize());
        if (Duke.stateSize() == 1) {
            throw new DukeException("No more tasks left to undo.");
        } else {
            Duke.deleteLastState();
            TaskList t2 = Duke.getLatestState();
            System.out.println(t2);
            System.out.println("undo size: " + Duke.stateSize());
            output = "Got it. I have undone the task.\n";
            output += Duke.getLatestState().printList();
            return output;
        }
    }
}
