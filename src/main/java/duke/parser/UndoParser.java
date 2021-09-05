package duke.parser;

import duke.commands.Command;
import duke.commands.InvalidCommand;
import duke.commands.UndoAddTaskCommand;
import duke.commands.UndoDeleteTaskCommand;
import duke.commands.UndoDoneCommand;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class UndoParser {
    private String command;


    public UndoParser(String command) {
        this.command = command;
    }

    /**
     * Reads the command and processes the first word to determine what the command type is.
     *
     * @param command Command that is passed into the parser.
     * @return Type of Command to be executed.
     */
    public static Command process(String command) {
        if (!(command.equals("undo"))) {
            throw new DukeException("invalidCommand");
        }
        String instruction = Storage.getLastCommand();
        String[] words = instruction.split(" ");
        String parsed = words[0];
        try {
            switch (parsed) {
            case ("delete"):
                return new UndoDeleteTaskCommand(command);
            case ("deadline"):
            case ("event"):
            case ("todo"):
                return new UndoAddTaskCommand(command);
            case ("done"):
                return new UndoDoneCommand(command);
            default:
                throw new DukeException("noChangesToUndo");
            }
        } catch (DukeException err) {
            Ui.display(err.toString());
        }
        return new InvalidCommand(command);
    }
}

