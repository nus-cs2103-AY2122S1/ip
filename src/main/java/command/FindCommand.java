package command;

import exceptions.DukeException;

import parser.Parser;

import storage.Storage;

import task.TaskList;

import ui.Ui;

public class FindCommand extends Command {
    private final String command;
    private final String delimiter;

    private final String MESSAGE_USAGE = String.format("Usage: %s <number>", Parser.COMMAND_FIND);
    private final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:\n%s";

    public FindCommand(String command, String delimiter) {
        this.command = command;
        this.delimiter = delimiter;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] commands = command.split(delimiter, 2);
        if (commands.length < 2) {
            throw new DukeException(MESSAGE_USAGE);
        }

        String matchingTasks = tasks.findTasksContainingString(commands[1]).toString();
        ui.print(String.format(MESSAGE_SUCCESS, matchingTasks));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
