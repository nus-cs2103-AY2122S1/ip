import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This is chatbot (Aisu) class.
 * You can:
 * 1) Type "todo (task)" - Add tasks without any date/time attached to it
 * 2) Type "list" - Show list
 * 3) Type "done (taskNumber)" - Mark task as done
 * 4) Type "deadline (task) /by (date)" - Add tasks that need to be done before a specific date/time
 * 5) Type "event (task) /at (date)" - Add tasks that start at a specific time and ends at a specific time
 * 6) Type "bye" - Exit program
 */
public class Aisu {
    private Storage storage;
    private Tasklist tasklist;
    private Ui ui;

    public Aisu(String dirPath, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, fileName);
        try {
            List<Task> cachedData = this.storage.load();
            this.tasklist = new Tasklist(cachedData);
            ui.showToUser("Got cached list!");
        } catch (FileNotFoundException | AisuException e) {
            this.tasklist = new Tasklist();
        }
    }

    public void run() {
        ui.showWelcomeMessage();


        // let parser decide what to do, ui does the talking for repeated texts/general errors?
        Parser.parse(input);
        while (!input.equals("bye")) {
            ui.showDivider();
            try {
                if (input.equals("list")) { // show list
                    System.out.println(tasklist);
                } else {
                    if (input.startsWith("done ")) { // mark task as completed
                        int n = Integer.parseInt(input.substring(5));
                        tasklist.markDone(n);
                    } else if (input.startsWith("todo ")) {
                        tasklist.addTask(input.substring(5), Tasklist.TaskTypes.T);
                    } else if (input.startsWith("deadline ")) {
                        tasklist.addTask(input.substring(9), Tasklist.TaskTypes.D);
                    } else if (input.startsWith("event ")) {
                        tasklist.addTask(input.substring(6), Tasklist.TaskTypes.E);
                    } else if (input.startsWith("delete ")) {
                        int n = Integer.parseInt(input.substring(7));
                        tasklist.deleteTask(n);
                    } else {
                        throw new AisuException("That's an invalid task format...");
                    }
                }
                storage.save(tasklist);
            } catch (AisuException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e);
            } finally {
                ui.showDivider();

                input = sc.nextLine(); // get next input
            }
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Aisu("data", "test1.txt");
    }
}
