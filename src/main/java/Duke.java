import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static final Path DATA_DIRECTORY_PATH = Paths.get("data");

    private static final Path SAVE_FILE_PATH = Paths.get("data", "duke.txt");

    private static final File SAVE_FILE = SAVE_FILE_PATH.toFile();

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

    private static void loadSave() throws DukeException {
        try {
            if (!Files.exists(DATA_DIRECTORY_PATH)) {
                Files.createDirectory(DATA_DIRECTORY_PATH);
            }
            if (!Files.exists(SAVE_FILE_PATH)) {
                Files.createFile(SAVE_FILE_PATH);
            }

            Scanner sc = new Scanner(SAVE_FILE);
            while (sc.hasNext()) {
                String[] taskInfo = sc.nextLine().split("\\|");
                String taskType = taskInfo[0];
                boolean isDone = taskInfo[1].equals("1");
                String taskDescription = taskInfo[2];
                Task task;
                switch (taskType) {
                case "T":
                    task = new ToDo(taskDescription, isDone);
                    break;
                case "D":
                    task = new Deadline(taskDescription, isDone, taskInfo[3]);
                    break;
                case "E":
                    task = new Event(taskDescription, isDone, taskInfo[3]);
                    break;
                default:
                    throw new DukeException("Oops! Your save file is corrupted and has an invalid format.");
                }
                taskList.add(task);
                numberOfTasks++;
            }
            sc.close();
        } catch (IOException e) {
            throw new DukeException("Oops! There was an error creating/retrieving a save file for Duke.");
        }
    }

    private static String getSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            sb.append(taskList.get(i).getSaveFormat());
        }
        return sb.toString();
    }

    private static void modifySave(String text, boolean isOverwrite) throws DukeException {
        try {
            FileWriter fw;
            if (isOverwrite) {
                fw = new FileWriter(SAVE_FILE);
            } else {
                fw = new FileWriter(SAVE_FILE, true);
            }
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Oops! There was an error accessing your save file.");
        }
    }

    private static void editTask(String[] commandAndArgument) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(commandAndArgument[1]) - 1;
            if (commandAndArgument[0].equals("done")) {
                taskList.get(taskIndex).markAsDone();
                System.out.println(DIVIDER
                        + "Nice! I've marked this task as done:\n"
                        + taskList.get(taskIndex).toString() + '\n'
                        + DIVIDER
                );
            } else {
                Task removedTask = taskList.remove(taskIndex);
                numberOfTasks--;
                System.out.println(DIVIDER
                        + "Got it! I've removed this task from the list:\n"
                        + removedTask.toString() + '\n'
                        + "Now you have "
                        + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.\n"
                        + DIVIDER
                );
            }
            modifySave(getSaveFormat(), true);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
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
        modifySave(newTask.getSaveFormat(), false);
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

        loadSave();

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
                } else if (command.equals("done") || command.equals("delete")) {
                    editTask(commandAndArgument);
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    addTask(commandAndArgument);
                } else {
                    throw new DukeException("Invalid command. List of valid commands include:\n"
                            + "list|todo|deadline|event|done|delete|bye");
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
