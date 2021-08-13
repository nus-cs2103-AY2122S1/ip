import java.util.ArrayList;
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
        boolean exit = false;
        while (!exit) {
            try {
                this.command = sc.nextLine().trim();
                String[] inputs = command.split("\\s", 2);
                System.out.println();
                switch (getInputType(inputs[0])) {
                case LIST:
                    printList();
                    break;
                case BYE:
                    exit = true;
                    break;
                case DONE:
                    if (inputs.length != 2) {
                        throw new LifelineException("You did not specify an integer!");
                    }
                    markAsDone(inputs[1]);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    if (inputs.length != 2) {
                        throw new LifelineException("Details of task cannot be blank!");
                    }
                    createTask(inputs[0], inputs[1]);
                    break;
                default:
                    echo(inputs[0]);
                    break;
                }
            } catch (LifelineException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
        exit();
    }

    private InputType getInputType(String input) throws LifelineException {
        try {
            return InputType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LifelineException("I am sorry! I don't know what that means! ☹");
        }
    }

    private void createTask(String task, String details) throws LifelineException {
        switch (task.toLowerCase()) {
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
            System.out.println("You have no remaining tasks.\n");
        } else {
            int uncompletedTask = 0;
            System.out.println("Here " + (taskList.size() > 1 ? "are" : "is")
                    + " your remaining " + (taskList.size() > 1 ? "tasks:" : "task:"));
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
    }

    private void addToList(Task task) {
        taskList.add(task);
        System.out.println("I have added this task for you:");
        System.out.println(task + "\n");
    }

    private void markAsDone(String index) throws LifelineException {
        try {
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
        } catch (NumberFormatException e) {
            throw new LifelineException("Index is not an integer!");
        }
    }

    private void echo(String input) {
        System.out.println("You have said \"" + input + "\"\n");
        System.out.println("I am sorry! I don't know what that means ☹\n");
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public void start() {
        this.greet();
        this.getInput();
    }
}
