package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {
    private TaskList tasks;
//    private String filePath = "data/tasks.txt";
//    private String directoryName = "data";

    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        try {
            storage.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            ui.showLine();
            String command = scanner.nextLine();
            try {
                if (command.equals("bye")) {
                    ui.showBye();
                    break;
                }
                if (command.equals("list")) {
                    tasks.printList();
                    continue;
                }
                String[] parsedCommand = Parser.parseCommand(command);

                switch (parsedCommand[0]) {
                case "done":
                    //Marks tasks as done
                    int index = Integer.valueOf(parsedCommand[1]) - 1;
                    tasks.setDone(index);
                    storage.saveTask(tasks);
                    break;

                case "delete":
                    //Deletes tasks
                    int indexD = Integer.valueOf(parsedCommand[1]) - 1;
                    tasks.deleteTask(indexD);
                    storage.saveTask(tasks);
                    break;

                case "todo":
                    //Adds a new Todo to the list
                    if (parsedCommand.length == 1) {
                        throw new TaskException("The description of a todo cannot be empty");
                    }
                    Todo newT = new Todo(parsedCommand[1], false);
                    tasks.addTask(newT);
                    storage.saveTask(tasks);
                    break;
                case "deadline":
                    if (parsedCommand.length == 1) {
                        throw new TaskException("The description of a deadline cannot be empty");
                    }
                    Task newD = Deadline.parseCommand(parsedCommand[1]);
                    tasks.addTask(newD);
                    storage.saveTask(tasks);
                    break;
                case "event":
                    if (parsedCommand.length == 1) {
                        throw new TaskException("The description of an event cannot be empty");
                    }
                    Task newE = Event.parseCommand(parsedCommand[1]);
                    tasks.addTask(newE);
                    storage.saveTask(tasks);
                    break;

                default:
                    throw new DukeException();
                }
            } catch (DukeException | TaskException | IOException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("The format of the date should be entered in the form dd-MM-yyyy HH:mm");
            }
            System.out.println("Now you have " + tasks.getNumTask() + "duke" + (tasks.getNumTask() > 1 ? "s " : " ") + "in the list");
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
