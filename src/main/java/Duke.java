import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void lineSpacing() {
        System.out.println("____________________________________________________________");
    }

    private static void displayAddedTask(Task currentTask) {
        lineSpacing();
        System.out.println("Got it. I've added this task: ");
        System.out.println(currentTask);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
        lineSpacing();
    }

    private static void printList() {
        lineSpacing();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println(String.format("%d.%s", i + 1, currentTask));
        }
        lineSpacing();
    }

    private static void doneTask(String userInput) {
        String[] inputArray = userInput.split(" ");
        Task completedTask = taskList.get(Integer.parseInt(inputArray[1]) - 1);
        completedTask.markAsDone();
        lineSpacing();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(completedTask);
        lineSpacing();
    }

    private static void addDeadline(String userInput) {
        List<String> inputArray = Arrays.asList(userInput.split(" /by "));
        String by = inputArray.get(1);
        String description = String.join(" ",
                new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" "))).remove(0));
        Deadline newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        displayAddedTask(newDeadline);
    }

    private static void addEvent(String userInput) {
        List<String> inputArray = Arrays.asList(userInput.split(" /at "));
        String timeFrame = inputArray.get(1);
        String description = String.join(" ",
                new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" "))).remove(0));
        Event newEvent = new Event(description, timeFrame);
        taskList.add(newEvent);
        displayAddedTask(newEvent);
    }

    private static void addTodo(String userInput) {
        List<String>  inputArray = Arrays.asList(userInput.split(" "));
        String description = String.join(" ", new ArrayList<String>(inputArray).remove(0));
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        displayAddedTask(newTodo);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                lineSpacing();
                System.out.println("Bye. Hope to see you again soon!");
                lineSpacing();
                break;
            }

           if (userInput.equals("list")) {
                printList();
                continue;
            }

           if (userInput.startsWith("done ")) {
               doneTask(userInput);
               continue;
           }

           if (userInput.startsWith("deadline ")) {
               addDeadline(userInput);
               continue;
           }

            if (userInput.startsWith("event ")) {
                addEvent(userInput);
                continue;
            }

            if (userInput.startsWith(("todo "))) {
                addTodo(userInput);
                continue;
            }
        }
    }

}
