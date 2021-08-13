import java.util.Scanner;
import java.util.ArrayList;

public class Lifeline {
    private Scanner sc;
    private ArrayList<Task> taskList;
    private String command;

    Lifeline() {
        this.taskList = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    private void greet() {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        System.out.println("Hello! I am\n" + lifeline);
        System.out.println("What can I help you with today?\n");
    }

    private void getInput() {
        this.command = sc.nextLine();
        String[] commands = command.split("\\s", 2);
        System.out.println();
        switch (commands[0]) {
        case "list":
            printList();
            break;
        case "bye":
            exit();
            break;
        case "done":
            markAsDone(commands[1]);
            break;
        case "todo":
            Task newTask = new ToDo(commands[1].trim());
            addToList(newTask);
            break;
        case "deadline":
            String[] info = commands[1].split("/by", 2);
            newTask = new Deadline(info[0].trim(), info[1].trim());
            addToList(newTask);
            break;
        case "event":
            info = commands[1].split("/at", 2);
            newTask = new Event(info[0].trim(), info[1].trim());
            addToList(newTask);
            break;
        default:
            echo(command);
            break;
        }
    }

    private void printList() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks\n");
        } else {
            int uncompletedTask = 0;
            System.out.println("Here " + (taskList.size() > 1 ? "are" : "is")
                    + " your " + (taskList.size() > 1 ? "tasks:" : "task:"));
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println((i + 1) + ". " + currTask);
                if (!currTask.isDone()) {
                    uncompletedTask++;
                }
            }
            System.out.println("You have " + uncompletedTask + " uncompleted " + (uncompletedTask > 1 ? "tasks" :
                    "task") + ".\n");
        }
        getInput();
    }

    private void addToList(Task task) {
        taskList.add(task);
        System.out.println("I have added this task for you: ");
        System.out.println(task + "\n");
        getInput();
    }

    private void markAsDone(String index) {
        int taskIndex = Integer.valueOf(index) - 1;
        Task taskToBeCompleted = taskList.get(taskIndex);
        taskToBeCompleted.setDone(true);
        System.out.println("You have completed the " + taskToBeCompleted.getClass().getName() + ": \n"
                + taskToBeCompleted.getName() + ".\n");
        getInput();
    }

    private void echo(String input) {
        System.out.println("You have said \"" + input + "\"\n");
        getInput();
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public void start() {
        this.greet();
        this.getInput();
    }
}
