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

    /**
     * This method handling commands given by user.
     *
     * @param command represents command given by user
     * @param tasks represents list of tasks
     * @return true if command can be handled
     * @throws DukeException if error occurs due to invalid command
     */
    public static String handleCommand(String command, ListOfTasks tasks) {
        while (!command.equalsIgnoreCase("bye")) {
            try {
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
