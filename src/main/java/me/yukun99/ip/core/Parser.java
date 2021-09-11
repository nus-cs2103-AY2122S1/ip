package me.yukun99.ip.core;

import static me.yukun99.ip.tasks.Task.Type;

import me.yukun99.ip.HelpBot;
import me.yukun99.ip.commands.AddCommand;
import me.yukun99.ip.commands.ArchiveCommand;
import me.yukun99.ip.commands.Command;
import me.yukun99.ip.commands.DeleteCommand;
import me.yukun99.ip.commands.DoneCommand;
import me.yukun99.ip.commands.ExitCommand;
import me.yukun99.ip.commands.FindCommand;
import me.yukun99.ip.commands.HelpCommand;
import me.yukun99.ip.commands.ListCommand;
import me.yukun99.ip.commands.UpdateCommand;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidCommandException;

/**
 * Parser for HelpBot commands.
 */
public class Parser {
    // Current instance of HelpBot.
    private final HelpBot helpBot;
    // TaskList instance from the current instance of HelpBot.
    private final TaskList taskList;
    // Storage instance from the current instance of HelpBot.
    private final Storage storage;
    // TaskFinder instance from the current instance of HelpBot.
    private final TaskFinder taskFinder;

    /**
     * Constructor for a Parser instance.
     *
     * @param helpBot HelpBot instance that this instance of Parser belongs to.
     * @param taskList TaskList instance from the current instance of HelpBot.
     * @param storage Storage instance from the current instance of HelpBot.
     * @param taskFinder TaskFinder instance from the current instance of HelpBot.
     */
    public Parser(HelpBot helpBot, TaskList taskList, Storage storage, TaskFinder taskFinder) {
        this.helpBot = helpBot;
        this.taskList = taskList;
        this.storage = storage;
        this.taskFinder = taskFinder;
    }

    /**
     * Parses message sent by the user.
     *
     * @param message Message sent by the user.
     * @return Command from message sent to the HelpBot.
     * @throws HelpBotIllegalArgumentException If arguments provided for the commands are missing or wrong.
     * @throws HelpBotInvalidCommandException If command sent by the user is invalid.
     */
    public Command parseCommand(String message)
            throws HelpBotInvalidCommandException, HelpBotIllegalArgumentException {
        Command command = null;
        if (message.equals("list")) {
            command = new ListCommand(null, taskList);
        }
        if (message.startsWith("list ")) {
            String date = message.replace("list ", "");
            String[] args = {date};
            command = new ListCommand(args, taskList);
        }
        if (message.startsWith("find ")) {
            String word = message.replaceFirst("find ", "");
            String[] args = word.split(" ");
            command = new FindCommand(args, taskList, taskFinder);
        }
        if (message.startsWith("todo ")) {
            String name = message.replaceFirst("todo ", "");
            String[] args = {name};
            command = new AddCommand(args, taskList, Type.TODO, storage);
        }
        if (message.startsWith("deadline ")) {
            String args = message.replaceFirst("deadline ", "");
            if (args.contains(" /by ")) {
                String[] argsSplit = args.split(" /by ");
                command = new AddCommand(argsSplit, taskList, Type.DEADLINE, storage);
            } else {
                throw new HelpBotIllegalArgumentException(args);
            }
        }
        if (message.startsWith("event ")) {
            String args = message.replaceFirst("event ", "");
            if (args.contains(" /at ")) {
                String[] argsSplit = args.split(" /at ");
                command = new AddCommand(argsSplit, taskList, Type.EVENT, storage);
            } else {
                throw new HelpBotIllegalArgumentException(args);
            }
        }
        if (message.startsWith("update ")) {
            String args = message.replaceFirst("update ", "");
            if (args.contains(" /to ")) {
                String[] argsSplit = args.split(" /to ");
                command = new UpdateCommand(argsSplit, taskList, storage);
            } else {
                throw new HelpBotIllegalArgumentException(args);
            }
        }
        if (message.startsWith("delete ")) {
            String strIndex = message.replaceFirst("delete ", "");
            String[] args = strIndex.split(" ");
            command = new DeleteCommand(args, taskList, storage);
        }
        if (message.startsWith("archive ")) {
            String strIndex = message.replaceFirst("archive ", "");
            String[] args = strIndex.split(" ");
            command = new ArchiveCommand(args, taskList, storage);
        }
        if (message.startsWith("done ")) {
            String strIndex = message.replaceFirst("done ", "");
            String[] args = {strIndex};
            command = new DoneCommand(args, taskList, storage);
        }
        if (message.equals("help")) {
            command = new HelpCommand(null, null);
        }
        if (message.equals("bye")) {
            command = new ExitCommand(null, null, helpBot);
        }
        if (command == null) {
            throw new HelpBotInvalidCommandException(message);
        }
        return command;
    }
}
