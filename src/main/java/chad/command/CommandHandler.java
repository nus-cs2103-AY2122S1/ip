package chad.command;

import java.util.Stack;

import chad.task.TaskHandler;

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

    Command undo(TaskHandler taskHandler) {
        if (undoableCommands.empty()) {
            return null;
        }
        UndoableCommand undoableCommand = undoableCommands.pop();
        undoableCommand.undo(taskHandler);
        return (Command) undoableCommand;
    }
}
