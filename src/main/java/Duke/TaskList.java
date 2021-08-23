package Duke;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {

    private final List<Task> taskList;
    File file = new File("taskFile/taskList.txt");

    public TaskList() {
        this.taskList = new ArrayList<>();
        Storage.loadData(file, taskList);     // process data in the task list file
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
            Storage.saveData(file, taskList);     // mark any changes to the text file
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
        Storage.processInstructions();
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
        throw new DukeException("", DukeException.TYPE.SYNTAX_ERROR);
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
}
