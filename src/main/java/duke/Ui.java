package duke;

import duke.exceptions.DukeEmptyTodoDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;

import java.util.ArrayList;

public class Ui {

    void printLines() {
        System.out.println("------------------------------------------------------------------");
    }

    void displayWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLines();
    }

    void displayByeMessage() {
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }

    void displayCommand(String description) {
        printLines();
        System.out.println(description);
        printLines();
    }

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

    void deleteTask(int taskIndex) {
        Tasklist.delete(taskIndex);
    }

    void displayTaskCompletion(Task toBeCompleted) {
        printLines();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(toBeCompleted);
        printLines();
    }


    void displayDukeList() {
        printLines();
        for (int i = 0;i < Tasklist.dukeList.size(); i++) {
            System.out.println((i+1) + ". " + Tasklist.dukeList.get(i));
        }
        printLines();
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
