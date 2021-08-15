import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "     ________________________________________\n"; // 5 spaces, 40 dashes
    private static final String INDENT = "     "; // 5 spaces
    private List<Task> taskList;
    private int taskNo;
    private boolean isRunning;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.greet();
        duke.converse();
    }

    public Duke() {
        taskList = new ArrayList<>(100);
        taskNo = 0;
        isRunning = true;
    }

    public void greet() {
        String greeting = LINE
            + INDENT + "Hello! I'm Duke\n"
            + INDENT + "What can I do for you?\n"
            + LINE;
        System.out.println(greeting);
    }

    public void converse() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning){
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ", 2);

            if (userInput.equals("bye")) {
                // Exit
                System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
                isRunning = false;
            } else if (userInput.equals("list")){
                // List all existing tasks
                System.out.println(LINE + INDENT + "Here are the tasks in your list:");
                for (int i = 0; i < taskNo; i++) {
                    Task currentTask = taskList.get(i);
                    System.out.println(INDENT + (i+1) + String.format(".[%s] %s", currentTask.getStatusIcon(), currentTask.description));
                }
                System.out.println(LINE);
            } else if (splitInput[0].equals("done")) {
                // Mark task as done
                try {
                    int doneTaskNo = Integer.parseInt(splitInput[1]);
                    if (doneTaskNo < 1 || doneTaskNo > taskNo) {
                        // Handle error if doneTaskNo out of range
                        System.out.println(LINE + INDENT + "Task number entered out of range!\n" + LINE);
                        continue;
                    }
                    Task doneTask = taskList.get(doneTaskNo - 1);
                    doneTask.markAsDone();
                    System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
                        + INDENT + INDENT + String.format("[%s] %s\n", doneTask.getStatusIcon(), doneTask.description)
                        + LINE);
                }
                catch (NumberFormatException e) {
                    // Handle error if parseInt finds a non-valid entry
                    System.out.println(LINE + INDENT + "Please enter a valid integer for task number!\n" + LINE);
                    continue;
                }
            } else {
                // Add task to list
                taskList.add(new Task(userInput));
                taskNo += 1;
                System.out.println(LINE + INDENT + "added: " + userInput + "\n" + LINE);
            }
        }
    }
}
