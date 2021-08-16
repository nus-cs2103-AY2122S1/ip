import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "     ________________________________________\n"; // 5 spaces, 40 dashes
    private static final String INDENT = "     "; // 5 spaces
    private List<Task> taskList;
    private enum taskKind{
        TODOS,
        DEADLINES,
        EVENTS
    }
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
            String[] slashSplitInput = userInput.split("/", 2);
            String[] spaceSplitInput = slashSplitInput[0].split(" ", 2);

            if (userInput.equals("bye")) {
                // Exit
                System.out.println(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
                isRunning = false;
            } else if (userInput.equals("list")){
                // List all existing tasks
                System.out.println(LINE + INDENT + "Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    System.out.println(INDENT + (i+1) + "." + currTask.toString());
                }
                System.out.println(LINE);
            } else if (spaceSplitInput[0].equals("done")) {
                // Mark task as done
                try {
                    int doneTaskNo = Integer.parseInt(spaceSplitInput[1]);
                    if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
                        // Handle error if doneTaskNo out of range
                        System.out.println(LINE + INDENT + "Task number entered out of range!\n" + LINE);
                        continue;
                    }
                    Task doneTask = taskList.get(doneTaskNo - 1);
                    doneTask.markAsDone();
                    System.out.println(LINE + INDENT + "Nice! I've marked this task as done:\n"
                        + INDENT + INDENT + doneTask.toString() + "\n"
                        + LINE);
                }
                catch (NumberFormatException e) {
                    // Handle error if parseInt finds a non-valid entry
                    System.out.println(LINE + INDENT + "Please enter a valid integer for task number!\n" + LINE);
                    continue;
                }
            } else if (spaceSplitInput[0].equals("todo")) {
                addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.TODOS);
            } else if (spaceSplitInput[0].equals("deadline")) {
                addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.DEADLINES);
            }else if (spaceSplitInput[0].equals("event")) {
                addTask(spaceSplitInput, slashSplitInput, taskList, taskKind.EVENTS);
            }
        }
    }

    private void addTask(String[] spaceSplitInput, String[] slashSplitInput, List<Task> taskList, taskKind currTaskKind) {
        Task currTask = null;
        switch (currTaskKind) {
            case TODOS:
                currTask = new ToDos(spaceSplitInput[1]);
                break;
            case DEADLINES:
                // Extract taskTime by discarding the "by"
                String taskTimeDDL = slashSplitInput[1].split(" ", 2)[1];
                currTask = new Deadlines(spaceSplitInput[1], taskTimeDDL);
                break;
            case EVENTS:
                String taskTimeEvent = slashSplitInput[1].split(" ", 2)[1];
                currTask = new Events(spaceSplitInput[1], taskTimeEvent);
                break;
        }

        taskList.add(currTask);
        System.out.println(LINE + INDENT + "Got it. I've added this task:\n"
            + INDENT + INDENT + currTask.toString() + "\n"
            + INDENT + String.format("Now you have %d tasks in the list.\n", taskList.size())
            + LINE);
    }
}
