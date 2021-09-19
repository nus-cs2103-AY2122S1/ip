package chad.command;

import chad.task.TaskHandler;

interface UndoableCommand {
    void undo(TaskHandler taskHandler);
}
