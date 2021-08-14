import java.util.Date;
import java.util.Scanner;

public class Duke {

    private static String addTasks(List taskList, Task task) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                taskList.addToList(task), taskList.taskCount());
    }

    public static void runDuke() {
        boolean validInput = false;
        Scanner input = new Scanner(System.in);
        List taskList = new List(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (!validInput) {
            String userInput = input.nextLine();
            String firstWord = userInput;
            String restOfInput = " ";
            String date = " ";
            String description = " ";

            if (userInput.contains(" ")) {
                firstWord = userInput.split(" ",2)[0];
                restOfInput = userInput.split(" ",2)[1];

                if (restOfInput.contains("/by")) {
                    description = restOfInput.split("/by",2)[0];
                    date = restOfInput.split("/by",2)[1];
                } else if (restOfInput.contains("/at")) {
                    description = restOfInput.split("/at",2)[0];
                    date = restOfInput.split("/at",2)[1];
                }
            }

            switch (firstWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    validInput = true;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:\n" + taskList.getList());
                    break;
                case "done":
                    System.out.println("Nice! I've marked this task as done:\n" + taskList.taskDone(Integer.parseInt(restOfInput)));
                    break;
                case "todo":
                    Task currToDo = new ToDo(restOfInput);
                    System.out.println(addTasks(taskList, currToDo));
                    break;
                case "deadline":
                    Task currDeadline = new Deadline(description, date);
                    System.out.println(addTasks(taskList, currDeadline));
                    break;
                case "event":
                    Task currEvent = new Event(description, date);
                    System.out.println(addTasks(taskList, currEvent));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        runDuke();
    }
}
