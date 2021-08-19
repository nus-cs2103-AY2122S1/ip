import java.util.ArrayList;
import java.util.Scanner;

import exceptions.EmptyDescriptionException;
import exceptions.InvalidInputException;
import exceptions.UserInputException;

public class Duke {
    public static void main(String[] args) {

        String GREETING = "Hello! I'm Duck \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can I do for you?";
        String BYE = "Bye. Hope to see you again soon!\n"
                + "   __(.)>   *quack*\n"
                + "~~ \\___)\n";

        ArrayList<Task> tasks = new ArrayList<>();
        boolean isActive = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while (isActive) {
            try {
                String newUserInput = scanner.nextLine();
                String firstWord = newUserInput;
                if (newUserInput.contains(" ")) {
                    String[] splitString = newUserInput.split(" ", 2);
                    firstWord = splitString[0];
                    newUserInput = splitString[1];
                }
                switch (firstWord) {
                    case "bye":
                        isActive = false;
                        System.out.println(BYE);
                        break;
                    case "list":
                        if (tasks.isEmpty()) {
                            System.out.println("There are no tasks on your list. *quack*");
                        } else if (tasks.size() == 1) {
                            System.out.println("There is one task on your list:");
                            System.out.println("1. " + tasks.get(0).toString());
                            System.out.println("*quack*");
                        } else {
                            System.out.println("Here are the tasks on your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println(i + 1 + ". " + tasks.get(i).toString());
                            }
                            System.out.println("*quack*");
                        }
                        break;
                    case "done":
                        int taskNo = Integer.parseInt(newUserInput);
                        if (taskNo < 1 || taskNo > tasks.size()) {
                            System.out.printf("Oops! No such task exists\n" +
                                            "Please use a number from 1 to %s",
                                    tasks.size());
                        } else {
                            Task completedTask = tasks.get(taskNo - 1);
                            completedTask.taskDone();
                            tasks.set(taskNo - 1, completedTask);
                            System.out.println("Nice! I've marked this task as done: \n"
                                    + tasks.get(taskNo - 1).toString());
                        }
                        break;
                    case "todo":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        tasks.add(new Todo(newUserInput));

                        System.out.println("Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n",
                                tasks.size());
                        break;
                    case "deadline":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        String description, dateTime;
                        if (newUserInput.contains("/")) {
                            String[] splitString = newUserInput.split("/by", 2);
                            description = splitString[0];
                            dateTime = splitString[1];
                        } else {
                            description = newUserInput;
                            dateTime = "not specified";
                        }
                        tasks.add(new Deadline(description, dateTime));
                        System.out.println("Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n",
                                tasks.size());
                        break;
                    case "event":
                        if (newUserInput.isEmpty()) {
                            throw new EmptyDescriptionException();
                        }
                        if (newUserInput.contains("/")) {
                            String[] splitString = newUserInput.split("/at", 2);
                            description = splitString[0];
                            dateTime = splitString[1];
                        } else {
                            description = newUserInput;
                            dateTime = "not specified";
                        }
                        tasks.add(new Event(description, dateTime));
                        System.out.println("Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1).toString());
                        System.out.printf("Now you have %d tasks in the list.\n",
                                tasks.size());
                        break;
                    case "delete":
                        taskNo = Integer.parseInt(newUserInput);
                        if (taskNo < 1 || taskNo > tasks.size()) {
                            System.out.println("Error: No such task exists");
                        } else {
                            System.out.println("Noted. I've removed this task: \n"
                                    + tasks.get(taskNo - 1).toString());
                            tasks.remove(taskNo - 1);
                            System.out.printf("Now you have %d tasks in the list.\n",
                                    tasks.size());
                        }
                        break;
                    default:
                        throw new InvalidInputException();

                }
            } catch (UserInputException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }


}



