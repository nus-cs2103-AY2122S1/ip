package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with Interactions with the user.
 */
public class Ui {
    private boolean isExit;
    private static final String GREETING = "Hello! I'm Duck \n"
            + "*quack*  >(.)__\n"
            + "          (___/ \n"
            + "What can I do for you?\n";
    private static final String BYE = "Bye. Hope to see you again soon!\n"
            + "   __(.)>   *quack*\n"
            + "~~ \\___)\n";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.isExit = false;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showGreeting() {
        System.out.println(GREETING);
    }

    public void showBye() {
        this.scanner.close();
        this.isExit = true;
        System.out.println(BYE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError(String errorMessage) {
        System.out.println("OOPS!! An error has occurred: " + errorMessage);
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public void showAdded(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString());
        System.out.printf("Now you have %d tasks in your list.\n",
                numTasks);
    }

    public void showDeleted(Task deletedTask, int numTask) {
        System.out.println("Noted. I've removed this task: \n"
                + deletedTask.toString());
        System.out.printf("Now you have %d tasks in your list.\n",
                numTask);
    }

    public void showTaskDone(Task task) {
        System.out.println("This task has already been completed!\n"
                + task.toString());
    }

    public void showMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done: \n"
                + task.toString());
    }

    public void showList(TaskList tasks) {
        if (tasks.taskList.isEmpty()) {
            System.out.println("There are no duke.tasks on your list. *quack*");
        } else if (tasks.getLength() == 1) {
            System.out.println("There is one task on your list:");
            System.out.println("1. " + tasks.getTask(0).toString());
            System.out.println("*quack*");
        } else {
            System.out.println("Here are the duke.tasks on your list:");
            int i = 1;
            for (Task task : tasks.taskList) {
                System.out.println(i + ". " + task.toString());
                i++;
            }
            System.out.println("*quack*\n");
        }
    }

    public void showFindResults(ArrayList<Task> results) {
        if (results.isEmpty()) {
            System.out.println("Sorry, I did not find any task that matches your search *sad quack*\n");
        } else {
            System.out.println("Here are the tasks I found:");
            for (Task task : results) {
                System.out.println(task.toString());
            }
            System.out.println("*quack*\n");
        }
    }
}

/*
public static void main(String[] args) {
               
        boolean isActive = true;
        
        
        while (isActive) {
            try {
                String newUserInput = scanner.nextLine();
                String firstWord = newUserInput;
                if (newUserInput.contains(" ")) {
                    String[] splitString = newUserInput.split(" ", 2);
                    firstWord = splitString[0];
                    newUserInput = splitString[1];
                } else {
                    newUserInput = "";
                }
                switch (firstWord) {
                    case "bye":
                        isActive = false;
                        System.out.println(BYE);
                        break;
                    case "list":
                        if (duke.tasks.isEmpty()) {
                            System.out.println("There are no duke.tasks on your list. *quack*");
                        } else if (duke.tasks.size() == 1) {
                            System.out.println("There is one task on your list:");
                            System.out.println("1. " + duke.tasks.get(0).toString());
                            System.out.println("*quack*");
                        } else {
                            System.out.println("Here are the duke.tasks on your list:");
                            for (int i = 0; i < duke.tasks.size(); i++) {
                                System.out.println(i + 1 + ". " + duke.tasks.get(i).toString());
                            }
                            System.out.println("*quack*");
                        }
                        break;
                    case "done":
                        int taskNo = Integer.parseInt(newUserInput);
                        if (taskNo < 1 || taskNo > duke.tasks.size()) {
                            System.out.printf("Oops! No such task exists\n" +
                                            "Please use a number from 1 to %s\n",
                                    duke.tasks.size());
                        } else {
                            Task completedTask = duke.tasks.get(taskNo - 1);
                            completedTask.taskDone();
                            duke.tasks.set(taskNo - 1, completedTask);
                            System.out.println("Nice! I've marked this task as done: \n"
                                    + duke.tasks.get(taskNo - 1).toString());
                            rewriteTaskRecord(duke.tasks);
                        }
                        break;
                    case "todo":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        
                        Task newTask = new Todo(newUserInput);
                        duke.tasks.add(newTask);
                        appendTask(newTask);

                        
                        break;
                    case "deadline":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String description, dateTime;
                        if (newUserInput.contains("/")) {
                            String[] splitString = newUserInput.split(" /by ");
                            description = splitString[0];
                            dateTime = splitString[1];
                        } else {
                            description = newUserInput;
                            dateTime = "not specified";
                        }

                        newTask = new Deadline(description, dateTime);
                        duke.tasks.add(newTask);
                        appendTask(newTask);

                        System.out.println("Got it. I've added this task:\n"
                                + duke.tasks.get(duke.tasks.size() - 1).toString());
                        System.out.printf("Now you have %d duke.tasks in the list.\n",
                                duke.tasks.size());
                        break;
                    case "event":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        if (newUserInput.contains("/")) {
                            String[] splitString = newUserInput.split(" /at ");
                            description = splitString[0];
                            dateTime = splitString[1];
                        } else {
                            description = newUserInput;
                            dateTime = "not specified";
                        }

                        newTask = new Event(description, dateTime);
                        duke.tasks.add(newTask);
                        appendTask(newTask);
                        
                        System.out.println("Got it. I've added this task:\n"
                                + duke.tasks.get(duke.tasks.size() - 1).toString());
                        System.out.printf("Now you have %d duke.tasks in the list.\n",
                                duke.tasks.size());
                        break;
                    case "delete":
                        taskNo = Integer.parseInt(newUserInput);
                        if (taskNo < 1 || taskNo > duke.tasks.size()) {
                            System.out.printf(
                                    duke.tasks.size());
                        } else {
                            System.out.println("Noted. I've removed this task: \n"
                                    + duke.tasks.get(taskNo - 1).toString());
                            duke.tasks.remove(taskNo - 1);
                            System.out.printf("Now you have %d duke.tasks in the list.\n",
                                    duke.tasks.size());
                            rewriteTaskRecord(duke.tasks);
                        }
                        break;
                    default:
                        throw new InvalidInputException();

                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("OOPS! Please input date in this format: yyyy-mm-dd");
            }
        }
        scanner.close();
    }
 */