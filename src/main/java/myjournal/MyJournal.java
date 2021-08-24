package myjournal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import myjournal.Storage;
import myjournal.TaskList;
import myjournal.Ui;
import myjournal.InvalidTaskNumberException;
import myjournal.InvalidTypeException;
import myjournal.Task;
import myjournal.Event;
import myjournal.Todo;
import myjournal.Deadline;
import myjournal.EmptyDescriptionException;

/**
 * A class to create chatBot called MyJournal.
 *
 * @author Felissa Faustine
 */
public class MyJournal {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MyJournal(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        String input;
        Scanner reader = new Scanner(System.in);
        ui.welcomeMessage();
        while (true) {
            input = reader.nextLine();
            try {
                Scanner line = new Scanner(input);
                String firstWord = line.next();
                if (input.equals("bye")) {
                    break;
                } else {
                    switch (firstWord) {
                        case "done":
                            ui.doneTaskPrint(Parser.parseDone(line, tasks));
                            break;
                        case "delete":
                            ui.removeTaskPrint(Parser.parseDelete(line, tasks));
                            break;
                        case "list":
                            Parser.parseList(tasks);
                            break;
                        case "todo":
                            tasks.addTask(Parser.parseTodo(line));
                            ui.taskAddPrint(tasks);
                            break;
                        case "event":
                            tasks.addTask(Parser.parseEvent(line));
                            ui.taskAddPrint(tasks);
                            break;
                        case "deadline":
                            tasks.addTask(Parser.parseDeadline(line));
                            ui.taskAddPrint(tasks);
                            break;
                        default:
                            throw new InvalidTypeException("OOPS!!! Please put either todo/event/deadline!");
                    }
                }
                storage.saveFile(tasks.toString());
            } catch (EmptyDescriptionException exception) {
                System.out.println(exception.toString());
            } catch (InvalidTypeException exception) {
                System.out.println(exception.toString());
            } catch (InvalidTaskNumberException exception) {
                System.out.println(exception.toString());
            } catch (DateTimeParseException exception) {
                System.out.println(exception.toString());
            }
        }
        ui.goodByeMessage();
    }

    /**
     * The main method of the MyJournal class.
     *
     * @param args An input of an array of strings.
     */
    public static void main(String[] args) {
        try {
            new MyJournal("./tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}