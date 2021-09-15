package duke.Tool;

import duke.Exceptions.DeleteWrongIndexException;
import duke.Exceptions.EmptyTaskListException;
import duke.Exceptions.NoDescriptionException;
import duke.Exceptions.NoCommandException;
import duke.Exceptions.NoTimeException;
import duke.Exceptions.WrongTimeFormatException;
import duke.Tasks.*;

import java.time.LocalDateTime;
import java.lang.String;
import java.time.format.DateTimeFormatter;

/**
 * Represents Parser class:  deals with making sense of the user command
 */
public class Parser {

    private final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static enum Operation {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT, FIND, PERIOD
    }

    /**
     * Is the integer method to judge whether input is integer
     *
     * @param input
     * @return boolean
     */
    public static boolean isInteger(String input) {
        if (input == null) {
            return false;
        } else {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
    }

    /**
     * Done command task process
     *
     * @param cmd
     * @param tasks
     * @return Task object
     * @throws EmptyTaskListException
     * @throws NoDescriptionException
     * @throws NoCommandException
     * @return the task of done operation
     */
    public static Task done(String cmd, TaskList tasks) throws EmptyTaskListException, NoDescriptionException, NoCommandException {

        int order = tasks.size();
        if (cmd.split(" ").length == 1) {
            throw new NoDescriptionException("Done");

        } else if (!isInteger(cmd.split(" ")[1])) {
            throw new NoCommandException(cmd);

        } else if (Integer.parseInt(cmd.split(" ")[1]) > order) {
            throw new EmptyTaskListException("Done");

        } else {
            int num = Integer.parseInt(cmd.split(" ")[1]) - 1;
            return new Done(num);

        }

    }

    /**
     * Deletes the task process
     *
     * @param cmd
     * @param tasks
     * @return Task object
     * @throws DeleteWrongIndexException
     * @throws NoDescriptionException
     * @throws NoCommandException
     * @return Task of delete operation
     */
    public static Task delete(String cmd, TaskList tasks) throws DeleteWrongIndexException, NoDescriptionException, NoCommandException {

        int order = tasks.size();
        if (cmd.split(" ").length == 1)  {
            throw new NoDescriptionException("Delete");

        } else if (!isInteger(cmd.split(" ")[1])) {
            throw new NoCommandException(cmd);

        } else if (Integer.parseInt(cmd.split(" ")[1]) > order) {
            throw new DeleteWrongIndexException("Delete");

        } else {

            int num = Integer.parseInt(cmd.split(" ")[1]) - 1;
            return new Delete(num);
        }

    }

    /**
     * Add tass like Todo, Deadline, Event command task process
     *
     * @param cmd
     * @param tasks
     * @return Task object
     * @throws NoDescriptionException
     * @throws NoTimeException
     * @throws NoCommandException
     * @throws WrongTimeFormatException
     * @return Task
     */
    public static Task addTask(String cmd, TaskList tasks) throws NoDescriptionException, NoTimeException,
            NoCommandException, WrongTimeFormatException {

        Parser.Operation instruction = Parser.Operation.valueOf(cmd.toUpperCase().split(" ")[0]);
        int order = tasks.size();
        if (cmd.split(" ").length != 1) {
            switch (instruction) {
            case TODO:

                if (cmd.split(" ").length == 1) {
                    throw new NoDescriptionException(instruction.name());

                } else {
                    return new Todo(cmd.substring(5), false);
                }

            case DEADLINE:
                String subString_deadline = cmd.substring(9);
                if (subString_deadline.split(" /by ").length == 1) {
                    throw new NoTimeException(instruction.name());

                } else {
                    try {
                        return new Deadline(subString_deadline.split(" /by ")[0],
                                LocalDateTime.parse(subString_deadline.split(" /by ")[1], timeFormat), false);
                    } catch (Exception e ) {
                        throw new WrongTimeFormatException(subString_deadline.split(" /by ")[1]);
                    }

                }

            case EVENT:
                String subString_event = cmd.substring(6);
                if (subString_event.split(" /at ").length == 1) {
                    throw new NoTimeException(instruction.name());

                } else {
                    try{
                        return new Event(subString_event.split(" /at ")[0],
                                LocalDateTime.parse(subString_event.split(" /at ")[1], timeFormat), false);
                    } catch (Exception e) {
                        throw new WrongTimeFormatException(subString_event.split(" /at ")[1]);
                    }

                }

            case PERIOD:
                String subString_period = cmd.substring(7);
                if (subString_period.split(" /from ").length == 1) {
                    throw new NoTimeException(instruction.name());

                } else {
                    try{
                        int endOfDescription = subString_period.indexOf("/from ");
                        int endOfStartTime = subString_period.indexOf("/to ");
                        String description = subString_period.substring(0, endOfDescription - 1);
                        String startTime = subString_period.substring(endOfDescription + 6, endOfStartTime - 1);
                        String endTime = subString_period.substring(endOfStartTime + 4);
                        LocalDateTime startDate =  LocalDateTime.parse(startTime, timeFormat);
                        LocalDateTime endDate = LocalDateTime.parse(endTime, timeFormat);
                        return new Period(description, startDate, endDate, false);
                    } catch (Exception e) {
                        throw new WrongTimeFormatException(subString_period.split(" /at ")[1]);
                    }

                }

            default:
                throw new NoCommandException(instruction.name());
            }
        }

        else {
            switch (instruction) {
                case TODO:
                    throw new NoDescriptionException(instruction.name());
                case DEADLINE:
                    throw new NoDescriptionException(instruction.name());
                case EVENT:
                    throw new NoDescriptionException(instruction.name());
                default:
                    throw new NoCommandException(instruction.name());
            }
        }
    }

    /**
     * Finds tasks by searching for a keyword.
     *
     * @param cmd
     * @param tasks
     * @return Find Object
     * @throws NoDescriptionException
     * @throws NoCommandException
     * @return Find task
     */
    public static Find find(String cmd, TaskList tasks) throws NoDescriptionException {
        int order = tasks.size();
        if (cmd.split(" ").length == 1) {
            throw new NoDescriptionException("Find");
        } else {
            return new Find(cmd.substring(5));
        }
    }

    /**
     * Parses the input in specific command
     *
     * @param cmd
     * @param tasks
     * @return Task object
     */
    public static Task parse(String cmd, TaskList tasks) {

        Operation operation;

        try {
            try {
                operation = Parser.Operation.valueOf(cmd.toUpperCase().split(" ")[0]);

            } catch (Exception e){
                throw new NoCommandException(cmd);
            }

            switch (operation) {
            case BYE:
                return new Exit();

            case LIST:
                return new List();

            case DONE:
                return done(cmd, tasks);

            case DELETE:
                return delete(cmd, tasks);

            case TODO:
                return addTask(cmd, tasks);

            case DEADLINE:
                return addTask(cmd, tasks);

            case EVENT:
                return addTask(cmd, tasks);

            case FIND:
                return find(cmd, tasks);

            case PERIOD:
                return addTask(cmd, tasks);

            default:
                throw new NoCommandException(cmd);

            }

        } catch (NoDescriptionException | EmptyTaskListException | NoCommandException | NoTimeException |
                DeleteWrongIndexException | WrongTimeFormatException e) {

            e.printStackTrace();
            return new Task("Invalid input", false);
        }
    }



}
