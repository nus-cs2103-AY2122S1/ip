import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void run() {
        this.greetUser();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
           receiveCommand(userInput);
           userInput = sc.nextLine();
        }
        this.exit();
    }

    public void printMessage(String message) {
        String formatDisplay = String.format("\t%s", message.replaceAll("\n", "\n\t"));
        System.out.println(formatDisplay);
    }

    public void greetUser() {
        String greetMessage = "Hello! I'm Saitama";
        String detailsMessage = "I do 100 sit-ups, 100 push-ups, 100 squats and a 10 kilometer run every day! No cap";
        printMessage(greetMessage);
        printMessage(detailsMessage);
    }

    public void receiveCommand(String command) {
        if (command.equals("list")) {
            this.displayTasks();
        } else if (command.matches("done \\d+")) {
            String taskNum = command.replaceAll("\\D+","");
            int index = Integer.parseInt(taskNum) - 1;
            this.markTask(index);
        } else {
            addTask(command);
        }
    }

    public void exit() {
        printMessage("Hope to see you again!! ^_^");
    }

    public void displayTasks() {
        int len = this.tasks.size();
        if (len == 0) {
            printMessage("You have no task!");
        } else {
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                Task task = this.tasks.get(i);
                printMessage(String.format("%d.%s", num, task));
            }
        }
    }

    public void markTask(int index) {
        try {
            Task task = this.tasks.get(index);
            task.MarkAsDone();
            printMessage(String.format("Nice! I've marked this task as done: \n\t%s", task));
        } catch (IndexOutOfBoundsException e) {
            printMessage("There is no such task!");
        }
    }

    public void addTask(String command) {
        Task task;
        if (command.startsWith("todo")) {
            task = new Todo(command.replaceFirst("todo ", ""));
        } else if (command.startsWith("deadline")) {
            String taskInfo = command.replaceFirst("deadline ", "");
            int separator = taskInfo.indexOf(" /by ");
            String description = taskInfo.substring(0, separator);
            String by = taskInfo.substring(separator + 5);
            task = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            String taskInfo = command.replaceFirst("event ", "");
            int separator = taskInfo.indexOf(" /at ");
            String description = taskInfo.substring(0, separator);
            String at = taskInfo.substring(separator + 5);
            task = new Event(description, at);
        } else {
            task = new Task(command);
        }
        tasks.add(task);
        printMessage(String.format("Got it. I've added this task:"
                + "\n\t%s"
                + "\nNow you have %d tasks in the list.", task, this.tasks.size()));
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
