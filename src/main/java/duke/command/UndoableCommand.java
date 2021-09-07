package duke.command;

import duke.task.TaskHandler;

interface UndoableCommand {
    void undo(TaskHandler taskHandler);
}
