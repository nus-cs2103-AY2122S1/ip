import java.util.Scanner;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Duke {
    private static String introString = "Hey there! I'm Good Duke. How many I help you today?";
    private static String outroString = "That was an excellent chat - I look forward to seeing you again soon!";
    private static UnaryOperator<String> addedString = item ->
            String.format("Alright, I've added \"%s\" to my list :)", item);
    private static Function<Task, String> setDoneString = task ->
            String.format("Certainly, I've marked this task as done: \n\t%s", task);
    private static Scanner sc = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int taskIndex = 0;

    public static void printList() {
        for (int i = 0; i < taskIndex; i++) {
            String output = String.format("%d. %s", i + 1, taskList[i]);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        System.out.println(introString);
        label:
        while (true) {
            String userEntry = sc.nextLine();
            String[] commands = userEntry.split(" ");
            switch (commands[0]) {
                case "bye":
                    System.out.println(outroString);
                    break label;
                case "list":
                    printList();
                    break;
                case "done":
                    Task task = taskList[Integer.parseInt(commands[1]) - 1];
                    task.setDone(true);
                    System.out.println(setDoneString.apply(task));
                    break;
                default:
                    taskList[taskIndex] = new Task(userEntry);
                    taskIndex++;
                    System.out.println(addedString.apply(userEntry));
                    break;
            }
        }
    }
}
