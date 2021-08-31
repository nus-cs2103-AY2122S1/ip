package banana;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * The Duke class runs the duke/chatbot
 * program.
 *
 * @author: Ravi Ananya
 **/

public class Duke {

    private static final String WELCOME_LABEL = "Hello! I'm Banana \n" + "     What can I do for you?";
    private static final String BYE_LABEL = "Bye. Hope to see you again soon!";

    private Storage store;
    private TaskList tasks;
    private Ui ui;

    /**
     * Tries starting up the program and
     * loading all the information from the files.
     *
     * @param filePath the file to be accessed.
     */
    public Duke(String filePath) {
        try {
            store = new Storage(filePath);
            ui = new Ui();
            tasks = store.load(
                    new File(store.getFilePath()));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        try {
            Parser.displayLabel(WELCOME_LABEL);
            String input = ui.getInput();
            while (!input.equals("bye")) {
                if (!input.equals("")) {
                    Parser p = new Parser(input);
                    p.useInput(tasks);
                    writeToFile(input, tasks);
                }
                input = ui.getInput();
            }
            Parser.displayLabel(BYE_LABEL);
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        new Duke("/Users/ravi57004/ip/src/main/java/Tasks.txt").run();
    }

    /**
     * Writes to the Tasks.txt file everytime
     * a change in tasks has occurred.
     *
     * @param input user input.
     * @param tasks the tasks to be managed.
     * @throws IOException if not able to write to the file.
     */
    public static void writeToFile(String input, TaskList tasks) throws IOException {
        String text = "";
        FileWriter fw = new FileWriter(
                "/Users/ravi57004/ip/src/main/java/Tasks.txt", false);
        if (!input.equals("bye")) {
            for (int i = 0; i < tasks.size(); i++) {
                String doneStr = "No";
                if (tasks.getTask(i).getIsDone().equals("[X]")) {
                    doneStr = "Yes";
                }
                if (tasks.getTask(i) instanceof ToDo) {
                    text += "T ~ " + doneStr + " ~ " +
                            tasks.getTask(i).getDescription() + "\n";
                } else if (tasks.getTask(i) instanceof Deadline) {
                    Deadline dl = (Deadline) tasks.getTask(i);
                    text += "D ~ " + doneStr + " ~ " +
                            tasks.getTask(i).getDescription() + " ~ " + dl.getDeadLine() + "\n";
                } else if (tasks.getTask(i) instanceof Event) {
                    Event ev = (Event) tasks.getTask(i);
                    text += "E ~ " + doneStr + " ~ " +
                            tasks.getTask(i).getDescription() + " ~ " + ev.getEvent() + "\n";
                } else {
                    text += doneStr + " ~ " +
                            tasks.getTask(i).getDescription() + "\n";
                }
            }
        }
        fw.write(text);
        fw.close();
    }

}

/**
 * The DukeException class
 * throws specialised Duke exceptions.
 *
 * @author: Ravi Ananya
 **/
class DukeException extends Exception {

    private String text;

    /**
     * Constructor for DukeException.
     *
     * @param text user input.
     */
    public DukeException(String text) {
        this.text = text;
    }

    /**
     * Gets the error message.
     *
     * @return the error message.
     */
    @Override
    public String getMessage() {
        return text;
    }

}

/**
 * The Task class handles tasks.
 *
 * @author: Ravi Ananya
 **/
class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description user input.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Gets the string notation of done
     * or not done.
     *
     * @return x for done or empty for not done.
     */
    public String getIsDone() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the user input.
     *
     * @return the user input.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as done.
     */
    public void setIsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getIsDone() + " " + description;
    }

}

/**
 * This class handles to-do
 * types of tasks.
 *
 * @author: Ravi Ananya
 **/

class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description user input.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

/**
 * This class handles deadline
 * types of tasks.
 *
 * @author: Ravi Ananya
 **/

class Deadline extends Task {

    protected String deadLine;
    protected LocalDate date;

    /**
     * Constructor for Deadline.
     *
     * @param description user input.
     * @param deadLine    date, day and/or time.
     */
    public Deadline(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;
    }

    /**
     * Constructor for Deadline.
     *
     * @param description user input.
     * @param date        for official dates
     * @param deadLine    date, day and/or time.
     */
    public Deadline(String description, LocalDate date, String deadLine) {
        super(description);
        this.date = date;
        this.deadLine = deadLine;
    }

    /**
     * Gets the deadline.
     *
     * @return the deadline
     */
    public String getDeadLine() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + deadLine;
        } else {
            return deadLine;
        }
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + deadLine + ")";
        } else {
            return "[D]" + super.toString() + " (by: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + deadLine + ")";
        }

    }

}

/**
 * This class handles event
 * types of tasks.
 *
 * @author: Ravi Ananya
 **/

class Event extends Task {

    protected LocalDate date;
    protected String timing;

    /**
     * Constructor for Event.
     *
     * @param description user input.
     * @param timing      date, day and/or time.
     */
    public Event(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Constructor for Event.
     *
     * @param description user input.
     * @param date        for official dates
     * @param timing      date, day and/or time.
     */
    public Event(String description, LocalDate date, String timing) {
        super(description);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Gets the event.
     *
     * @return the event.
     */
    public String getEvent() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timing;
        } else {
            return timing;
        }
    }

    @Override
    public String toString() {
        if (date == null) {
            return "[E]" + super.toString() + " (at: " + timing + ")";
        } else {
            return "[E]" + super.toString() + " (at: " +
                    date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timing + ")";
        }
    }

}







