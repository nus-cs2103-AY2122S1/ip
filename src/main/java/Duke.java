import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static String line = "____________________________________________________________";
    private static String indent = "    ";

    enum Commands {
        LIST("list"),
        DONE("done"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        BYE("bye"),
        INVALID("invalid");

        private String c;
        Commands(String c) {
            this.c = c;
        }

        public static Commands fromString(String text) {
            for (Commands cmd : Commands.values()) {
                if (cmd.c.equalsIgnoreCase(text)) {
                    return cmd;
                }
            }
            return Commands.INVALID;
        }
    }

    private static void toScreen(String... msgs) {
        System.out.println(indent + line);
        for (String msg : msgs) {
            System.out.println(indent + msg);
        }
        System.out.println(indent + line);
    }

    private static void parser(String cmd, String remainder) {
        Commands c = Commands.fromString(cmd);
        switch (c) {
            case LIST:

        }
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);

        toScreen("Hello, I'm Duke!", "How can I help you?");
        String in = sc.nextLine();
        int space = in.indexOf(' ');
        String cmd = space > 0 ? in.substring(0, space) : in;
        String remainder = space > 0 ? in.substring(space + 1) : in;
        Commands c = Commands.fromString(cmd);

        while(!c.equals(Commands.BYE)) {
            switch (c) {
                case LIST:
                    //display tasklist
                    if (taskList.size() == 0) {
                        toScreen("You currently have no tasks.");
                        break;
                    }
                    System.out.println(indent + line + "\n" +
                            indent + "Here are the tasks in your list: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(indent + indent + (i + 1) + "." + taskList.get(i).toString());
                    }

                    System.out.println(indent + line);
                    break;
                case DONE:
                    //mark task as done
                    try {
                        int finishedTaskIndex = Integer.parseInt(remainder);
                        Task finishedTask = taskList.get(finishedTaskIndex - 1);
                        finishedTask.markAsDone();
                        toScreen("Nice! I've marked the following task as done: ",
                                indent + finishedTask.toString());
                    } catch (IndexOutOfBoundsException e) {
                        toScreen("Task could not be marked as done.", "Please input valid task index.");
                    }
                    break;
                case DELETE:
                    //delete task
                    try {
                        int deletedTaskIndex = Integer.parseInt(remainder);
                        Task deletedTask = taskList.get(deletedTaskIndex - 1);
                        taskList.remove(deletedTaskIndex - 1);
                        toScreen("Ok, I've deleted the following task: ",
                                indent + deletedTask.toString());
                    } catch (IndexOutOfBoundsException e) {
                        toScreen("Task could not be deleted.", "Please input valid task index.");
                    }
                    break;
                case TODO:
                    //add Todo task
                    try {
                        if (cmd == in) {
                            throw new DukeException("oops");
                        }
                        Task temp = new Todo(remainder);
                        taskList.add(temp);
                        toScreen("Ok! A new task has been added:",
                                indent + temp.toString(),
                                "You now have " + taskList.size() + " task(s) in total.");
                    } catch (DukeException de) {
                        toScreen("Sorry, the Todo task could not be added.",
                                "Please include the description for this task.");
                    }
                    break;
                case DEADLINE:
                    //add Deadline task
                    try {
                        int slash = remainder.indexOf("/by");
                        if (slash < 0 || cmd == in) {
                            throw new DukeException("oops");
                        }
                        String actionName = remainder.substring(0, slash);
                        String time = remainder.substring(slash + 4);
                        Task temp = new Deadline(actionName, time);
                        taskList.add(temp);
                        toScreen("Ok! A new task has been added:",
                                indent + temp.toString(),
                                "You now have " + taskList.size() + " task(s) in total.");
                    } catch (DukeException de) {
                        toScreen("Sorry, the Deadline task could not be added.",
                                "Please include the description and deadline for this task with /by.");
                    }
                    break;
                case EVENT:
                    //add Event task
                    try {
                        int slash = remainder.indexOf("/at");
                        if (slash < 0 || cmd == in) {
                            throw new DukeException("oops");
                        }
                        String actionName = remainder.substring(0, slash);
                        String time = remainder.substring(slash + 4);
                        Task temp = new Event(actionName, time);
                        taskList.add(temp);
                        toScreen("Ok! A new task has been added:",
                                indent + temp.toString(),
                                "You now have " + taskList.size() + " task(s) in total.");
                    } catch (DukeException de) {
                        toScreen("Sorry, the Event task could not be added.",
                                "Please include the description and time of this task with /at.");
                    }
                    break;
                case INVALID:
                    //invalid user input
                    toScreen("I'm sorry, I don't understand. Please try again.");
                    break;
            }

            /*
            if (cmd.equalsIgnoreCase("list")) {
                //display tasklist
                System.out.println(indent + line + "\n" +
                        indent + "Here are the tasks in your list: ");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(indent + indent + (i + 1) + "." + taskList.get(i).toString());
                }
                System.out.println(indent + line);
            } else if (cmd.equalsIgnoreCase("done")) {
                //mark task as done
                try {
                    int finishedTaskIndex = Integer.parseInt(remainder);
                    Task finishedTask = taskList.get(finishedTaskIndex - 1);
                    finishedTask.markAsDone();
                    toScreen("Nice! I've marked the following task as done: ",
                            finishedTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    toScreen("Task could not be marked as done.", "Please input valid task index.");
                }
            } else if (cmd.equalsIgnoreCase("delete")) {
                //delete task
                try {
                    int deletedTaskIndex = Integer.parseInt(remainder);
                    Task deletedTask = taskList.get(deletedTaskIndex - 1);
                    taskList.remove(deletedTaskIndex - 1);
                    toScreen("Ok, I've deleted the following task: ",
                            deletedTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    toScreen("Task could not be deleted.", "Please input valid task index.");
                }
            } else if (cmd.equalsIgnoreCase("todo")) {
                //add Todo task
                try {
                    if (cmd == in) {
                        throw new DukeException("oops");
                    }
                    Task temp = new Todo(remainder);
                    taskList.add(temp);
                    toScreen("Ok! A new task has been added:",
                            indent + temp.toString(),
                            "You now have " + taskList.size() + " task(s) in total.");
                } catch (DukeException de) {
                    toScreen("Sorry, the Todo task could not be added.",
                            "Please include the description for this task.");
                }
            } else if (cmd.equalsIgnoreCase("deadline")) {
                //add Deadline task
                try {
                    int slash = remainder.indexOf("/by");
                    if (slash < 0 || cmd == in) {
                        throw new DukeException("oops");
                    }
                    String actionName = remainder.substring(0, slash);
                    String time = remainder.substring(slash + 4);
                    Task temp = new Deadline(actionName, time);
                    taskList.add(temp);
                    toScreen("Ok! A new task has been added:",
                            indent + temp.toString(),
                            "You now have " + taskList.size() + " task(s) in total.");
                } catch (DukeException de) {
                    toScreen("Sorry, the Deadline task could not be added.",
                            "Please include the description and deadline for this task with /by.");
                }
            } else if (cmd.equalsIgnoreCase("event")) {
                //add Event task
                try {
                    int slash = remainder.indexOf("/at");
                    if (slash < 0 || cmd == in) {
                        throw new DukeException("oops");
                    }
                    String actionName = remainder.substring(0, slash);
                    String time = remainder.substring(slash + 4);
                    Task temp = new Event(actionName, time);
                    taskList.add(temp);
                    toScreen("Ok! A new task has been added:",
                            indent + temp.toString(),
                            "You now have " + taskList.size() + " task(s) in total.");
                } catch (DukeException de) {
                    toScreen("Sorry, the Event task could not be added.",
                            "Please include the description and time of this task with /at.");
                }
            } else {
                //invalid user input
                toScreen("I'm sorry, I don't understand. Please try again.");
            }*/
            //get next input
            in = sc.nextLine();
            space = in.indexOf(' ');
            cmd = space > 0 ? in.substring(0, space) : in;
            remainder = space > 0 ? in.substring(space + 1) : in;
            c = Commands.fromString(cmd);
        }
        //end program
        toScreen("Bye. Hope to see you again soon!");
    }
}
