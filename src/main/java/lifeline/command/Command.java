package lifeline.command;

import java.util.Arrays;
import java.util.List;

import lifeline.storage.Storage;
import lifeline.task.TaskList;
import lifeline.ui.Ui;
import lifeline.util.QuadFunction;

public enum Command {
    // inspiration from https://alexpower.me/advanced-java-enum-features-you-need-to-know/
    ALIAS(CommandHandler::handleAlias, "alias", "a", "al"),
    BYE(CommandHandler::handleBye, "bye", "bb", "goodbye", "b"),
    DEADLINE(CommandHandler::handleDeadline, "deadline", "d", "dl"),
    DELETE(CommandHandler::handleDelete, "delete", "del", "rm"),
    DONE(CommandHandler::handleDone, "done", "complete"),
    EVENT(CommandHandler::handleEvent, "event", "e"),
    FIND(CommandHandler:: handleFind, "find", "f"),
    HELP(CommandHandler:: handleHelp, "help", "h"),
    LIST(CommandHandler::handleList, "list", "ls", "l"),
    TODO(CommandHandler::handleToDo, "todo", "t", "td");

    private QuadFunction<String, Storage, TaskList, Ui> execute;
    private List<String> aliases;

    Command(QuadFunction<String, Storage, TaskList, Ui> execute, String... aliases) {
        this.execute = execute;
        this.aliases = Arrays.asList(aliases);
    }

    public QuadFunction<String, Storage, TaskList, Ui> getExecute() {
        return execute;
    }

    public boolean hasCommand(String command) {
        return this.aliases.contains(command);
    }
}
