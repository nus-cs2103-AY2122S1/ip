package duke;

import java.time.format.DateTimeParseException;

/**
 * Class includes method for processing user commands.
 */
public class Parser {
    private static boolean isValidCommand;
    private static String message = "";
    public static boolean isValid() {
        return isValidCommand;
    }
    private static String helpMessage = "Quick help for actions you can perform: \n" +
            "1. Add todo using format “todo <task>” (e.g. todo math)\n" +
            "2. Add event using format “event <task> /at <time>” (e.g. event run /at Mon 2-4pm)\n" +
            "3. Add deadline using format “deadline <task> /by <date>” (e.g. deadline do work /by 2021-02-11)\n" +
            "4. Mark task as done with the task’s index using format “done <index of task>”  (e.g. done 2)\n" +
            "5. Delete task with the task’s index using format “delete <index of task>” (e.g. delete 2)\n" +
            "6. Find tasks similar to a keyword you entered using format “find <your input>” (e.g. find math)\n" +
            "7. View all tasks in task list using command “list”";

    /**
     * Handles commands given by user.
     *
     * @param command represents command given by user
     * @param tasks represents list of tasks
     * @return true if command can be handled
     * @throws DukeException if error occurs due to invalid command
     */
    public static String handleCommand(String command, ListOfTasks tasks) {
        while (!command.equalsIgnoreCase("bye")) {
            try {
                message = "";
                if (command.equalsIgnoreCase("list")) {
                    message = tasks.listOut();
                } else if (command.contains("done")) {
                    try {
                        if (command.equals("done ") || command.equals("done")) {
                            message = "OOPS!!! The task number "
                                    + "cannot be empty.";
                            throw new DukeException(
                                    "OOPS!!! The task number "
                                            + "cannot be empty.");
                        } else {
                            message = tasks.markDone(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("deadline")) {
                    try {
                        if (command.equals("deadline ")
                                || command.equals("deadline")) {
                            message = "OOPS!!! The deadline "
                                    + "cannot be empty.";
                            throw new DukeException("    OOPS!!! The deadline "
                                    + "cannot be empty.");
                        } else {
                            try {
                                message = tasks.addDeadline(command);
                            } catch (DateTimeParseException e) {
                                message = "OOPS!!! The date "
                                        + "for the deadline "
                                        + "should be written in the "
                                        + "form: yyyy-mm-dd.";
                                throw new DukeException("    OOPS!!! The date "
                                        + "for the deadline "
                                        + "should be written in the "
                                        + "form: yyyy-mm-dd.");
                            }
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("event")) {
                    try {
                        if (command.equals("event ")
                                || command.equals("event")) {
                            message = "OOPS!!! The "
                                    + "description of an event "
                                    + "cannot be empty.";
                            throw new DukeException("OOPS!!! The "
                                    + "description of an event "
                                    + "cannot be empty.");
                        } else {
                            message = tasks.addEvent(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("todo")) {
                    try {
                        if (command.equals("todo ") || command.equals("todo")) {
                            message = "OOPS!!! The "
                                    + "description of a todo "
                                    + "cannot be empty.";
                            throw new DukeException();
                        } else {
                            message = tasks.addTask(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("delete")) {
                    try {
                        if (command.equals("delete ")
                                || command.equals("delete")) {
                            message = "OOPS!!! The task "
                                    + "number cannot be empty.";
                            throw new DukeException("OOPS!!! The task "
                                    + "number cannot be empty.");
                        } else {
                            message = tasks.delete(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("find")) {
                    try {
                        if (command.equals("find ")
                                || command.equals("find")) {
                            message = "OOPS!!! The find "
                                    + "field cannot be empty.";
                            throw new DukeException("OOPS!!! The find "
                                    + "field cannot be empty.");
                        } else {
                            message = tasks.findSimilarTasks(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("help")) {
                    message = helpMessage;
                } else {
                    message = "OOPS!!! I'm sorry, but "
                            + "I don't know what that means :-(";
                    throw new DukeException("OOPS!!! I'm sorry, but "
                            + "I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                isValidCommand = false;
                return message;
            }
        }
        isValidCommand = true;
        return "Bye. Hope to see you again soon!";
    }

}
