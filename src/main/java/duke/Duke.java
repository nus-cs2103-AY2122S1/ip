package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Event;

import java.io.File;
import java.time.LocalDate;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * A CLI bot which is based off Duke.
 *
 * @author mrmrinal
 */
public class Duke extends Application{

    private final Storage storage;
    private final TaskList tasks;
    private Ui ui;
    private Gui gui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userimg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeimg = new Image(this.getClass().getResourceAsStream("/images/nestor.png"));

     public enum RequestType {
        DEFAULT,
        FIND,
        DONE,
        DELETE,
        DEADLINE,
        EVENT,
        TODO,
        UNUSUAL
    }


    /**
     * Instantiates a new Dukebot that the user can input commands to.
     * The filePath argument must specify an absolute location to where
     * the duke.txt file is stored.
     *
     * @param filePath String representation of storage path
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        gui = new Gui();
        tasks = new TaskList(storage.loadIntoDuke());
    }
    
    public Duke(){
        storage = new Storage("." +  File.separator + "data"
                + File.separator + "duke.txt");
        ui = new Ui();
        gui = new Gui();
        tasks = new TaskList(storage.loadIntoDuke());
    }
    


//    /**
//     * Main method that is responsible for launching the bot.
//     */
//    public static void main(String[] args) {
//        new Duke("." +  File.separator + "data"
//                + File.separator + "duke.txt").run();
//    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this. 
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dukeGreeting();
        //Step 3. Add functionality to handle user input.
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
        
        

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        if(userInput.getText().equals("bye")){
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(userimg)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(dukeimg))
            );
            Platform.exit();
            System.exit(0);
            
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userimg)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeimg))
        );
        
        
        userInput.clear();
    }
    
    private void dukeGreeting(){
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(gui.dukeGreeting()), new ImageView(dukeimg))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @return The string that dukebot outputs
     */
    String getResponse(String input) {
        RequestType userRequest;
        if(input.equals("bye")){
            storage.writeToFile(tasks);
            return gui.farewellMessage();
        } else {
            userRequest = duke.Parser.parse(input);
            
            switch (userRequest) {
                case DEFAULT:
                    return tasks.list();
                case FIND:
                    return tasks.find(input.substring(5));
                case UNUSUAL:
                    return gui.unusualRequest();
                case DONE:
                    return done(input);
                case DELETE:
                    return delete(input);
                case DEADLINE:
                    return deadline(input);
                case EVENT:
                    return event(input);
                case TODO:
                    return todo(input);
            }
        }
        return "";
    }
    

    /**
     * Prints the following statement once an action is performed.
     * 
     * @param userInput Name of task
     * @param actionType Type of action
     */
    public String echo(String userInput, String actionType) {
        return gui.dukeResponse("Got it sir, I have " + actionType + " this task:\n "
                + userInput + "\nNow you have " + tasks.getSize() + " tasks in the list.\n");

    }
    
    private String done(String userInput) {
        try {
            int task = Integer.parseInt(userInput.substring(5));
            if (task > 0 && task <= tasks.getSize()) {
                tasks.markDone(task - 1);
                String r;
                r = gui.dukeResponse("One task down sir. Here is the task I checked off:\n");
                r = r+ gui.dukeResponse("    " + tasks.getTask(task).toString() + "\n");
                return r;
            } else {
                return gui.dukeResponse("You have entered an invalid task number Sir, please input again.\n");
            }
        } catch (NumberFormatException e){
            return gui.dukeResponse("Task number formatted incorrectly. Try again\n");
        } catch (StringIndexOutOfBoundsException e){
            return gui.dukeResponse("Enter number of the task you want to delete");
        }
    }
    
    private String delete(String userInput) {
        try {
            int task = Integer.parseInt(userInput.substring(7));
            if(task > 0 && task <= tasks.getSize()){
                Task t = tasks.deleteTask(task);
                return echo(t.toString(), "removed");
            } else {
                return gui.dukeResponse("You have entered an invalid task number Sir, please input again.\n");
            }
        } catch (NumberFormatException e){
            return gui.dukeResponse("Task number formatted incorrectly. Try again\n");
        }
    }
    
    private String deadline(String userInput) {
        if (userInput.indexOf("/by")  < 11 ) {
            return gui.dukeResponse("Enter a valid deadline activity description\n");
        } else if (userInput.length() <= userInput.indexOf("/by") +  4) {
            return gui.dukeResponse("Enter a valid deadline time\n");
        } else {
            String description = userInput.substring(9, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);

            try {
                LocalDate time = LocalDate.parse(by);
                Task t =  new Deadline(description, by);
                tasks.addTask(t);
                storage.addNewTask(t);
                return echo(t.toString(), "added");
            } catch (Exception e) {
                return gui.dukeResponse("Enter a valid date in the format yyyy-mm-dd\n");
            }
        }
    }
    
    private String event(String userInput) {
        if (userInput.indexOf("/at")  < 8 ) {
            return gui.dukeResponse("Enter a valid event activity description\n");
        } else if (userInput.length() <= userInput.indexOf("/at") +  4) {
            return gui.dukeResponse("Enter a valid event time\n");
        } else {
            String description = userInput.substring(6, userInput.indexOf("/at") - 1);
            String at = userInput.substring(userInput.indexOf("/at") + 4);
            Task t =  new Event(description, at);
            tasks.addTask(t);
            storage.addNewTask(t);
            return echo(t.toString(), "added");
            
        }
    }
    
    private String todo(String userInput) {
        try {
            readActivity(userInput.substring(5), "todo");
            String description = userInput.substring(5);
            Task t = new ToDo(description);
            tasks.addTask(t);
            storage.addNewTask(t);
            return echo(t.toString(), "added");
        } catch (DukeException e) {
            return gui.dukeResponse(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            return gui.dukeResponse("Enter a valid todo activity\n");
        }
    }
    
    private static void readActivity(String userTask, 
                                     String taskType) throws DukeException {
        if (userTask.length() <= 1) {
            throw new DukeException("Enter valid " + taskType + " activity\n");
        }
    }
    
}
