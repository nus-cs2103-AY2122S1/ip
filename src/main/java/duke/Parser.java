package duke;

import java.time.format.DateTimeParseException;

/**
 * Class includes method for processing user commands.
 */
public class Parser {

    /**
     * This method handling commands given by user.
     *
     * @param command represents command given by user
     * @param tasks represents list of tasks
     * @return true if command can be handled
     * @throws DukeException if error occurs due to invalid command
     */
    public static boolean handleCommand(String command, ListOfTasks tasks) {
        while (!command.equalsIgnoreCase("bye")) {
            try {
                if (command.equalsIgnoreCase("list")) {
                    tasks.listOut();
                } else if (command.contains("done")) {
                    try {
                        if (command.equals("done ") || command.equals("done")) {
                            throw new DukeException(
                                    "    OOPS!!! The task number "
                                    + "cannot be empty.");
                        } else {
                            tasks.markDone(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("deadline")) {
                    try {
                        if (command.equals("deadline ")
                                || command.equals("deadline")) {
                            throw new DukeException("    OOPS!!! The deadline "
                                    + "cannot be empty.");
                        } else {
                            try {
                                tasks.addDeadline(command);
                            } catch (DateTimeParseException e) {
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
                            throw new DukeException("    OOPS!!! The "
                                    + "description of an event "
                                    + "cannot be empty.");
                        } else {
                            tasks.addEvent(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("todo")) {
                    try {
                        if (command.equals("todo ") || command.equals("todo")) {
                            throw new DukeException();
                        } else {
                            tasks.addTask(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("delete")) {
                    try {
                        if (command.equals("delete ")
                                || command.equals("delete")) {
                            throw new DukeException("    OOPS!!! The task "
                                    + "number cannot be empty.");
                        } else {
                            tasks.delete(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("find")) {
                    try {
                        if (command.equals("find ")
                                || command.equals("find")) {
                            throw new DukeException("    OOPS!!! The find "
                                    + "field cannot be empty.");
                        } else {
                            tasks.findSimilarTasks(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    throw new DukeException("    OOPS!!! I'm sorry, but "
                            + "I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("    __________________________"
                        + "__________________________________");
                return false;
            }
        }
        return true;
    }

}
