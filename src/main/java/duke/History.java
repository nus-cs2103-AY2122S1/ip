package duke;

import java.util.ArrayList;

import duke.command.Command;

public class History {
    private ArrayList<Command> history;
    private Command redo;

    History() {
        history = new ArrayList<>();
    }

    public Command getLastCommand() {
        int len = history.size();
        return history.get(len - 1);
    }

    /**
     * Gets the last command in the History arraylist
     */
    public void undo() {
        int len = history.size() - 1;
        this.redo = history.remove(len);
    }

    public void addHistory(Command command) {
        history.add(command);
    }

    public boolean isEmpty() {
        return this.history.isEmpty();
    }
}
