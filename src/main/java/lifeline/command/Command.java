package lifeline.command;

import lifeline.storage.Storage;
import lifeline.task.TaskList;
import lifeline.ui.Ui;
import lifeline.util.QuadFunction;

public enum Command {
    // inspiration from https://alexpower.me/advanced-java-enum-features-you-need-to-know/
    BYE(CommandHandler::handleBye),
    DEADLINE(CommandHandler::handleDeadline),
    DELETE(CommandHandler::handleDelete),
    DONE(CommandHandler::handleDone),
    EVENT(CommandHandler::handleEvent),
    LIST(CommandHandler::handleList),
    TODO(CommandHandler::handleToDo);

    public QuadFunction<String, Storage, TaskList, Ui> execute;

    Command(QuadFunction<String, Storage, TaskList, Ui> execute) {
        this.execute = execute;
    }
}
