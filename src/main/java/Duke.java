import java.util.Scanner;

public class Duke {
    private static String introString = "Hey there! I'm Good Duke. How many I help you today?";
    private static String outroString = "That was an excellent chat - I look forward to seeing you again soon!";

    private static Scanner sc = new Scanner(System.in);
    private static Task[] taskList = new Task[100];
    private static int taskIndex = 0;

    public static String taskListString() {
        String output = "";
        for (int i = 0; i < taskIndex; i++) {
            output += String.format("%d. %s\n", i + 1, taskList[i]);
        }
        return output;
    }

    public static String addedString(Task task) {
        return String.format("Alright, I've added this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, taskIndex);
    }

    public static String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n\t%s\n", task);
    }

    public static void print(String str) {
        String horizontalLine = "________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);

    }

    public static void main(String[] args) {
        print(introString);
        label:
        while (true) {
            String userEntry = sc.nextLine();
            String[] commands = userEntry.split(" ");
            String description;
            String descriptionAndTime;
            String time;
            Task task;
            switch (commands[0]) {
                case "bye":
                    print(outroString);
                    break label;
                case "list":
                    print(taskListString());
                    break;
                case "done":
                    task = taskList[Integer.parseInt(commands[1]) - 1];
                    task.setDone(true);
                    print(doneString(task));
                    break;
                case "todo":
                    description = userEntry.substring(5);
                    task = new ToDo(description);
                    taskList[taskIndex] = task;
                    taskIndex++;
                    print(addedString(task));
                    break;
                case "deadline":
                    descriptionAndTime = userEntry.substring(9);
                    description = descriptionAndTime.split("/by ")[0];
                    time = descriptionAndTime.split("/by ")[1];
                    task = new Deadline(description, time);
                    taskList[taskIndex] = task;
                    taskIndex++;
                    print(addedString(task));
                    break;
                case "event":
                    descriptionAndTime = userEntry.substring(6);
                    description = descriptionAndTime.split("/at ")[0];
                    time = descriptionAndTime.split("/at ")[1];
                    task = new Event(description, time);
                    taskList[taskIndex] = task;
                    taskIndex++;
                    print(addedString(task));
                    break;
            }
        }
    }
}
