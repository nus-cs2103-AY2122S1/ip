package Duke;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final List<Task> taskList;
    File file = new File("taskFile/taskList.txt");

    public Duke() {
        this.taskList = new ArrayList<>();
        loadData();     // process data in the task list file
    }

    public boolean isExitCommand(String command) {
        return (command.equals("bye") || command.equals("Bye") || command.equals("BYE"));
    }
    /**
     * Process incoming command of Duke - one for all method
     * @param command Command entered by user in Duke
     */
    public void processCommand(String command) {
        String[] divide = command.split(" ", 2);
        String commandType = divide[0];
        String description = (divide.length < 2) ? "" : divide[1];
        try {
            switch (commandType) {
                case "help":
                    respondHelp();
                    break;
                case "list":
                    respondList();
                    break;
                case "done":
                    respondDone(description);
                    break;
                case "todo":
                    respondTask(description, Task.Type.TODO);
                    break;
                case "deadline":
                    respondTask(description, Task.Type.DEADLINE);
                    break;
                case "event":
                    respondTask(description, Task.Type.EVENT);
                    break;
                case "delete":
                    respondDelete(description);
                    break;
                default:
                    defaultResponse();
            }
        } catch (DukeException e) {
            respondWith(e.getMessage());
        } catch (NumberFormatException e) {
            respondWith("☹ OOPS!!! Your description of '" + commandType + "' is incorrect!\n" +
                    "To use '" + commandType + "', please enter 'help' for instructions");
        } finally {
            saveData();     // mark any changes to the text file
        }
    }

    /**
     * Initializing Duke
     */
    public void gettingStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        respondWith("Hello! I'm Duke. \nType in 'help' if you are new to us.\nWhat can I do for you?");
    }

    /**
     * Exiting Duke
     */
    public void exitProgram() {
        System.out.println("\nProgram exiting... \nBye. Hope to see you again soon!\n");
    }

    /**
     * Getting helps with Duke command
     */
    private void respondHelp() {
        try {
            File file = new File("taskFile/instructions.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print all currently available tasks in Duke
     */
    private void respondList() {
        StringBuilder output = new StringBuilder("Here is the list of all tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        respondWith(output.toString());
    }

    /**
     * Mark a task by its index as completed.
     * All tasks are marked as completed if entered 'done all'
     * @param command Duke.Task command
     */
    private void respondDone(String command) throws DukeException {
        command = command.replaceAll("\\s+", "");
        if (command.equals("all")) {
            for (Task task : taskList) {
                task.markAsCompleted();
            }
            respondWith("Nice! I've marked all tasks in your list as done!");
        } else {
            int position = Integer.parseInt(command) - 1;
            if (position >= taskList.size() || position < 0) {
                throw new DukeException("", DukeException.TYPE.OUT_OF_BOUND);
            } else {
                Task calledTask = taskList.remove(position);
                calledTask.markAsCompleted();
                taskList.add(position, calledTask);
                respondWith("Nice! I've marked this task as done: \n" + calledTask);
            }
        }
    }

    /**
     * Delete a task inside the list by its index.
     * All tasks are deleted if entered 'delete all'
     * @param command Duke.Task command
     */
    public void respondDelete(String command) {
        command = command.replaceAll("\\s+", "");
        if (command.equals("")) {
            throw new DukeException("Error", DukeException.TYPE.INCOMPLETE);
        } else if (command.equals("all")) {
            taskList.clear();
            respondWith("Noted. I've reset your list and remove all tasks");
        } else {
            int position = Integer.parseInt(command) - 1;
            if (position >= taskList.size() || position < 0) {
                throw new DukeException("Error", DukeException.TYPE.OUT_OF_BOUND);
            } else {
                Task deletedTask = taskList.remove(position);
                respondWith("Noted. I've removed this task:\n" + deletedTask + "\n Now you have " +
                        taskList.size() + " tasks in the list");
            }
        }
    }

    /**
     * Adding a specified task to the list.
     * There are 3 types of task: TODO, DEADLINE, and EVENT
     * @param command Duke.Task command
     * @param type Duke.Task type
     */
    private void respondTask(String command, Task.Type type) {
        command = command.trim();
        if (command.equals("")) {
            throw new DukeException("Error", DukeException.TYPE.INCOMPLETE);
        } else {
            Task newTask = (type == Task.Type.TODO) ? new Todo(command)
                            : (type == Task.Type.DEADLINE) ? new Deadline(command)
                            : new Event(command);
            taskList.add(newTask);
            respondWith("Got it! I've added this task:\n" + newTask +
                    "\nNow you have " + taskList.size() + " tasks in the list");
        }
    }

    private void defaultResponse() {
        throw new DukeException("", DukeException.TYPE.INVALID);
    }
    /**
     * Template of respond after each command-reply cycle
     * @param input: Entered command on terminal
     */
    private void respondWith(String input) {
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(input);
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("Enter command: ");
    }

    public void saveData() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                writer.write(task + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Assuming that data are loaded correctly
     */
    public void loadData() {
        try {
            FileReader reader = new FileReader(file);
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                char type = (taskString.length() < 1) ? ' ' : taskString.trim().charAt(1);
                char status = (taskString.length() < 4) ? '?' : taskString.trim().charAt(4);
                String[] taskArray = taskString.split("] ", 2);
                String taskDescription = (taskArray.length < 2) ? "" : taskArray[1];
                Task curr;
                switch (type) {
                    case 'T':
                        taskDescription = taskDescription.trim();
                        curr = new Todo(taskDescription);
                        if (status == 'X') {
                            curr.markAsCompleted();
                        }
                        taskList.add(curr);
                        break;
                    case 'D':
                        taskDescription = taskDescription.trim().
                                replaceAll("\\(", "/").
                                replaceAll("\\)", "");
                        curr = new Deadline(taskDescription);
                        if (status == 'X') {
                            curr.markAsCompleted();
                        }
                        taskList.add(curr);
                        break;
                    case 'E':
                        taskDescription = taskDescription.
                                replaceAll("\\(", "/").
                                replaceAll("\\)", "");
                        curr = new Event(taskDescription);
                        if (status == 'X') {
                            curr.markAsCompleted();
                        }
                        taskList.add(curr);
                        break;
                    default:
                        break;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
