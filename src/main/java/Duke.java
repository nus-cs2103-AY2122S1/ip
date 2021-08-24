import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;


    Duke(String filePath) {
        this.storage = new Storage(filePath);
        if (this.storage.readFile() != null) {
            this.taskList = new TaskList(this.storage.readFile());
        } else {
            this.taskList = new TaskList();
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke("file.text");

        System.out.println("Hello I am Duke.\nWhat can I do for you?");
        System.out.println();

        if (duke.taskList.getNumOfTask() > 0) {
            System.out.println("Current number of tasks: " + duke.taskList.getNumOfTask());
            duke.taskList.list();
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while (true) {
            if (echo.equals("bye")) {
                System.out.println("Bye! See you next time!");
                duke.storage.writeToFile(duke.taskList.getTaskList());
                break;
            }
            if (echo.equals("List")) {
                duke.taskList.list();
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("done")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.taskList.markAsDone(parsed[1]);
                } else {
                    duke.taskList.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            if (echo.equals("")) {
                System.out.println("Please enter a new task or action.");
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("delete")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.taskList.deleteTask(parsed[1]);
                } else {
                    duke.taskList.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            duke.taskList.addTask(echo);
            echo = scanner.nextLine();
        }

    }
}
