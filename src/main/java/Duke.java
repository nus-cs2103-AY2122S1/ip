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
                // List all tasks in the task list.
                int i = 0;
                for (Task task : taskList) {
                    if (task != null) {
                        System.out.println(++i + "." + task.toString());
                    } else {
                        break;
                    }
                }

            } else if (userInput.startsWith("done ")) {
                // Mark a certain task as done.
                Task doneTask = taskList[Integer.parseInt(userInput.split(" ")[1]) - 1];
                doneTask.markDone();
                System.out.println("Nice! I've marked this task as done:\n" + "  " + doneTask.toString());

            } else {
                // Adds a Task to the task list.
                Task newTask;
                String substring = userInput.split(" ", 2)[1];
                if (userInput.startsWith("todo ")) {
                    newTask = new ToDo(substring);

                } else if (userInput.startsWith("deadline ")) {
                    String[] nameAndTime = substring.split(" /by ");
                    newTask = new Deadline(nameAndTime[0], nameAndTime[1]);

                } else {
                    String[] nameAndTime = substring.split(" /at ");
                    newTask = new Event(nameAndTime[0], nameAndTime[1]);
                }
                taskList[taskIndex] = newTask;
                taskIndex++;
                String taskCount = (taskIndex == 1) ? "1 task" : taskIndex + " tasks";
                System.out.println("Got it. I've added this task:\n" + "  " + newTask.toString() +
                        "\n" + "Now you have " + taskCount + " in the list.");
            }
        }
    }
}
