package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import java.lang.String;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * The Duke program implements an application that reads the user input
 * and does the corresponding actions based on the user input.
 *
 * @author Ethan
 */
public class Duke extends Application {

    public static final String WIPE_COMMAND = "WIPE";
    public static final String FIND_COMMAND = "f ";
    public static final String FIND_COMMAND_ALTERNATE = "s ";
    public static final String EVENT_COMMAND = "e ";
    public static final String DEADLINE_COMMAND = "d ";
    public static final String TODO_COMMAND = "t ";
    public static final String DELETE_COMMAND = "del ";
    public static final String DELETE_COMMAND_ALTERNATE = "rem ";
    public static final String DONE_COMMAND = "do ";
    public static final String LIST_COMMAND = "l";
    public static final String BYE_COMMAND = "bye";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image("https://static.wikia.nocookie.net/undertale/images/0/0b/Burgerpants_face_1.png/revision/latest/smart/width/250/height/250?cb=20160103175652");
    private Image user = new Image("https://static.wikia.nocookie.net/undertale-au-fanon/images/1/1e/Undertale_frisk_Sprite.png/revision/latest?cb=20200624132041");

    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskListInternal taskListInternal = new TaskListInternal();
    public static File file = new File("data/list.txt");

    public static void main(String[] args) {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        storage.checkExistence();

        ui.showFileLocation(file.getAbsolutePath());

        taskListInternal.initialise(file, storage);

        Parser.parse();
    }

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
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    /**
     * Gets the response to a command from Duke.
     *
     * @param command The command that was entered.
     */
    public String getResponse(String command) {

        taskListInternal.initialise(file, storage);
        if (command.equals(BYE_COMMAND)) {
            return "Have a SPARKELOUS day.";
        } else if (command.equals(LIST_COMMAND)) {

            return listStringForm();

        } else if (command.startsWith(DONE_COMMAND) && command.length()>DONE_COMMAND.length()) {
            String numbers = command.substring(DONE_COMMAND.length());

            //Exception handling for invalid number inputs
            try {
                int taskNo = Integer.parseInt(numbers);
            } catch (NumberFormatException notANumber) {
                System.err.println(notANumber);
                return "HELLO SIR I REQUIRE A NUMBER";
            }

            int taskNo = Integer.parseInt(numbers);

            //Exception handling for numbers that are too big or small
            if (TaskListInternal.lines.size() < taskNo) {
                return "HELLO SIR THAT'S TOO MUCH";
            }
            if (taskNo <= 0) {
                return "HI SIR NO NON-POSITIVE INTEGERS ALLOWED";
            }

            int targetIndex = taskNo-1;
            return markedAsDoneString(targetIndex);

        } else if ((command.startsWith(DELETE_COMMAND) || command.startsWith(DELETE_COMMAND_ALTERNATE))
                && command.length()>DELETE_COMMAND.length()) {
            String numbers = command.substring(DELETE_COMMAND.length());

            //Exception handling for invalid number inputs
            try {
                int taskNo = Integer.parseInt(numbers);
            } catch (NumberFormatException notANumber) {
                System.err.println(notANumber);
                return "THAT'S NOT A NUMBER";
            }

            int taskNo = Integer.parseInt(numbers);

            //Exception handling for numbers that are too big or small
            if (TaskListInternal.lines.size() < taskNo) {
                return "THAT'S TOO MUCH";
            }
            if (taskNo <= 0) {
                return "HI SIR NO NON-POSITIVE INTEGERS ALLOWED";
            }

            int targetIndex = taskNo-1;
            return taskDeletedString(targetIndex);

        } else if (command.startsWith(TODO_COMMAND) && command.length()>TODO_COMMAND.length()) {
            String task = command.substring(TODO_COMMAND.length());

            //Exception handling for missing name
            if (task.equals("")) {
                return "I NEED A NAME SIR!!!";
            }

            return todoTaskAddedString(task);


        } else if (command.startsWith(DEADLINE_COMMAND) && command.length()>DEADLINE_COMMAND.length()) {
            String taskNDate = command.substring(DEADLINE_COMMAND.length());

            //Exception handling for missing date
            if (!(taskNDate.contains("/by"))) {
                return "BY WHEN? I DONT KNOW AHHHHHHHHHHH";
            }

            //Exception handling for missing name or date
            try {
                int splitIndex = taskNDate.indexOf("/by");
                String task = taskNDate.substring(0, splitIndex - 1);
                String date = taskNDate.substring(splitIndex + 4);
            }catch(StringIndexOutOfBoundsException error){
                return "THERES NO NAME or DATE OF the TASK!!!! Oh no.";
            }

            int splitIndex = taskNDate.indexOf("/by");
            String task = taskNDate.substring(0, splitIndex - 1);
            String date = taskNDate.substring(splitIndex + 4);

            //Exception handling for missing name
            if (task.equals("")) {
                return "WHATS THE NAME OF THE THING SIR";
            }

            //Exception handling for invalid date
            try {
                LocalDate test = LocalDate.parse(date);
            } catch (DateTimeException error) {
                return "That is not a date. Please enter in YYYY-MM-DD format. " +
                        "So sorry to inconvenience you.";
            }

            return deadlineTaskAddedString(task, date);

        } else if (command.startsWith(EVENT_COMMAND) && command.length()>EVENT_COMMAND.length()) {
            String taskNDate = command.substring(EVENT_COMMAND.length());

            //Exception handling for missing date
            if (!(taskNDate.contains("/at"))) {
                return "HELP THERES NO DATE please enter in YYYY-MM-DD format. " +
                        "Thank you for your kind cooperation.";
            }

            //Exception handling for missing name or date
            try {
                int splitIndex = taskNDate.indexOf("/at");
                String task = taskNDate.substring(0, splitIndex - 1);
                String date = taskNDate.substring(splitIndex + 4);
            }catch(StringIndexOutOfBoundsException error){
                return "THERES NO NAME or DATE OF the TASK!!!! Oh no.";
            }

            int splitIndex = taskNDate.indexOf("/by");
            String task = taskNDate.substring(0, splitIndex - 1);
            String date = taskNDate.substring(splitIndex + 4);

            //Exception handling for missing name
            if (task.equals("")) {
                return "WHATS THE NAME OF THE THING";
            }

            //Exception handling for invalid date
            try {
                LocalDate test = LocalDate.parse(date);
            } catch (DateTimeException error) {
                return "WHATS THE DATE WHAT HAPPENED";
            }

            return eventTaskAddedString(task, date);

        } else if ((command.startsWith(FIND_COMMAND) || command.startsWith(FIND_COMMAND_ALTERNATE))
                && command.length()>FIND_COMMAND.length()) {
            String searchQuery = command.substring(FIND_COMMAND.length());
            return searchResultsString(searchQuery);

        } else if (command.equals(WIPE_COMMAND)) {
            return listWipedString();
        } else {
            return "(WHAT IS THIS PERSON TRYING TO SAY WHY IS HE TYPING GIBBERISH I'M JUST TRYING TO SURVIVE)";
        }
    }

