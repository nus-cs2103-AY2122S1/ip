package duke.command;

import java.util.Stack;

import duke.task.TaskHandler;

class CommandHandler {

    private static CommandHandler instance;

    private Stack<UndoableCommand> undoableCommands;

    private CommandHandler() {
        undoableCommands = new Stack<>();
    }

    static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    void addToUndoableCommands(UndoableCommand undoableCommand) {
        undoableCommands.push(undoableCommand);
    }

    void undo(TaskHandler taskHandler) {
        if (undoableCommands.empty()) {
            return;
        }
        UndoableCommand undoableCommand = undoableCommands.pop();
        undoableCommand.undo(taskHandler);
    }
}
