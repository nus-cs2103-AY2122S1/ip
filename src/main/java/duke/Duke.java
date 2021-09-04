package duke;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Event;

import java.io.File;
import java.time.LocalDate;

/**
 * A CLI bot which is based off Duke.
 *
 * @author mrmrinal
 */
public class Duke extends Application {

    private final Storage storage;
    private final TaskList tasks;
    private final Gui gui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image userimg = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeimg = new Image(this.getClass().getResourceAsStream("/images/nestor.png"));

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

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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
        gui = new Gui();
        tasks = new TaskList(storage.loadIntoDuke());
    }

    public Duke() {
        storage = new Storage("." +  File.separator + "data"
                + File.separator + "duke.txt");
        gui = new Gui();
        tasks = new TaskList(storage.loadIntoDuke());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @param input the input for which the bot has to make a response
     * @return The string that dukebot outputs
     */
    String getResponse(String input) {
        RequestType userRequest;
        if (input.equals("bye")) {
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
                    return makeDone(input);
                case DELETE:
                    return deleteTask(input);
                case DEADLINE:
                    return deadline(input);
                case EVENT:
                    return event(input);
                case TODO:
                    return todo(input);
                default:
                    return tasks.list();
            }
        }
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
    
    private String makeDone(String userInput) {
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
    
    private String deleteTask(String userInput) {
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
            readActivity(userInput.substring(5));
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
    
    private static void readActivity(String userTask) throws DukeException {
        if (userTask.length() <= 1) {
            throw new DukeException("Enter valid " + "todo" + " activity description\n");
        }
    }
    
}
