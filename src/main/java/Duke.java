import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean dukeOpen = true;
        Task[] taskList = new Task[100];
        int taskIndex = 0;

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        while (dukeOpen) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                dukeOpen = false;
                System.out.println("Bye. Hope to see you again soon!");

            } else if (userInput.equals("list")) {
                int i = 0;
                for (Task task : taskList) {
                    if (task != null) {
                        System.out.println(++i + "." + task.toString());
                    } else {
                        break;
                    }
                }

            } else if (userInput.startsWith("done ")) {
                Task doneTask = taskList[Integer.parseInt(userInput.split(" ")[1]) - 1];
                doneTask.markDone();
                System.out.println("Nice! I've marked this task as done: \n" + "  " + doneTask.toString());

            } else {
                taskList[taskIndex] = new Task(userInput);
                taskIndex++;
                System.out.println("added: " + userInput);
            }
        }
    }
}
