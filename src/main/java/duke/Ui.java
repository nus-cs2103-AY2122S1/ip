package duke;

import duke.exceptions.DukeEmptyTodoDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;

/**
 * duke.Ui represents the Implementation of the interface of Duke.
 * Ui handles the displaying of messages and executing commands for
 * various tasks
 */
import java.util.ArrayList;

public class Ui {

    private final String LINES = "-------------------------------------------";

    void printLines() {
        System.out.println(LINES);
    }

    /**
     * Displays the welcome message to the user
     */
    void displayWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLines();
    }

    /**
     * Displays the farewell message to user when they enter bye.
     */
    void displayByeMessage() {
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }

    String showByeMessage() {
        StringBuilder str = new StringBuilder();
        str.append(LINES+"\n");
        str.append("Bye. Hope to see you again soon!\n");
        str.append(LINES+"\n");
        return str.toString();
    }

    void displayCommand(String description) {
        printLines();
        System.out.println(description);
        printLines();
    }


    String executeCommand(String command, String description, int i) throws DukeException {
        try {
            if (command.equals("list")) {
               return showDukeList();
            } else if (command.equals("done")) {
                int taskIndex = Integer.parseInt(description.substring(1)) - 1;
                Task toBeCompleted = Tasklist.dukeList.get(taskIndex);
                toBeCompleted.completeTask();
                return showTaskCompletion(toBeCompleted);
            } else {
                StringBuilder str = new StringBuilder();
                if (command.equals("deadline")) {
                    String[] description_and_time = Parser.splitDescriptionAndTime(description);
                    String deadlineDescription = Parser.getDescription(description_and_time);
                    String time = Parser.getTime(description_and_time);
                    Deadline newDeadline = new Deadline(deadlineDescription, time);
                    Tasklist.dukeList.add(newDeadline);
                    str.append(LINES+"\n");
                    str.append("Got it. I've added this task:\n");
                    str.append(newDeadline+"\n");
                } else if (command.equals("event")) {
                    String[] event_and_time = Parser.splitDescriptionAndTime(description);
                    String eventDescription = Parser.getDescription(event_and_time);
                    String time = Parser.getTime(event_and_time);
                    Event newEvent = new Event(eventDescription, time);
                    Tasklist.dukeList.add(newEvent);
                    str.append(LINES+"\n");
                    str.append("Got it. I've added this task:\n");
                    str.append(newEvent+"\n");
                } else if (command.equals("todo")) {
                    if (description.trim().equals("")) {
                        throw new DukeEmptyTodoDescriptionException();
                    }
                    str.append(LINES+"\n");
                    str.append("Got it. I've added this task:\n");
                    ToDo newTodo = new ToDo(description.substring(1));
                    Tasklist.add(newTodo);
                    str.append(newTodo+"\n");
                } else if (command.equals("delete")) {
                    int taskIndex = Integer.parseInt(description.substring(1)) - 1;
                    str.append(LINES+"\n");
                    str.append("Noted. I've removed this task:\n");
                    Task taskToBeDeleted = Tasklist.dukeList.get(taskIndex);
                    str.append(taskToBeDeleted+"\n");
                    deleteTask(taskIndex);
                } else if (command.equals("find")) {
                    String keyword = description.substring(1);
                    ArrayList<Task> foundTasks = Tasklist.find(keyword);
                    return showFoundDukeList(foundTasks);
                } else {
                    throw new DukeUnknownCommandException();
                }

                if (!command.equals("find")) {
                    str.append("Now you have " + Tasklist.dukeList.size() + " tasks in the list.\n");
                    str.append(LINES+"\n");
                }
                return str.toString();
            }
        } catch ( DukeEmptyTodoDescriptionException | DukeUnknownCommandException e) {
            StringBuilder str=  new StringBuilder();
            str.append(LINES+"\n");
            str.append(e.getMessage() + "\n");
            str.append(LINES+"\n");
            return str.toString();
        }
    }

    /**
     * Show the completion message for the Task marked done
     * @param toBeCompleted the Task which is to be marked done
     */
    String showTaskCompletion(Task toBeCompleted) {
        StringBuilder str = new StringBuilder();
        str.append(LINES+"\n");
        str.append("Nice! I've marked this task as done:\n");
        str.append(toBeCompleted+"\n");
        str.append(LINES+"\n");

        return str.toString();
    }

    String showDukeList() {
        StringBuilder str = new StringBuilder();
        str.append(LINES+"\n");
        for (int i = 0;i < Tasklist.dukeList.size(); i++) {
            str.append((i+1) + ". " + Tasklist.dukeList.get(i)+"\n");
        }
        str.append(LINES+"\n");

        return str.toString();
    }

    /**
     * Executes the commands entered by the user.
     * @param command        valid command entered by the user
     * @param description    description for the Task
     * @throws DukeException if the command is invalid or event has an empty description
     */
    void executeCommand(String command, String description)  throws DukeException {
        try {
            if (command.equals("list")) {
                displayDukeList();
            } else if (command.equals("done")) {
                int taskIndex = Integer.parseInt(description.substring(1)) - 1;
                Task toBeCompleted = Tasklist.dukeList.get(taskIndex);
                toBeCompleted.completeTask();
                displayTaskCompletion(toBeCompleted);
            } else {
                if (command.equals("deadline")) {
                    String[] description_and_time = Parser.splitDescriptionAndTime(description);
                    String deadlineDescription = Parser.getDescription(description_and_time);
                    String time = Parser.getTime(description_and_time);
                    Deadline newDeadline = new Deadline(deadlineDescription, time);
                    Tasklist.dukeList.add(newDeadline);
                    printLines();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline);
                } else if (command.equals("event")) {
                    String[] event_and_time = Parser.splitDescriptionAndTime(description);
                    String eventDescription = Parser.getDescription(event_and_time);
                    String time = Parser.getTime(event_and_time);
                    Event newEvent = new Event(eventDescription, time);
                    Tasklist.dukeList.add(newEvent);
                    printLines();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent);
                } else if (command.equals("todo")) {
                    if (description.trim().equals("")) {
                        throw new DukeEmptyTodoDescriptionException();
                    }
                    printLines();
                    System.out.println("Got it. I've added this task:");
                    ToDo newTodo = new ToDo(description.substring(1));
                    Tasklist.add(newTodo);
                    System.out.println(newTodo);
                } else if (command.equals("delete")) {
                    int taskIndex = Integer.parseInt(description.substring(1)) - 1;
                    printLines();
                    System.out.println("Noted. I've removed this task:");
                    Task taskToBeDeleted = Tasklist.dukeList.get(taskIndex);
                    System.out.println(taskToBeDeleted);
                    deleteTask(taskIndex);
                } else if (command.equals("find")) {
                    String keyword = description.substring(1);
                    ArrayList<Task> foundTasks = Tasklist.find(keyword);
                    displayFoundDukeList(foundTasks);
                } else {
                    throw new DukeUnknownCommandException();
                }

                if (!command.equals("find")) {
                    System.out.println("Now you have " + Tasklist.dukeList.size() + " tasks in the list.");
                    printLines();
                }
            }
        } catch ( DukeEmptyTodoDescriptionException | DukeUnknownCommandException e) {
            printLines();
            System.out.println(e.getMessage());
            printLines();
        }

    }

    /**
     * Remove a Task from the list
     * @param taskIndex the index of the Task to be removed
     */
    void deleteTask(int taskIndex) {
        Tasklist.delete(taskIndex);
    }

    /**
     * Display the completion message for the Task marked done
     * @param toBeCompleted the Task which is to be marked done
     */
    void displayTaskCompletion(Task toBeCompleted) {
        printLines();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(toBeCompleted);
        printLines();
    }

    /**
     * Display the tasks in the DukeList
     */
    void displayDukeList() {
        printLines();
        for (int i = 0;i < Tasklist.dukeList.size(); i++) {
            System.out.println((i+1) + ". " + Tasklist.dukeList.get(i));
        }
        printLines();
    }

    String showFoundDukeList(ArrayList<Task> foundTasks) {
        StringBuilder str = new StringBuilder();
        str.append(LINES+"\n");
        str.append("Here are the matching tasks in your list:\n");
        for (int i = 0;i < foundTasks.size(); i++) {
            str.append((i+1) + ". " + foundTasks.get(i) + "\n");
        }
        str.append(LINES+"\n");

        return str.toString();
    }

    void displayFoundDukeList(ArrayList<Task> foundTasks) {
        printLines();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0;i < foundTasks.size(); i++) {
            System.out.println((i+1) + ". " + foundTasks.get(i));
        }
        printLines();
    }
}
