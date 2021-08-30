package duke;

import java.time.format.DateTimeParseException;

public class Parser {

    public static boolean handleCommand(String command, ListOfTasks xs) {
        while (!command.equalsIgnoreCase("bye")) {
            try {
                if (command.equalsIgnoreCase("list")) {
                    xs.listOut();
                } else if (command.contains("done")) {
                    try {
                        if (command.equals("done ") || command.equals("done")) {
                            throw new DukeException("    OOPS!!! The task number cannot be empty.");
                        } else {
                            xs.markDone(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("deadline")) {
                    try {
                        if (command.equals("deadline ") || command.equals("deadline")) {
                            throw new DukeException("    OOPS!!! The deadline cannot be empty.");
                        } else {
                            try {
                                xs.addDeadline(command);
                            } catch (DateTimeParseException e) {
                                throw new DukeException("    OOPS!!! The date for the deadline should be written in the form: yyyy-mm-dd.");
                            }
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("event")) {
                    try {
                        if (command.equals("event ") || command.equals("event")) {
                            throw new DukeException("    OOPS!!! The description of an event cannot be empty.");
                        } else {
                            xs.addEvent(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("todo")) {
                    try {
                        if (command.equals("todo ") || command.equals("todo")) {
                            throw new DukeException();
                        } else {
                            xs.addTask(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("delete")) {
                    try {
                        if (command.equals("delete ") || command.equals("delete")) {
                            throw new DukeException("    OOPS!!! The task number cannot be empty.");
                        } else {
                            xs.delete(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }  else {
                    throw new DukeException("    OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("    ____________________________________________________________");
                return false;
            }
        }
        return true;
    }

}