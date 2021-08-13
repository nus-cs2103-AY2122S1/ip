import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
        try {
            switch (commands[0].toLowerCase()) {
            case "list":
                printList();
                break;
            case "bye":
                exit();
                break;
            case "done":
                if (commands.length != 2) {
                    throw new LifelineException("You did not specify an integer!");
                }
                markAsDone(commands[1]);
                break;
            case "todo":
            case "deadline":
            case "event":
                if (commands.length != 2) {
                    throw new LifelineException("Details of task cannot be blank!");
                }
                createTask(commands[0], commands[1]);
                break;
            default:
                echo(commands[0]);
                break;
            }
        } catch (LifelineException e) {
            System.out.println(e.getMessage());
        }

    }

    private void createTask(String task, String details) throws LifelineException {
        switch (task) {
        case "todo":
            Task newTask = new ToDo(details);
            addToList(newTask);
            break;
        case "deadline":
            String[] description = details.split("/by", 2);
            if (description.length != 2) {
                throw new LifelineException("Deadline cannot be blank! Use /by <deadline>");
            }
            newTask = new Deadline(description[0].trim(), description[1].trim());
            addToList(newTask);
            break;
        case "event":
            description = details.split("/at", 2);
            if (description.length != 2) {
                throw new LifelineException("Event date/time cannot be blank! Use /at <Day> <Time>");
            }
            newTask = new Deadline(description[0].trim(), description[1].trim());
            addToList(newTask);
            break;
        default:
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
            System.out.println("You have " + uncompletedTask + " uncompleted " + (uncompletedTask > 1 ? "tasks"
                    : "task") + ".\n");
        }
        getInput();
    }

    private void addToList(Task task) {
        taskList.add(task);
        System.out.println("I have added this task for you:");
        System.out.println(task + "\n");
        getInput();
    }

    private void markAsDone(String index) throws LifelineException {        try {
            int taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new LifelineException("Index is out of bounds!");
            }
            Task taskToBeCompleted = taskList.get(taskIndex);
            if (taskToBeCompleted.isDone()) {
                System.out.println("The task is already done!");
            } else {
                taskToBeCompleted.setDone(true);
                System.out.println("You have completed the " + taskToBeCompleted.getClass().getName() + ":\n"
                        + taskToBeCompleted.getName() + "\n");
            }

            getInput();
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer!");
        }
    }

    private void echo(String input) {
        System.out.println("You have said \"" + input + "\"\n");
        System.out.println("I am sorry! I don't know what that means ☹");
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