    /**
     * Wipes the list and returns a string to notify the user.
     *
     * @return String
     */
    String listWipedString() {
        TaskListInternal.lines.clear();
        try {
            storage.writeListToFile(Duke.file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert TaskListInternal.lines.isEmpty();
        return "BAM! The list has been wiped.";
    }

    /**
     * Searches the task list for a tasks containing a specific search query and
     * returns a sublist with the search results.
     *
     * @param searchQuery The query the user is searching for.
     * @return String form of a sublist with the search results.
     */
    String searchResultsString(String searchQuery) {
        int c = 1;
        String output = "Here are the search results for \"" + searchQuery + "\":" + System.lineSeparator();
        for (String task : TaskListInternal.lines) {
            if (task.contains(searchQuery)) {
                output += c + ". " + task + System.lineSeparator();
            }
            c++;
        }
        assert c== TaskListInternal.lines.size();
        return output;
    }

    /**
     * Attempts to add a deadline to the task list and returns a string to notify
     * the user.
     *
     * @param task Name of the deadline.
     * @param date Date of the deadline.
     * @return String informing user that the task has been added or that it is already
     *         inside the list.
     */
    String deadlineTaskAddedString(String task, String date) {
        Deadline taskToAdd = new Deadline(task, date);
        String toBeAdded = taskToAdd.toString();
        boolean notAlreadyInside = taskListInternal.add(storage, toBeAdded);
        try {
            storage.writeListToFile(Duke.file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (notAlreadyInside) {
            return "Task " + toBeAdded + " has been added. SPARKTASTIC.";
        } else {
            return "It is already inside. WOWZA!";
        }
    }

    /**
     * Attempts to add an event to the task list and returns a string to notify
     * the user.
     *
     * @param task Name of the event.
     * @param date Date of the event.
     * @return String informing user that the task has been added or that it is already
     *         inside the list.
     */
    String eventTaskAddedString(String task, String date) {
        Event taskToAdd = new Event(task, date);
        String toBeAdded = taskToAdd.toString();
        boolean notAlreadyInside = taskListInternal.add(storage, toBeAdded);
        try {
            storage.writeListToFile(Duke.file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (notAlreadyInside) {
            return "Task " + toBeAdded + " has been added. SPARKTASTIC.";
        } else {
            return "It is already inside. WOWZA!";
        }
    }

    /**
     * Attempts to add a to-do task to the task list and returns a
     * string to notify the user.
     *
     * @param task Name of the to-do task.
     * @return String informing user that the task has been added or that it is already
     *         inside the list.
     */
    String todoTaskAddedString(String task) {
        ToDo taskToAdd = new ToDo(task);
        String toBeAdded = taskToAdd.toString();
        boolean notAlreadyInside = taskListInternal.add(storage, toBeAdded);
        try {
            storage.writeListToFile(Duke.file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (notAlreadyInside) {
            return "Task " + toBeAdded + " has been added. SPARKTASTIC.";
        } else {
            return "It is already inside. WOWZA!";
        }
    }

    /**
     * Deletes the task at the target index of the list and returns a string to
     * notify the user.
     *
     * @param targetIndex Index of the task to be deleted.
     * @return String informing the user of the task's deletion.
     */
    String taskDeletedString(int targetIndex) {
        String toBeDeleted = TaskListInternal.lines.get(targetIndex);
        taskListInternal.delete(storage, targetIndex);
        return "Task" + toBeDeleted + " has been deleted. How sad.";
    }

    /**
     * Marks a task at the target index of the list as done and returns a string
     * to notify the user.
     *
     * @param targetIndex Index of the task to be marked as done.
     * @return String informing the user that the task has been marked done.
     */
    private String markedAsDoneString(int targetIndex) {
        String toBeDone = TaskListInternal.lines.get(targetIndex);
        boolean notAlreadyDone = taskListInternal.makeDone(storage, targetIndex);
        if (notAlreadyDone) {
            return "Task " + toBeDone + " is now marked as done. Well done.";
        } else {
            return "It is ALREADY DONE WOW!!!!!!";
        }
    }

    /**
     * Returns the task list in string form.
     *
     * @return String form of the task list or a message if the list is empty.
     */
    private String listStringForm() {
        int c = 1;
        String output = "";
        for (String line : TaskListInternal.lines) {
            output += (c + ". " + line + System.lineSeparator());
            c++;
        }
        if (c == 1) {
            return "The list is empty. How tragic.";
        }
        assert c== TaskListInternal.lines.size();
        return output;
    }

}
