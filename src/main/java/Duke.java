import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void execute(String userInput) {
        String command = Parser.getCommand(userInput);
        switch (command) {
            case "todo":
                if (this.tasks.isDescExists(userInput)) {
                    // get task description
                    String desc = Parser.getDescription(userInput, "todo", "ignore");
                    // add task to list
                    Todo todo = tasks.createTodo(desc);
                    ui.displaySuccessMessage(todo, tasks.length());
                } else {
                    ui.display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case "deadline":
                if (this.tasks.isDescExists(userInput)) {
                    String descDeadline = Parser.getDescription(userInput, "deadline ", "/by ");
                    String dateDeadline = Parser.getDate(userInput,"/by ");
                    Deadline dl = tasks.createDeadline(descDeadline, dateDeadline);
                    ui.displaySuccessMessage(dl, tasks.length());
                } else {
                    ui.display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case "event":
               if (this.tasks.isDescExists(userInput)) {
                   String descEvent = Parser.getDescription(userInput, "event ", "/at ");
                   String dateEvent = Parser.getDate(userInput,  "/at ");
                   String timeEvent = Parser.getTime(userInput);
                   // add task to list
                   Event event = tasks.createEvent(descEvent, dateEvent, timeEvent);
                   ui.displaySuccessMessage(event, tasks.length());
               } else {
                   ui.display("OOPS! The description cannot be empty. Please try again.");
               }
               break;

            case "done":
                if (this.tasks.isDescExists(userInput)) {
                    // get task number
                    int taskNumber = Parser.getTaskNumber(userInput);
                    if (this.tasks.isTaskExists(taskNumber)) {
                        Task task = this.tasks.getTask(taskNumber - 1);
                        // mark done as done
                        task.markAsDone();
                        ui.display("Nice! This task is marked as done: \n" + "      " + task);
                    } else {
                        ui.display("This task does not exist! Please try again.");
                    }
                } else {
                    ui.display("OOPS! The description cannot be empty. Please try again.");
                }
                break;

            case "delete":
               if (this.tasks.isDescExists(userInput)) {
                   int taskNumberDel = Parser.getTaskNumber(userInput);
                   if (this.tasks.isTaskExists(taskNumberDel)) {
                       Task taskDel = this.tasks.getTask(taskNumberDel - 1);
                       tasks.deleteTask(taskNumberDel - 1);
                       ui.display("Gotchu mate. I've removed this task: \n" + "      " + taskDel + "\n    Now you have "
                               + this.tasks.length() + (this.tasks.length() <= 1 ? " task" : " tasks") + " in the list.");
                   } else {
                       ui.display("This task does not exist! Please try again.");
                   }
               } else {
                   ui.display("OOPS! The description cannot be empty. Please try again.");
               }
               break;

            case "list":
                this.tasks.displayAllItems();
                break;

            default:
                ui.display("OOPS! I do not understand what does that mean. Maybe you can try either one of " +
                        "[todo, deadline, event, done, list, delete]?");
        }
    }

    public void run() {

        ui.greet();

        // initialize Scanner object
        Scanner scan = new Scanner(System.in);

        // read user input
        String userInput = scan.nextLine();

        while (!userInput.equals("bye")) {
            this.execute(userInput);
            userInput = scan.nextLine();
        }

        scan.close();

        this.storage.writeToFile(tasks.getAllTasks());

        ui.exit();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

}
