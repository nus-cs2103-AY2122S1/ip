package Duke.Tool;

import Duke.Exceptions.*;
import Duke.Tasks.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.lang.String;
import java.time.format.DateTimeFormatter;

public class Parser {

    private final static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static enum Operation {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    /**
     * the method isInteger to judge whether input is integer
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

    public static Task done(String cmd, TaskList task) throws EmptyTaskListException, NoDescriptionException, NoCommandException, IOException {

        int order = task.size();
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

    public static Task delete(String cmd, TaskList task) throws DeleteWrongIndexException, NoDescriptionException, NoCommandException, IOException {

        int order = task.size();
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


    public static Task addTask(String cmd, TaskList task) throws NoDescriptionException, NoTimeException,
            NoCommandException, IOException, WrongTimeFormatException {

        Parser.Operation instruction = Parser.Operation.valueOf(cmd.toUpperCase().split(" ")[0]);
        int order = task.size();
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

    public static Task parse(String cmd, TaskList task) {

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
                return done(cmd, task);

            case DELETE:
                return delete(cmd, task);

            case TODO:
                return addTask(cmd, task);

            case DEADLINE:
                return addTask(cmd, task);

            case EVENT:
                return addTask(cmd, task);

            default:
                throw new NoCommandException(cmd);

            }

        } catch (NoDescriptionException | EmptyTaskListException | NoCommandException | NoTimeException |
                DeleteWrongIndexException | IOException | WrongTimeFormatException e) {

            e.printStackTrace();
            return null;
        }
    }



}
