import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER = "____________________________________________________________\n";

    private static ArrayList<Task> taskList;

    private static int numberOfTasks;

    private static void printStartMessage() {
        System.out.println(DIVIDER
                + LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + DIVIDER
        );
    }

    private static void printGoodbyeMessage() {
        System.out.println(DIVIDER
                + "Bye. Hope to see you again soon!\n"
                + DIVIDER
        );
    }

    private static void printTaskList() {
        System.out.print(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(String.format("%d.%s", i + 1, taskList.get(i)));
        }
        System.out.println(DIVIDER);
    }

    private static void finishTask(String[] commandAndArgument) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(commandAndArgument[1]) - 1;
            if (taskIndex >= numberOfTasks) {
                throw new DukeException("Please enter a valid task number.");
            } else {
                taskList.get(taskIndex).markAsDone();
                System.out.println(DIVIDER
                        + "Nice! I've marked this task as done:\n"
                        + taskList.get(taskIndex).toString() + '\n'
                        + DIVIDER
                );
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        }
    }

    private static void addTask(String[] taskTypeAndDetails) throws DukeException {
        if (taskTypeAndDetails.length < 2) {
            throw new DukeException("The description of a task cannot be empty.\n"
                    + "Please input your task in the following manner:\n"
                    + "todo|deadline|event <task_description>");
        }

        Task newTask;
        if (taskTypeAndDetails[0].equals("todo")) {
            newTask = new ToDo(taskTypeAndDetails[1]);
        } else if (taskTypeAndDetails[0].equals("deadline")) {
            String taskDetails = taskTypeAndDetails[1];
            String[] descriptionAndDateTime = taskDetails.split(" /by ", 2);
            if (descriptionAndDateTime.length < 2
                    || descriptionAndDateTime[0].strip().equals("")
                    || descriptionAndDateTime[1].strip().equals("")) {
                throw new DukeException("Invalid format for a deadline task.\n"
                        + "Please input your deadline task in the following manner:\n"
                        + "deadline <task_description> /by <task_deadline>");
            } else {
                newTask = new Deadline(descriptionAndDateTime[0], descriptionAndDateTime[1]);
            }
        } else if (taskTypeAndDetails[0].equals("event")) {
            String taskDetails = taskTypeAndDetails[1];
            String[] descriptionAndDateTime = taskDetails.split(" /at ", 2);
            if (descriptionAndDateTime.length < 2
                    || descriptionAndDateTime[0].strip().equals("")
                    || descriptionAndDateTime[1].strip().equals("")) {
                throw new DukeException("Invalid format for an event.\n"
                        + "Please input your event in the following manner:\n"
                        + "event <event_description> /at <event_date_or_time>");
            } else {
                newTask = new Event(descriptionAndDateTime[0], descriptionAndDateTime[1]);
            }
        } else {
            throw new DukeException("Invalid command. List of valid commands include:\n"
                    + "list|todo|deadline|event|done|bye");
        }
        taskList.add(newTask);
        numberOfTasks++;
        System.out.println(DIVIDER
                + "Got it. I've added this task:\n"
                + newTask + '\n'
                + "Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.\n"
                + DIVIDER
        );
    }

    public static void main(String[] args) {
        printStartMessage();

        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();
        numberOfTasks = 0;

        while (true) {
            try {
                String userInput = sc.nextLine();
                String[] commandAndArgument = userInput.split(" ", 2);
                String command = commandAndArgument[0];

                if (command.equals("bye")) {
                    printGoodbyeMessage();
                    sc.close();
                    break;
                } else if (command.equals("list")) {
                    printTaskList();
                } else if (command.equals("done")) {
                    finishTask(commandAndArgument);
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    addTask(commandAndArgument);
                } else {
                    throw new DukeException("Invalid command. List of valid commands include:\n"
                            + "list|todo|deadline|event|done|bye");
                }
            } catch (DukeException e) {
                System.out.println(DIVIDER
                        + e + '\n'
                        + DIVIDER
                );
            }
        }
    }
}
