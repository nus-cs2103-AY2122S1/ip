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
        this.command = sc.nextLine().trim();
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
        default:
            addToList(command);
            break;
        }
    }

    private void printList() {
        System.out.println("Here " + (taskList.size() > 1 ? "are" : "is") + " your " + (taskList.size() > 1 ? "tasks:" : "task:"));
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println();
        getInput();
    }

    private void addToList(String input) {
        taskList.add(new Task(input));
        echo(input);
        getInput();
    }

    private void markAsDone(String index) {
        int taskIndex = Integer.valueOf(index) - 1;
        Task taskToBeCompleted = taskList.get(taskIndex);
        taskToBeCompleted.setDone(true);
        System.out.println("You have completed the task: \n" + taskToBeCompleted.getName() + "\n");
        getInput();
    }

    private void echo(String input) {
        System.out.println("I have added the task: \n" + input + "\n");
        System.out.println("Anything else?\n");
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public void start() {
        this.greet();
        this.getInput();
    }
}
