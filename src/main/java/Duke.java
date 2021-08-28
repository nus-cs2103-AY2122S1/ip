import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static Parser parser = new Parser();

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage (filePath);
        try {
            Ui.print("Hello! My name is Alexa \nHow can I help you today?");
            tasks = new TaskList(Storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/alexa.txt").run();
    }

    public static void run() {
        Scanner newInput = new Scanner(System.in);
        while(newInput.hasNextLine()) {
            String input = newInput.nextLine();
            Task currentTask = new Task(input);
            try {
                if (input.equals("bye")) {
                    Ui.print("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    Ui.list();
                } else if (parser.parseDone(input)) {
                    Ui.done(input);
                } else if (parser.parseToDo(input)) {
                    TaskList.todo(input);
                } else if (parser.parseDeadline(input)) {
                    TaskList.deadline(input);
                } else if (parser.parseEvent(input)) {
                    TaskList.event(input);
                } else if (parser.parseDelete(input)) {
                    TaskList.delete(input);
                } else {
                    Ui.invalidInput();
                }
                Storage.writeTasks();
            } catch (DukeException err){
                Ui.print(err.getMessage());
            }
        }
    }
}
