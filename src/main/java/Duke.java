import org.junit.Test;

import javax.xml.stream.util.EventReaderDelegate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();
    private static final String divider = "\t____________________________________________________________\n";

    @FunctionalInterface
    private interface CheckedConsumer<T> {
        void accept(T t) throws DukeException;
    }

    enum Action {
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        NONE
    }

    private static class DukeException extends Exception {
        private DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public void greet() {
        dukePrint("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public boolean process(String str) {
        try {
            String[] arr = str.split(" ", 2);
            if (arr.length < 1) {
                throw new DukeException("No command was given.");
            }
            String firstWord = arr[0];
            if (str.equalsIgnoreCase("bye")) {
                dukePrint("Bye. Hope to see you again soon!\n");
                return false;
            } else if (str.equalsIgnoreCase("list")) {
                displayList();
            } else {
                Action action = parseFirstWord(firstWord.toLowerCase());
                CheckedConsumer<String> consumer = actionToFunction(action);
              if (arr.length < 2 && (action == Action.DONE || action == Action.DELETE)) {
                    throw new DukeException("Task number for "+firstWord+" is not given.");
                } else if (arr.length < 2 || arr[1].isBlank()) {
                    throw new DukeException("The description of " + firstWord + " cannot be empty");
                } else {
                   consumer.accept(arr[1]);
                }
            }
            return true;
        } catch (DukeException e) {
            dukePrint( "☹ OOPS!!! " + e.getMessage() + "\n");
            return true;
        }
    }

    private Action parseFirstWord(String firstWord) {
        switch (firstWord) {
            case "done":
                return Action.DONE;
            case "delete":
                return Action.DELETE;
            case "todo":
                return Action.TODO;
            case "deadline":
                return Action.DEADLINE;
            case "event":
                return Action.EVENT;
            default:
                return Action.NONE;
        }
    }


    private CheckedConsumer<String> actionToFunction(Action action) throws DukeException {
        switch (action) {
            case DONE:
                return this::done;
            case DELETE:
                return this::delete;
            case TODO:
                return this::addTodo;
            case DEADLINE:
                return this::addDeadline;
            case EVENT:
                return this::addEvent;
            case NONE:
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void delete(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                Task t =list.remove(i-1);
                saveFile();
                dukePrint("Got it. I've removed this task:\n" + t + "\n" + "Now you have " +
                        list.size() + " task" + (list.size() < 2 ? " " : "s ") + "in the list.");
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    private void done(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                Task t = list.get(i - 1);
                t.markDone();
                dukePrint("Nice! I've marked this task as done:\n" + t);
                saveFile();
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    private void addEvent(String str) throws DukeException {
        if (str.contains("/at ")) {
            String[] arr = str.split("/at ", 2);
            Task t = new Event(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }

    private void addDeadline(String str) throws DukeException {
        if (str.contains("/by ")) {
            String[] arr = str.split("/by ", 2);
            Task t = new Deadline(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }


    private void addTodo(String str) throws DukeException {
        Task t = new Todo(str);
        addTask(t);
    }

    private void addTask(Task t) throws DukeException {
        list.add(t);
        dukePrint("Got it. I've added this task:\n" + t + "\n" + "Now you have " +
                list.size() + " task" + (list.size() < 2 ? " " : "s ") + "in the list.");
        saveFile();
    }

    private void displayList() {
        if (list.size() == 0) {
            dukePrint("list empty");
            return;
        }
        //TODO: Change to dukeprint
        System.out.print(divider);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }

    private void saveFile() throws DukeException {
        try {
            File folder = new File("./data");
            if (!folder.exists()) {
                boolean isFolderCreated = folder.mkdir();
            }
            File file = new File("./data/duke.txt");
           file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(list.stream().map(Task::saveString).reduce(
                    "", (string1, string2) -> string1 + string2 + "\n"));
            writer.close();
            dukePrint("Tasks have been saved successfully.");
        }
        catch (IOException e) {
            throw new DukeException("IO Exception File Cannot Be Found");
        }
    }

    public void loadFile() {
        File file = new File("./data/duke.txt");
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    Task t = processData(data);
                    if (t!=null){
                        list.add(t);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                //TODO: Duke exception
                dukePrint("Error");
            }
        }
    private Task processData(String str) {
        Scanner stringProcessor  = new Scanner(str);
        stringProcessor.useDelimiter("\\|");
        try {
            String type = stringProcessor.next();
            switch (type) {
                case "T":
                    return new Todo(stringProcessor.next(),stringProcessor.next());
                case "D":
                    return new Deadline(stringProcessor.next(), stringProcessor.next(),stringProcessor.next());
                case "E":
                    return new Event(stringProcessor.next(), stringProcessor.next(),stringProcessor.next());
            }
            return null;
        } catch (NoSuchElementException e){
            //TODO: Duke exception
            dukePrint("Error");
            return null;
        }
    }

    private void dukePrint(String str) {
        Scanner scanner = new Scanner(str);
        System.out.print(divider);
        while (scanner.hasNextLine()) {
            System.out.print("\t");
            System.out.println(scanner.nextLine());
        }
        System.out.println(divider);
    }
}
