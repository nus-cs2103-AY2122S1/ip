package saber.parser;

import saber.Saber;
import saber.commands.AddCommand;
import saber.commands.ByeCommand;
import saber.commands.DeadlineCommand;
import saber.commands.DeleteCommand;
import saber.commands.DoneCommand;
import saber.commands.EventCommand;
import saber.commands.FindCommand;
import saber.commands.ListCommand;
import saber.commands.SaberCommand;
import saber.commands.SortCommand;
import saber.commands.TodoCommand;
import saber.exceptions.MissingArgumentException;
import saber.exceptions.MissingTimeException;
import saber.exceptions.SaberCommandNotFoundException;

/** Encapsulates a parser for the Saber application (to parse user commands) */
public class SaberParser {
    private String[] parsedCommand;
    private String time;
    private String according;

    /**
     * Constructs for the SaberParser class, this constructor will also parse the command
     * passed in the parameters
     *
     * @param unparsedCommand the raw unparsed command (from the user)
     */
    public SaberParser(String unparsedCommand) {
        String unparsedCommandWithoutTime;
        String[] parsedCommandTemp = unparsedCommand.split(" ", 2);

        // To check whether the unparsed command has a time component to it and is a deadline or event command
        // Because we do not want to unintentionally parse /at or /by as time when the command is not of type
        // deadline or event
        boolean isDeadlineCommandType = parsedCommandTemp[0].equals(Saber.InputCommand.deadline.name());
        boolean isEventCommandType = parsedCommandTemp[0].equals(Saber.InputCommand.event.name());
        boolean isSortCommandType = parsedCommandTemp[0].equals(Saber.InputCommand.sort.name());
        boolean hasTime = unparsedCommand.contains(" /at ") || unparsedCommand.contains(" /by ");

        // Only parse /at and /by as time when the command is of type deadline or event
        if (hasTime && (isDeadlineCommandType || isEventCommandType)) {
            int slashIndex = unparsedCommand.indexOf(" /at ");
            if (slashIndex == -1) {
                slashIndex = unparsedCommand.indexOf(" /by ");
            }
            unparsedCommandWithoutTime = unparsedCommand.substring(0, slashIndex).trim();
            try {
                this.time = unparsedCommand.substring(slashIndex + 5);
            } catch (IndexOutOfBoundsException e) {
                this.time = "";
            }
        } else {
            this.time = "";
            unparsedCommandWithoutTime = unparsedCommand;
        }
        this.parsedCommand = unparsedCommandWithoutTime.split(" ", 2);
    }

    /**
     * Gets Command Type of the command
     *
     * @return Command Type as Saber.InputCommand enums
     * @throws SaberCommandNotFoundException if the command is not recognisable by Saber application
     */
    public Saber.InputCommand getCommandType() throws SaberCommandNotFoundException {
        Saber.InputCommand commandType;

        try {
            commandType = Saber.InputCommand.valueOf(this.parsedCommand[0]);
        } catch (IllegalArgumentException e) {
            throw new SaberCommandNotFoundException("Invalid Command");
        }
        return commandType;
    }

    /**
     * Gets Time from the command
     *
     * @return the time parsed from the command as a String
     * @throws MissingTimeException if the command does not have a time component
     */
    public String getTime() throws MissingTimeException {
        if (this.time.equals("")) {
            throw new MissingTimeException("Time not found");
        }
        return this.time;
    }

    /**
     * Gets argument from the command
     *
     * @return the argument parsed from the command as a String
     * @throws MissingArgumentException if the command does not have an argument component
     */
    public String getArgument() throws MissingArgumentException {
        if (parsedCommand.length == 1) {
            throw new MissingArgumentException("Argument not found");
        }
        return this.parsedCommand[1];
    }

    /**
     * Gets a SaberCommand object from the command
     *
     * @return a SaberCommand object that the caller can use to execute the command
     * @throws SaberCommandNotFoundException if the command is not recognisable by Saber application
     */
    public SaberCommand parse() throws SaberCommandNotFoundException {
        Saber.InputCommand commandType = getCommandType();

        SaberCommand command;
        switch (commandType) {
        case add:
            try {
                String taskForAdd = getArgument();
                command = new AddCommand(taskForAdd, false);
            } catch (MissingArgumentException e) {
                command = new AddCommand("", true);
            }
            break;
        case bye:
            command = new ByeCommand();
            break;
        case done:
            try {
                int taskIndex = Integer.parseInt(getArgument());
                command = new DoneCommand(taskIndex - 1, false);
            } catch (MissingArgumentException | NumberFormatException e) {
                command = new DoneCommand(0, true);
            }
            break;
        case deadline:
            try {
                String deadlineTask = getArgument();
                String deadlineTime = getTime();
                command = new DeadlineCommand(deadlineTask, deadlineTime, false, false);
            } catch (MissingArgumentException e) {
                command = new DeadlineCommand("", "", true, false);
            } catch (MissingTimeException e) {
                command = new DeadlineCommand("", "", false, true);
            }
            break;
        case delete:
            try {
                int taskIndex = Integer.parseInt(getArgument());
                command = new DeleteCommand(taskIndex - 1, false);
            } catch (MissingArgumentException | NumberFormatException e) {
                command = new DeleteCommand(0, true);
            }
            break;
        case event:
            try {
                String eventTask = getArgument();
                String eventTime = getTime();
                command = new EventCommand(eventTask, eventTime, false, false);
            } catch (MissingArgumentException e) {
                command = new EventCommand("", "", true, false);
            } catch (MissingTimeException e) {
                command = new EventCommand("", "", false, true);
            }
            break;
        case find:
            try {
                String findString = getArgument();
                command = new FindCommand(findString, false);
            } catch (MissingArgumentException e) {
                command = new FindCommand("", true);
            }
            break;
        case todo:
            try {
                String taskForTodo = getArgument();
                command = new TodoCommand(taskForTodo, false);
            } catch (MissingArgumentException e) {
                command = new TodoCommand("", true);
            }
            break;
        case sort:
            command = new SortCommand();
            break;
        case list:
            command = new ListCommand();
            break;
        default:
            command = null;
        }
        return command;
    }
}
