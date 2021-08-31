package banana;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Duke class is the program's
 * fundamental class.
 *
 * @author: Ravi Ananya
 **/

public class Duke extends Application {

    private static final String WELCOME_LABEL = "Hello! I'm Banana \n" + "     What can I do for you?";
    private static final String BYE_LABEL = "Bye. Hope to see you again soon!";

    private Storage store;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {

    }

    /**
     * Tries starting up the program and
     * loading all the information from the files.
     *
     * @param filePath the file to be accessed.
     */
    public void init(String filePath) {
        try {
            store = new Storage(filePath);
            tasks = store.load(
                    new File(store.getFilePath()));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the GUI program.
     *
     * @param stage the screen.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 700.0);
        scrollPane.setPrefSize(500, 635);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(440.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        init("/Users/ravi57004/ip/src/main/java/Tasks.txt");
        dialogContainer.getChildren().add(getDialogLabel(Parser.displayLabel(WELCOME_LABEL)));
        sendButton.setOnMouseClicked((event) -> {
            addToScreen(dialogContainer, userInput, tasks);
        });

        userInput.setOnAction((event) -> {
            addToScreen(dialogContainer, userInput, tasks);
        });
    }

    /**
     * The tasks to be printed
     * on the screen.
     *
     * @param dc the dialogContainer.
     * @param ui the Textfield
     * @param tasks the list of tasks.
     */
    public void addToScreen(VBox dc, TextField ui, TaskList tasks) {
        try {
            Parser p = new Parser(ui.getText());
            if (!ui.getText().equals("bye")) {
                String output = p.useInput(tasks);
                dc.getChildren().add(getDialogLabel(output));
                writeToFile(ui.getText(), tasks);
            } else {
                dc.getChildren().add(getDialogLabel(Parser.displayLabel(BYE_LABEL)));
            }
            ui.clear();
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    public static void main(String[] args) {
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







