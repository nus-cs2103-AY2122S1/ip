package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.PrintCommand;

/**
 * A class that contaisn methods to parse command line strings
 */
public class Parser {
    /**
     * Parses a command line string input on screen to get the details
     * of the Command object
     * @param commandLine The command line string to parse
     * @return The Command object
     * @throws DukeException If the command line string is not valid
     */
    public Command parse(String commandLine) throws DukeException {
        String[] commandLineParts = commandLine.split("\\s+", 2);

        String commandType;
        String commandInfo;

        if (commandLineParts.length == 2) {
            commandType = commandLineParts[0];
            commandInfo = commandLineParts[1];

            // If user inputs extra text after "bye" or "list" commands
            if ((commandType.equals("bye") || commandType.equals("list")) && !commandInfo.equals("")) {
                throw new DukeException("INVALID COMMAND. Please try again!");
            }
        } else { // for "bye" and "list" commands
            commandType = commandLineParts[0];
            commandInfo = "";
        }

        Command command;
        int taskNum;
        String taskDescription;
        String taskInfo;
        String date;

        switch (commandType) {
        case "bye":
            command = new ExitCommand(commandType);
            break;
        case "list":
            command = new ListCommand(commandType);
            break;
        case "done":
            taskNum = getTaskNumFromCommand(commandInfo);
            command = new DoneCommand(commandType, taskNum);
            break;
        case "delete":
            taskNum = getTaskNumFromCommand(commandInfo);
            command = new DeleteCommand(commandType, taskNum);
            break;
        case "todo":
            taskDescription = getTaskDescriptionFromToDoCommand(commandInfo);
            command = new AddToDoCommand(commandType, taskDescription);
            break;
        case "deadline":
            taskInfo = getTaskInfoFromDeadlineCommand(commandInfo);
            command = new AddDeadlineCommand(commandType, taskInfo);
            break;
        case "event":
            taskInfo = getTaskInfoFromEventCommand(commandInfo);
            command = new AddEventCommand(commandType, taskInfo);
            break;
        case "print":
            date = getDateFromPrintCommand(commandInfo);
            command = new PrintCommand(commandType, date);
            break;
        default:
            throw new DukeException("INVALID COMMAND. Please try again!");
        }

        return command;
    }

    /**
     * Gets task number from the command information.
     * @param commandInfo The command information
     * @return The task number
     * @throws DukeException If the information is not valid
     */
    private int getTaskNumFromCommand(String commandInfo) throws DukeException {
        if (commandInfo.trim().length() > 0) {
            try {
                return Integer.parseInt(commandInfo);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to be marked as done!");
            }
        } else {
            throw new DukeException("Please enter a task number to be marked as done!");
        }
    }

    /**
     * Gets task description from ToDo command information.
     * @param commandInfo The ToDo command information
     * @return The task description
     * @throws DukeException If the information is not valid
     */
    private String getTaskDescriptionFromToDoCommand(String commandInfo) throws DukeException {
        if (commandInfo.trim().length() > 0) {
            return commandInfo;
        } else {
            throw new DukeException("The description of a todo cannot be empty!");
        }
    }

    /**
     *
     * @param commandInfo
     * @return
     * @throws DukeException
     */
    private String getTaskInfoFromDeadlineCommand(String commandInfo) throws DukeException {
        if (commandInfo.trim().length() > 0) {
            String[] deadlineTaskDetails = commandInfo.split("/", 2);

            if (deadlineTaskDetails.length == 2) {
                if (deadlineTaskDetails[0].trim().length() > 0) {
                    if (deadlineTaskDetails[1].trim().startsWith("by")) {
                        String beforeDateTime = deadlineTaskDetails[1].trim();
                        String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                        if (beforeDateTimeParts.length == 2) {
                            return commandInfo;
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of a deadline cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /by before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "Enter /by before specifying the date!");
                    }
                } else {
                    if (deadlineTaskDetails[1].trim().startsWith("by")) {
                        String beforeDateTime = deadlineTaskDetails[1].trim();
                        String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                        if (beforeDateTimeParts.length == 2) {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of a deadline cannot be empty!");
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of a deadline cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of a deadline cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /by before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("INCOMPLETE & WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "The description of a deadline cannot be empty!"
                                + System.lineSeparator() + "\t"
                                + "Enter /by before specifying the date/time!");
                    }
                }
            } else {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of a deadline cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /by before specifying the date/time]");
            }
        } else {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of a deadline cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /by before specifying the date/time]");
        }
    }

    /**
     *
     * @param commandInfo
     * @return
     * @throws DukeException
     */
    private String getTaskInfoFromEventCommand(String commandInfo) throws DukeException {
        if (commandInfo.trim().length() > 0) {
            String[] eventTaskDetails = commandInfo.split("/", 2);

            if (eventTaskDetails.length == 2) {
                if (eventTaskDetails[0].trim().length() > 0) {
                    if (eventTaskDetails[1].trim().startsWith("at")) {
                        String startEndDateTime = eventTaskDetails[1].trim();
                        String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                        if (startEndDateTimeParts.length == 2) {
                            return commandInfo;
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of an event cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /at before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "Enter /at before specifying the date!");
                    }
                } else {
                    if (eventTaskDetails[1].trim().startsWith("at")) {
                        String startEndDateTime = eventTaskDetails[1].trim();
                        String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                        if (startEndDateTimeParts.length == 2) {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of an event cannot be empty!");
                        } else {
                            throw new DukeException("INCOMPLETE COMMAND"
                                    + System.lineSeparator() + "\t"
                                    + "The description of an event cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "The date/time of an event cannot be empty!"
                                    + System.lineSeparator() + "\t"
                                    + "[Note: Enter /at before specifying the date/time]");
                        }
                    } else {
                        throw new DukeException("INCOMPLETE & WRONG COMMAND"
                                + System.lineSeparator() + "\t"
                                + "The description of an event cannot be empty!"
                                + System.lineSeparator() + "\t"
                                + "Enter /at before specifying the date/time!");
                    }
                }
            } else {
                throw new DukeException("INCOMPLETE COMMAND"
                        + System.lineSeparator() + "\t"
                        + "The date/time of an event cannot be empty!"
                        + System.lineSeparator() + "\t"
                        + "[Note: Enter /at before specifying the date/time]");
            }
        } else {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description and date/time of an event cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "[Note: Enter /at before specifying the date/time]");
        }
    }

    /**
     *
     * @param commandInfo
     * @return
     * @throws DukeException
     */
    private String getDateFromPrintCommand(String commandInfo) throws DukeException {
        if (commandInfo.trim().length() > 0) {
            if (commandInfo.trim().startsWith("/on")) {
                String[] specificDateParts = commandInfo.split("\\s+", 2);

                if (specificDateParts.length == 2) {
                    return specificDateParts[1];
                } else {
                    throw new DukeException("INCOMPLETE COMMAND"
                            + System.lineSeparator() + "\t"
                            + "The date is not specified!"
                            + System.lineSeparator() + "\t"
                            + "[Note: Enter /on before specifying the date]");
                }
            } else {
                throw new DukeException("WRONG COMMAND"
                        + System.lineSeparator() + "\t"
                        + "Enter /on before specifying the date!");
            }
        } else {
            throw new DukeException("INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "Enter /on before specifying the date!");
        }
    }
}
