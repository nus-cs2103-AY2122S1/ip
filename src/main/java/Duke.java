import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {

    private static final String WELCOME_LABEL = "Hello! I'm Banana \n" + "     What can I do for you?";
    private static final String BYE_LABEL = "Bye. Hope to see you again soon!";

    private Storage store;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            store = new Storage(filePath);
            ui = new Ui();
            tasks = new TaskList(store.load(
                    new File(store.getFilePath())));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            Parser.displayLabel(WELCOME_LABEL);
            while (true) {
                String input = ui.getInput();;
                if (input.equals("bye")) { break; }
                Parser p = new Parser(input);
                p.useInput(tasks);
                writeToFile(input, tasks);
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



    public static void writeToFile(String input, TaskList tasks) throws DukeException, IOException {
        String text = "";
        FileWriter fw = new FileWriter("/Users/ravi57004/ip/src/main/java/Tasks.txt", false);
        if (!input.equals("bye")) {
            for (int i = 0; i < tasks.size(); i++) {
                String doneStr = "No";
                if (tasks.getTask(i).getIsDone().equals("[X]")) {
                    doneStr = "Yes";
                }
                if (tasks.getTask(i) instanceof ToDo) {
                    text += "T ~ " + doneStr + " ~ " + tasks.getTask(i).newTask + "\n";
                } else if (tasks.getTask(i) instanceof Deadline) {
                    Deadline dl = (Deadline) tasks.getTask(i);
                    text += "D ~ " + doneStr + " ~ " + tasks.getTask(i).newTask + " ~ " + dl.getDeadLine() + "\n";
                } else if (tasks.getTask(i) instanceof Event) {
                    Event ev = (Event) tasks.getTask(i);
                    text += "E ~ " + doneStr + " ~ " + tasks.getTask(i).newTask + " ~ " + ev.getEvent() + "\n";
                } else {
                    text += doneStr + " ~ " + tasks.getTask(i).newTask + "\n";
                }
            }
        }
        fw.write(text);
        fw.close();
    }
}


class DukeException extends Exception {

    private String text;

    public DukeException(String text) {
        this.text = text;
    }

    @Override
    public String getMessage() {
        return text;
    }

}

class Task {

    protected String newTask;
    protected boolean isDone;

    public Task(String newTask) {
        this.newTask = newTask;
    }

    public String getIsDone() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setIsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return getIsDone() + " " + newTask;
    }

}

class ToDo extends Task {

    protected String newTask;

    public ToDo(String newTask) {
        super(newTask);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

class Deadline extends Task {

    protected String newTask;
    protected String deadLine;
    protected LocalDate date;

    public Deadline(String newTask, String deadLine) {
        super(newTask);
        this.deadLine = deadLine;
    }

    public Deadline(String newTask, LocalDate date,  String deadLine) {
        super(newTask);
        this.date = date;
        this.deadLine = deadLine;
    }

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

class Event extends Task {

    protected String newTask;
    protected LocalDate date;
    protected String timing;

    public Event(String newTask, String timing) {
        super(newTask);
        this.timing = timing;
    }

    public Event(String newTask, LocalDate date,  String timing) {
        super(newTask);
        this.date = date;
        this.timing = timing;
    }

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






