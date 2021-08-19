import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    // Constant and Static Variable
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String greeting = "Hello! I am \n"
            + logo
            + "The awesome bot helper! \n"
            + "How can I help you today?";

    public static final String goodbye = "Bye. Hope to see you again soon!";

    enum taskType {ToDo, Event, Deadline}

    public static void main(String[] args) {

        reply(greeting);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        mainLoop:
        while (true) {
            String userInput = myObj.nextLine();
            try {
                String[] splitUserInput = userInput.split(" ", 2);
                String command = splitUserInput[0];
                switch (command) {
                    case "bye":
                        break mainLoop;

                    case "list":
                        if (splitUserInput.length > 1 ) {
                            throw new DukeExceptions("Oops, the list command cannot have any modifier or description");
                        }
                        listCommand();
                        break;

                    case "delete":
                        try {
                            String index = splitUserInput[1];
                            deleteCommand(index);
                        } catch (ArrayIndexOutOfBoundsException e){
                            reply("Oops, you forgot to tell me which item you want to delete!");
                        } finally {
                            break;
                        }

                    case "done":
                        try {
                            String index = splitUserInput[1];
                            doneCommand(index);
                        } catch (ArrayIndexOutOfBoundsException e){
                            reply("Oops, you forgot to tell me which item you want to mark as done!");
                        } finally {
                            break;
                        }

                    case "event":
                        if (splitUserInput.length == 1 ) {
                            throw new DukeExceptions("Oops, + " +
                                    "you need to tell me the description and the time of the event");
                        }
                        taskCommand(taskType.Event, splitUserInput[1]);

                        break;
                    case "deadline":
                        if (splitUserInput.length == 1 ) {
                            throw new DukeExceptions("Oops, " +
                                    "you need to tell me the description of the task and the time of the deadline");
                        }
                        taskCommand(taskType.Deadline, splitUserInput[1]);

                        break;
                    case "todo":
                        if (splitUserInput.length == 1 ) {
                            throw new DukeExceptions("Oops, " +
                                    "you need to tell me the description of the task on hand");
                        }
                        taskCommand(taskType.ToDo, splitUserInput[1]);
                        break;

                    case "":
                        reply("Oops, looks like you forgot to put your command");

                    default:
                        reply("Oops.. I don't understand your command..");
                        break;
                }
            } catch (DukeExceptions e) {
                reply(e.getMessage());
            }
        }
        reply(goodbye);
    }

    public static void reply(String output) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(output);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void listCommand() {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list! \n");
        int index = 1;
        for (Task listedTask : taskList) {
            result.append(index + ". " + listedTask.toString() + '\n');
            index++;
        }
        reply(result.toString().stripTrailing());
    }

    public static void deleteCommand(String index) {
        try {
            int taskIndex = Integer.parseInt(index);
            if (taskIndex > taskList.size()) {
                throw new DukeExceptions ("Oops, the list is not that big :/");
            }
            Task task = taskList.get(taskIndex - 1);
            String output = "Alright! I deleted the following task as finished: \n"
                    + "    " + task.toString();
            taskList.remove(taskIndex - 1);
            reply(output);
        } catch (NumberFormatException e) {
            reply("Oops, delete command can only take a single integer as the argument!");
        } catch (DukeExceptions e) {
            reply(e.getMessage());
        }
    }

    public static void doneCommand(String index) {
        try {
            int taskIndex = Integer.parseInt(index);
            if (taskIndex > taskList.size()) {
                throw new DukeExceptions ("Oops, the list is not that big :/");
            }
            Task task = taskList.get(taskIndex - 1);
            task.markFinished();
            reply("Well done! I marked the following task as finished: \n"
                    + "    " + task.toString());
        } catch (NumberFormatException e) {
            reply("Oops, done command can only take a single integer as the argument!");
        } catch (DukeExceptions e) {
            reply(e.getMessage());
        }
    }

    public static void taskCommand(taskType type, String body) {
        try {
            Task task;
            switch (type) {
                case Event:
                    try {
                        String[] splitBody = body.split("/at", 2);
                        if (splitBody.length == 1 || splitBody[1].strip().equals("")) {
                            throw new DukeExceptions ("Oops! I need to know when the deadline is. " +
                                    "Use the /by argument please");
                        }
                        String desc = splitBody[0];
                        String time = splitBody[1];
                        task = Event.create(desc, time);
                        taskList.add(task);
                        reply("Got it! I've added this event to the list: \n"
                                + "  " + task.toString() + '\n'
                                + String.format("Now you have %d tasks in the list", taskList.size()));
                    } catch (DukeExceptions e) {
                        reply(e.getMessage());
                    } finally {
                        break;
                    }


                case ToDo:
                    task = ToDo.create(body);
                    taskList.add(task);
                    reply("Got it! I've added this task to do: \n"
                            + "  " + task.toString() + '\n'
                            + String.format("Now you have %d tasks in the list", taskList.size()));
                    break;

                case Deadline:
                    try {
                        String[] splitBody = body.split("/by", 2);
                        if (splitBody.length == 1 || splitBody[1].strip().equals("")) {
                            throw new DukeExceptions ("Oops! I need to know when the deadline is. " +
                                    "Use the /by argument please");
                        }
                        String desc = splitBody[0];
                        String time = splitBody[1];
                        task = Deadline.create(desc, time);
                        taskList.add(task);
                        reply("Got it! I've added this time sensitive task: \n"
                                + "  " + task.toString() + '\n'
                                + String.format("Now you have %d tasks in the list", taskList.size()));
                    } catch (DukeExceptions e) {
                        reply(e.getMessage());
                    } finally {
                        break;
                    }

            }
        } catch (DukeExceptions e) {
            reply(e.getMessage());
        }
    }
}
