/**
 * Todo App.
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Duke {
    public static TaskList tasks;
    public static Storage storage;
    public static Ui ui;
    public static Parser parser;

    /**
     * Constructor.
     *
     * @param filePath Path of the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.load();
            ui.printLoadSuccess();
        } catch (DukeException err) {
            ui.printDukeException(err);
            tasks = new TaskList();
        }
    }

    /**
     * Starts the ToDo app.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/save.txt").run();
    }

    private void run() {
        ui.printWelcome();

        // Take user input
        String nextLine;
        Command nextCommand = Command.INVALID;
        do {
            try {
                nextLine = ui.nextLine();
                nextCommand = parser.parseCommand(nextLine);
                String[] arguments = parser.parseArguments(nextCommand, nextLine);

                execute(nextCommand, arguments);
                storage.save(tasks);
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
        } while (!parser.isBye(nextCommand));

    }

    private void execute(Command c, String[] arguments) throws DukeException {
        switch (c) {
        case TODO:
            tasks.addTask(new ToDo(arguments[0]));
            ui.printNewTask(tasks);
            break;
        case DEADLINE:
            tasks.addTask(new Deadline(arguments[0], parser.parseDate(arguments[1])));
            ui.printNewTask(tasks);
            break;
        case EVENT:
            tasks.addTask(new Event(arguments[0], arguments[1]));
            ui.printNewTask(tasks);
            break;
        case DONE:
            int index = parser.parseInt(arguments[0]) - 1;
            tasks.completeTask(index);
            ui.printDoneTask(tasks.get(index));
            break;
        case DELETE:
            tasks.deleteTask(parser.parseInt(arguments[0]) - 1);
            break;
        case LIST:
            ui.printList(tasks);
            break;
        case BYE:
            ui.printGoodbye();
            ui.closeScanner();
            break;
        }
    }
}

    /**
     * Listens for user input via scanner and responds accordingly.
     * list - shows current list of Task.
     * bye - closes and saves the app.
     * todo [description] - adds todo to list.
     * deadline [description] /by [date] - adds deadline to list.
     * event [description] /at [date] - adds events to list.
     */
//    private void run() {
//
//        while (!s.equals("bye")) {
//            try {
//                if (s.equals("list")) {
//                    int index = 1;
//
//                    System.out.println("---------");
//                    for (Task item : tasks) {
//                        System.out.println(index + ". " + item.toString());
//                        index++;
//                    }
//                    System.out.println("---------");
//                } else if (s.startsWith("done")) {
//                    int index = Integer.parseInt(s.split(" ")[1]) - 1;
//                    tasks.get(index).setStatus(true);
//
//                    System.out.println("---------");
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println(tasks.get(index));
//                    System.out.println("---------");
//                } else if (s.startsWith("delete")) {
//                    int index = Integer.parseInt(s.replace("delete", "").trim()) - 1;
//
//                    System.out.println("---------");
//                    System.out.println("Noted. I've removed this task:");
//                    System.out.println(tasks.get(index));
//                    tasks.remove(index);
//                    System.out.println("Now you have " + tasks.size() + " task in the list");
//                    System.out.println("---------");
//
//                } else {
//                    // Adding tasks
//                    if (s.startsWith("todo")) {
//                        if (s.replace("todo", "").trim().equals("")) {
//                            throw new DukeException("ToDos need to have a description");
//                        }
//                        tasks.add(new ToDo(s.replace("todo", "").trim()));
//                    } else if (s.startsWith("deadline")) {
//                        if (!s.contains("/by")) {
//                            throw new DukeException("deadlines require a /by");
//                        }
//
//                        try {
//                            String[] data = s.replace("deadline ", "").split("/by");
//                            tasks.add(
//                                    new Deadline(data[0].trim(),
//                                    LocalDate.parse(data[1].trim())
//                            ));
//                        } catch (DateTimeParseException err) {
//                            throw new DukeException("/by date is not valid");
//                        }
//                    } else if (s.startsWith("event")) {
//                        if (!s.contains("/at")) {
//                            throw new DukeException("events require an /at");
//                        }
//
//                        String[] data = s.replace("event ", "").split("/at");
//                        tasks.add(new Event(data[0].trim(), data[1].trim()));
//                    } else {
//                        throw new DukeException("Sorry I don't know what that means");
//                    }
//
//                    int len = tasks.size();
//                    System.out.println("---------");
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(tasks.get(len - 1).toString());
//                    System.out.println("Now you have " + len + " task in the list");
//                    System.out.println("---------");
//                }
//
//                saveList();
//                s = in.nextLine();
//            } catch (DukeException err) {
//                System.out.println("---------");
//                System.out.println(err.getMessage());
//                System.out.println("---------");
//                s = in.nextLine();
//            }
//
//        }
//    }

