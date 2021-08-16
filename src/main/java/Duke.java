import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner;


    public static void main(String[] args) {
        System.out.println("Hello, I am Duke. What can I do for you?");
        scanner = new Scanner(System.in);
        waitResponse();
        scanner.close();
    }

    private static void waitResponse() {
        String command = Duke.scanner.next();
        String action = Duke.scanner.nextLine();
        handleRequest(command, action);
    }

    private static void handleRequest(String command, String action) {
        boolean end = false;

        switch (command) {
            case "todo":
                handleTodo(action);
                break;
            case "deadline":
                handleDeadline(action);
                break;
            case "event":
                handleEvent(action);
                break;
            case "list":
                handleList();
                break;
            case "done":
                handleDone(action);
                break;
            case "bye":
                handleBye();
                break;
        }
    }
    private static void handleTodo(String action) {
        Task newTask = new Todo(action.trim());
        tasks.add(newTask);
        System.out.println(String.format(
        "Got it. I've added this task: \n\t %s \nNow you have %d task in the list.",
        newTask, tasks.size()));
        waitResponse();
    }


    private static void handleDeadline(String action) {
        String[] deadlineInputs = action.trim().split("/by", 2);
        Task newTask = new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim());
        tasks.add(newTask);
        System.out.println(String.format(
        "Got it. I've added this task: \n  %s \nNow you have %d task in the list.",
        newTask, tasks.size()));
        waitResponse();
    }

    private static void handleEvent(String action) {
        String[] eventInputs = action.trim().split("/at", 2);
        Task newTask = new Event(eventInputs[0], eventInputs[1]);
        tasks.add(newTask);
        System.out.println(String.format(
        "Got it. I've added this task:\n\t %s\nNow you have %d task in the list.",
        newTask, tasks.size()));
        waitResponse();
    }

    private static void handleList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        waitResponse();
    }

    private static void handleBye() {
        System.out.println("Bye. Hope to see you again!");
    }

    private static void handleDone(String action) {
        int taskNumber = Integer.parseInt(action.trim());
        if (taskNumber <= tasks.size()) {
            Task taskToComplete = tasks.get(taskNumber - 1);
            taskToComplete.complete();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskToComplete);
        } else {
            System.out.println("This task does not exist! Use the list command to see your tasks.");
        }
        waitResponse();
    }


}


// if (command.equals("bye")) {
//         System.out.println("Bye. Hope to see you again!");
//         return;
//         } else if (command.equals("list")) {
//         System.out.println("Here are the tasks in your list:");
//         for (int i = 0; i < tasks.size(); i++) {
//        System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
//        }
//        waitResponse();
//        } else if (command.equals("done")) {
//        int taskNumber = Integer.parseInt(action.trim());
//        if (taskNumber <= tasks.size()) {
//        Task taskToComplete = tasks.get(taskNumber - 1);
//        taskToComplete.complete();
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(taskToComplete);
//        } else {
//        System.out.println("This task does not exist! Use the list command to see your tasks.");
//        }
//        waitResponse();
//        } else if (command.equals("todo")) {
//        Task newTask = new Todo(action.trim());
//        tasks.add(newTask);
//        System.out.println(String.format(
//        "Got it. I've added this task: \n\t %s \nNow you have %d task in the list.",
//        newTask, tasks.size()));
//        waitResponse();
//        } else if (command.equals("deadline")) {
//        String[] deadlineInputs = action.trim().split("/by", 2);
//        Task newTask = new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim());
//        tasks.add(newTask);
//        System.out.println(String.format(
//        "Got it. I've added this task: \n  %s \nNow you have %d task in the list.",
//        newTask, tasks.size()));
//        waitResponse();
//        } else if (command.equals("event")) {
//        String[] eventInputs = action.trim().split("/at", 2);
//        Task newTask = new Event(eventInputs[0], eventInputs[1]);
//        tasks.add(newTask);
//        System.out.println(String.format(
//        "Got it. I've added this task: \n\t %s \nNow you have %d task in the list.",
//        newTask, tasks.size()));
//        waitResponse();
//        } else {
//        System.out.println("Sorry! What is this command?");
//        waitResponse();
//        }