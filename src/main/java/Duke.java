import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> taskList = new ArrayList<>();

    private enum Command {
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        LIST,
        BYE
    };

    /**
     * Display formatted message.
     * @param message Message to be printed in console.
     */
    public void display(String message) {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    " + message);
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    /**
     * Display all items in the list.
     */
    public void displayAllItems() {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            String item = "    " + (i + 1) + "." +  taskList.get(i);
            System.out.println(item);
        }
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    /**
     * Display greeting message.
     */
    public void greet() {
        display("Hi, I'm Sync-Me Sebby.\n    " +
                "I'm here to assist you with tracking and synchronizing of your personal tasks.\n    " +
                "Let me know how I can help?");
    }

    /**
     * Display exit message.
     */
    public void exit() {
        this.display("Goodbye. See you again soon!");
    }

    /**
     * Display success message for adding task.
     * @param tasks The list of tasks.
     * @param task The individual task which can be Todo, Deadline or Event.
     */
    public void displaySuccessMessage(List<Task> tasks, Task task) {
        this.display("Got it. I've added this task: \n" + "      " + task + "\n    Now you have "
                + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks") + " in the list.");
    }

    public String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    public String getDescription(String userInput, String splitText, String splitTime) {
        String[] splitInput = userInput.split(splitText)[1].split(splitTime);
        return splitInput[0].trim();
    }

    public String getTime(String userInput, String splitTime) {
        String[] splitInput = userInput.split(splitTime);
        return splitInput[1];
    }

    public int getTaskNumber(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]);
    }

    /**
     * Checks if description is given for Todo, Deadline and Event tasks.
     * @param userInput the user input provided by scanner.
     * @return true if description is provided; false otherwise.
     */
    public boolean isDescExists(String userInput) {
        String[] arr = userInput.split(" ");
        return arr.length >= 2;
    }

    public boolean isTaskExists(int taskNumber) {
        return taskNumber <= this.taskList.size();
    }

    public void operate(String userInput, Command command) {
        switch (command) {
            case TODO:
                if (this.isDescExists(userInput)) {
                    // get task description
                    String desc = this.getDescription(userInput, "todo", "ignore");
                    // add task to list
                    Todo todo = new Todo(desc);
                    this.taskList.add(todo);
                    displaySuccessMessage(this.taskList, todo);
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case DEADLINE:
                if (this.isDescExists(userInput)) {
                    String descDeadline = this.getDescription(userInput, "deadline ", "/by ");
                    String timeDeadline = this.getTime(userInput,"/by ");
                    Deadline dl = new Deadline(descDeadline, timeDeadline);
                    this.taskList.add(dl);
                    displaySuccessMessage(this.taskList, dl);
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case EVENT:
               if (this.isDescExists(userInput)) {
                   String descEvent = this.getDescription(userInput, "event ", "/at ");
                   String timeEvent = this.getTime(userInput,  "/at ");
                   // add task to list
                   Event event = new Event(descEvent, timeEvent);
                   this.taskList.add(event);
                   displaySuccessMessage(this.taskList, event);
               } else {
                   display("OOPS! The description cannot be empty. Please try again.");
               }
               break;

            case DONE:
                if (this.isDescExists(userInput)) {
                    // get task number
                    int taskNumber = this.getTaskNumber(userInput);
                    if (isTaskExists(taskNumber)) {
                        Task task = this.taskList.get(taskNumber - 1);
                        // mark done as done
                        task.markAsDone();
                        display("Nice! This task is marked as done: \n" + "      " + task);
                    } else {
                        display("This task does not exist! Please try again.");
                    }
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case DELETE:
               if (this.isDescExists(userInput)) {
                   int taskNumberDel = this.getTaskNumber(userInput);
                   if (isTaskExists(taskNumberDel)) {
                       Task taskDel = this.taskList.get(taskNumberDel - 1);
                       this.taskList.remove(taskNumberDel - 1);
                       display("Gotchu mate. I've removed this task: \n" + "      " + taskDel + "\n    Now you have "
                               + this.taskList.size() + (this.taskList.size() <= 1 ? " task" : " tasks") + " in the list.");
                   } else {
                       display("This task does not exist! Please try again.");
                   }
               } else {
                   display("OOPS! The description cannot be empty. Please try again.");
               }
               break;

            case LIST:
                this.displayAllItems();
                break;

            default:
                display("OOPS! I do not understand what does that mean. Maybe you can try either one of " +
                        "[todo, deadline, event, done, list, delete]?");
        }
    }

    public void start() {

        this.greet();

        // initialize Scanner object
        Scanner scan = new Scanner(System.in);

        // read user input
        String userInput = scan.nextLine();
        String cmd = this.getCommand(userInput).toUpperCase();
        Command command = Command.valueOf(cmd);

        while (command!= Command.BYE) {
            this.operate(userInput, command);
            userInput = scan.nextLine();
            cmd = this.getCommand(userInput).toUpperCase();
            command = Command.valueOf(cmd);
        }
        scan.close();

        this.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}
