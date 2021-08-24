package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandParameterException;
import duke.exception.NoSuchTaskException;
import java.io.IOException;
import duke.task.*;
import duke.UI;
import duke.Storage;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DukeParser {
    TaskList list;
    Storage storage;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);


    public DukeParser(TaskList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    public void parse(String rawInput) throws DukeException {
        Scanner inputScanner = new Scanner(rawInput);
        String checkForKeyword = inputScanner.next();

        switch (checkForKeyword) {
            case "list":
                handleList(inputScanner);
                break;
            case "done":
                handleDone(inputScanner);
                break;
            case "delete":
                handleDelete(inputScanner);
                break;
            case "todo":
                handleTodo(inputScanner);
                break;
            case "deadline":
                handleDeadline(inputScanner);
                break;
            case "event":
                handleEvent(inputScanner);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    public void handleList(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNext()) {
            throw new InvalidCommandParameterException();
        } else {
            UI.printList(list);
        }
    }

    public void handleDone(Scanner inputScanner) throws InvalidCommandParameterException
            , NoSuchTaskException {
        if (inputScanner.hasNextInt()) {
            int taskPos = inputScanner.nextInt() - 1;
            list.markDone(taskPos);
            storage.writeList(list);
            UI.printTaskDone(list.getTask(taskPos + 1));
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    public void handleDelete(Scanner inputScanner) throws InvalidCommandParameterException
            , NoSuchTaskException {
        if (inputScanner.hasNextInt()) {
            int taskPos = inputScanner.nextInt() - 1;
            Task temp = list.deleteTask(taskPos);
            storage.writeList(list);
            UI.printTaskDeleted(temp, list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    public void handleTodo(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String secondWord = inputScanner.nextLine();
            list.addTask(new Todo(secondWord));
            storage.writeList(list);
            UI.printTaskAdded(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    public void handleDeadline(Scanner inputScanner) throws InvalidCommandParameterException
             {
        if (inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/by ", 2);
            String content = contentAndDate[0];
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1],formatter);
            list.addTask(new Deadline(content, date));
            storage.writeList(list);
            UI.printTaskAdded(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

    public void handleEvent(Scanner inputScanner) throws InvalidCommandParameterException {
        if (inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/at ", 2);
            String content = contentAndDate[0];
            LocalDateTime date = LocalDateTime.parse(contentAndDate[1],formatter);
            list.addTask(new Event(content, date));
            storage.writeList(list);
            UI.printTaskAdded(list);
        } else {
            throw new InvalidCommandParameterException();
        }
    }

}
