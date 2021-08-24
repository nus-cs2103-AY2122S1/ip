import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> taskList = new ArrayList<>();
    private FileController fileController;

    public Duke(String filePath) {
        this.fileController = new FileController(filePath);
    }

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

    public void operate(String userInput) {
        String command = this.getCommand(userInput);
        switch (command) {
            case "todo":
                if (this.isDescExists(userInput)) {
                    // get task description
                    String desc = this.getDescription(userInput, "todo", "ignore");
                    // add task to list
                    Todo todo = new Todo(desc);
                    this.taskList.add(todo);
                    this.fileController.writeToFile(this.taskList);
                    displaySuccessMessage(this.taskList, todo);
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case "deadline":
                if (this.isDescExists(userInput)) {
                    String descDeadline = this.getDescription(userInput, "deadline ", "/by ");
                    String timeDeadline = this.getTime(userInput,"/by ");
                    Deadline dl = new Deadline(descDeadline, timeDeadline);
                    this.taskList.add(dl);
                    this.fileController.writeToFile(this.taskList);
                    displaySuccessMessage(this.taskList, dl);
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case "event":
               if (this.isDescExists(userInput)) {
                   String descEvent = this.getDescription(userInput, "event ", "/at ");
                   String timeEvent = this.getTime(userInput,  "/at ");
                   // add task to list
                   Event event = new Event(descEvent, timeEvent);
                   this.taskList.add(event);
                   this.fileController.writeToFile(this.taskList);
                   displaySuccessMessage(this.taskList, event);
               } else {
                   display("OOPS! The description cannot be empty. Please try again.");
               }
               break;

            case "done":
                if (this.isDescExists(userInput)) {
                    // get task number
                    int taskNumber = this.getTaskNumber(userInput);
                    if (isTaskExists(taskNumber)) {
                        Task task = this.taskList.get(taskNumber - 1);
                        // mark done as done
                        task.markAsDone();
                        this.fileController.writeToFile(this.taskList);
                        display("Nice! This task is marked as done: \n" + "      " + task);
                    } else {
                        display("This task does not exist! Please try again.");
                    }
                } else {
                    display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case "delete":
               if (this.isDescExists(userInput)) {
                   int taskNumberDel = this.getTaskNumber(userInput);
                   if (isTaskExists(taskNumberDel)) {
                       Task taskDel = this.taskList.get(taskNumberDel - 1);
                       this.taskList.remove(taskNumberDel - 1);
                       this.fileController.writeToFile(this.taskList);
                       display("Gotchu mate. I've removed this task: \n" + "      " + taskDel + "\n    Now you have "
                               + this.taskList.size() + (this.taskList.size() <= 1 ? " task" : " tasks") + " in the list.");
                   } else {
                       display("This task does not exist! Please try again.");
                   }
               } else {
                   display("OOPS! The description cannot be empty. Please try again.");
               }
               break;

            case "list":
                this.displayAllItems();
                break;

            default:
                display("OOPS! I do not understand what does that mean. Maybe you can try either one of " +
                        "[todo, deadline, event, done, list, delete]?");
        }
    }

    public void start() {

        // create file if it does not exist
        this.fileController.createFile();

        this.greet();

        // initialize Scanner object
        Scanner scan = new Scanner(System.in);

        // read user input
        String userInput = scan.nextLine();

        while (!userInput.equals("bye")) {
            this.operate(userInput);
            userInput = scan.nextLine();
        }
        scan.close();

        this.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.start();
    }

}
