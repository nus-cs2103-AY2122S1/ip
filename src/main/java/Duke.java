import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class Duke implements Runnable {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        // initialization
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // greet the user
        ui.hi();
        // listen to user input
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            Parser parser = new Parser();
            String command = parser.parse(input);
            switch (command) {
                case "bye":
                    try {
                        storage.store(tasks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ui.bye();
                    scan.close();
                    break;
                case "todo":
                    try {
                        Task t = new Todo(input);
                        tasks.add(t);
                        ui.add(t, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.reply(new EmptyDescriptionError("todo").getMessage());
                    }
                    break;
                case "event":
                    try {
                        Task t = new Event(input);
                        tasks.add(t);
                        ui.add(t, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.reply(new EmptyDescriptionError("event").getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        Task t = new Deadline(input);
                        tasks.add(t);
                        ui.add(t, tasks.size());
                    } catch (IndexOutOfBoundsException e) {
                        ui.reply(new EmptyDescriptionError("event").getMessage());
                    }
                    break;
                case "list":
                    ui.reply(tasks.list());
                    break;
                case "done":
                    Task t = tasks.done(Integer.parseInt(input.substring(5)) - 1);
                    ui.done(t);
                    break;
                case "delete":
                    t = tasks.delete(Integer.parseInt(input.substring(7)) - 1);
                    ui.delete(t, tasks.size());
                    break;
                default:
                    try {
                        throw new UnknownCommandError("");
                    } catch (UnknownCommandError e) {
                        ui.reply(e.getMessage());
                    }
                    break;
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
