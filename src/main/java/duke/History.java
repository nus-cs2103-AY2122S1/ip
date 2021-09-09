package duke;

import duke.command.Command;

import java.util.ArrayList;

public class History {
    public ArrayList<Command> history;
    public Command redo;

    History() {
        history = new ArrayList<>();
    }

    public Command getLastCommand() {
        int len = history.size();
        return history.get(len - 1);
    }

    public void undo() {
        int len = history.size() - 1;
        this.redo = history.remove(len );
    }

    public void addHistory(Command command) {
        history.add(command);
    }
}
