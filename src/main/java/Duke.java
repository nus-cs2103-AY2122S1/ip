import java.util.Scanner;

enum TaskType {
    TODO, DEADLINE, EVENT
}

public class Duke {
    public static void main(String[] args) {

        String GREETING = "Hello! I'm Duck \n"
                + "*quack*  >(.)__\n"
                + "          (___/ \n"
                + "What can I do for you?";
        String BYE = "Bye. Hope to see you again soon!\n"
                + "   __(.)>   *quack*\n"
                + "~~ \\___)\n";

        Task[] tasks = new Task[100];
        int count = 0;
        boolean active = true;

        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        String newUserInput;

        while (active) {
            int startDescription, startDateTime;
            String description, dateTime;
            newUserInput = scanner.nextLine();
            String firstWord = newUserInput.contains(" ")
                    ? newUserInput.split(" ")[0]
                    : newUserInput;

            switch (firstWord) {
                case "bye":
                    active = false;
                    System.out.println(BYE);
                    break;
                case "list":
                    if (count == 0) {
                        System.out.println("There are no tasks on your list. *quack*");
                    } else if (count == 1) {
                        System.out.println("There is one task on your list:");
                        System.out.println("1. " + tasks[0].toString());
                        System.out.println("*quack*");

                    } else {
                        System.out.println("Here are the tasks on your list:");
                        for (int i = 0; i < count; i++) {
                            System.out.println(i + 1 + ". " + tasks[i].toString());
                        }
                        System.out.println("*quack*");
                    }
                    break;
                case "done":
                    int taskNo = Integer.parseInt(newUserInput.split(" ")[1]);
                    if (taskNo < 1 || taskNo > count) {
                        System.out.println("Error: No such task exists");
                    } else {
                        tasks[taskNo - 1].taskDone();
                        System.out.println("Nice! I've marked this task as done: \n"
                                + tasks[taskNo - 1].toString());
                    }
                    break;
                case "todo":
                    startDescription = newUserInput.indexOf(" ") + 1;
                    description = newUserInput.substring(startDescription);
                    tasks[count] = new Todo(description);
                    count++;
                    System.out.println("Got it. I've added this task:\n"
                            + tasks[count - 1].toString());
                    System.out.printf("Now you have %d tasks in the list.\n", count);
                    break;
                case "deadline":
                    startDescription = newUserInput.indexOf(" ") + 1;
                    startDateTime = newUserInput.indexOf("/") + 1;
                    description = newUserInput.substring(startDescription, startDateTime - 2);
                    dateTime = newUserInput.substring(startDateTime);
                    tasks[count] = new Deadline(description, dateTime);
                    count++;
                    System.out.println("Got it. I've added this task:\n"
                            + tasks[count - 1].toString());
                    System.out.printf("Now you have %d tasks in the list.\n", count);
                    break;
                case "event":
                    startDescription = newUserInput.indexOf(" ") + 1;
                    startDateTime = newUserInput.indexOf("/") + 1;
                    description = newUserInput.substring(startDescription, startDateTime - 2);
                    dateTime = newUserInput.substring(startDateTime);
                    tasks[count] = new Event(description, dateTime);
                    count++;
                    System.out.println("Got it. I've added this task:\n"
                            + tasks[count - 1].toString());
                    System.out.printf("Now you have %d tasks in the list.\n", count);
                    break;
                default:
                    System.out.println("Error: user input " + newUserInput);
            }
        }
        scanner.close();
    }


}



