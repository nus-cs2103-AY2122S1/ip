import java.util.Scanner;

public class Duke {
    
    public static void main (String[]args) {
        Scanner in = new Scanner(System.in);
        ListOfTasks xs = new ListOfTasks();
        CompilationOfFiles.loadAndSaveFile(xs);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String command = in.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            System.out.println("    ____________________________________________________________");
            try {
                if (command.equalsIgnoreCase("list")) {
                    xs.listOut();
                } else if (command.contains("done")) {
                    try {
                        if (command.equals("done ") || command.equals("done")) {
                            throw new DukeException("    OOPS!!! The task number cannot be empty.");
                        } else {
                            xs.isDone(command);
                        }
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.contains("deadline")) {
                    try {
                        if (command.equals("deadline ") || command.equals("deadline")) {
                            throw new DukeException("    OOPS!!! The deadline cannot be empty.");
                        } else {
                            xs.addDeadline(command);
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
                            xs.add(command);
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
            command = in.nextLine();
            }

    }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");

    }
}

