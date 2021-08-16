import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public String getDescription() {
            return description;
        }

    }

    public static ArrayList<Task> itemList = new ArrayList<Task>();

    public static void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    public static String getFirstWord(String input) {
        String arr[] = input.split(" ", 2);

        String firstWord = arr[0];
        return firstWord;
    }

    public static Integer getTaskNumber(String input) {
        String arr[] = input.split(" ", 2);

        Integer taskNumber = Integer.parseInt(arr[1]);
        return taskNumber;
    }

    public static void addTask(String input) {
        Task t = new Task(input);
        itemList.add(t);
        System.out.println("added:" + " " + input);
        start();
    }

    public static void markTaskDone(String input) {
        //get which task number is completed
        int taskDoneNum = getTaskNumber(input);
        Task taskDone = itemList.get(taskDoneNum - 1);
        taskDone.markAsDone();
        System.out.println("Nice! I've marked this task as done: " + '\n' + "[" + taskDone.getStatusIcon() + "] " + taskDone.getDescription());
    }

    public static void printItemList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i + 1 + "." + "[" + itemList.get(i).getStatusIcon() + "] " + itemList.get(i).getDescription());
        }
    }

    public static void start() {

        String exit = "bye";
        String input;
        String lineBreak = "========================================================================";
        String items = "list";

        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        System.out.println(lineBreak);

        if (input.equals(exit)) {
            itemList.clear();
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals(items)) {
            printItemList();
        } else {
            String check = getFirstWord(input);

            if (check.equals("done")) {
                markTaskDone(input);
            } else {
                addTask(input);
            }
        }
        System.out.println(lineBreak);
        start();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        start();
    }
}
