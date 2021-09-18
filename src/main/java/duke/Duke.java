package duke;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
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
    
    public enum RequestType {
        DEFAULT,
        FIND,
        DONE,
        DELETE,
        UPDATE,
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
                case UPDATE:
                    return updateTask(input);
                case DEADLINE:
                    return makeDeadlineTask(input);
                case EVENT:
                    return makeEventTask(input);
                case TODO:
                    return makeTodoTask(input);
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
    public String sayAction(String userInput, String actionType) {
        assert actionType.equals("removed") || actionType.equals("added")  || actionType.equals("updated")
                : "Unknown action type found in echo";
        return gui.dukeResponse("Got it sir, I have " + actionType + " this task:\n "
                + userInput + "\nNow you have " + tasks.getSize() + " tasks in the list.\n");

    }
    
    private String makeDone(String userInput) {
        try {
            assert userInput.substring(0, 4).equals("done") : "first 4 characters should be done";
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
                return sayAction(t.toString(), "removed");
            } else {
                return gui.dukeResponse("You have entered an invalid task number Sir, please input again.\n");
            }
        } catch (NumberFormatException e){
            return gui.dukeResponse("Task number formatted incorrectly. Try again\n");
        }
    }
    
    private String updateTask(String userInput){
        try{
            int task = Integer.parseInt(userInput.substring(userInput.indexOf("/no") +  4));
            if(task > 0 && task <= tasks.getSize()){
                String newTask = userInput.substring(7, userInput.indexOf("/no") - 1);
                
                RequestType taskType = duke.Parser.parse(newTask);
                
                if (taskType == RequestType.DEADLINE) {
                    return updateDeadline(newTask, task);
                } else if (taskType == RequestType.TODO) {
                    return updateTodo(newTask, task);
                } else if(taskType == RequestType.EVENT){
                    return updateEvent(newTask, task);
                } 
                return "Enter a valid task";
            }
        } catch (Exception e){
            return "Error with input";
        }
        return "Error with input. Please try again";
    }
    
    private String updateDeadline(String userInput, int index){
        if(index > tasks.getSize() || index < 1){
            return "Enter a valid task number";
        } else if (userInput.indexOf("/by")  < 11 ) {
            return gui.dukeResponse("Enter a valid deadline activity description\n");
        } else if (userInput.length() <= userInput.indexOf("/by") +  4) {
            return gui.dukeResponse("Enter a valid deadline time\n");
        } else {
            String description = userInput.substring(9, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + 4);

            try {
                LocalDate time = LocalDate.parse(by);
                Task t =  new Deadline(description, by);
                if(index == tasks.getSize() + 1){
                    tasks.addTask(t);
                    storage.addNewTask(t);
                    return sayAction(t.toString(), "added");
                } else {
                    tasks.updateTask(t, index);
                    storage.writeToFile(tasks);
                    return sayAction(t.toString(), "updated");
                }
                
            } catch (Exception e) {
                assert e.toString().startsWith("java.time.format.DateTimeParseException:")
                        : e.toString();
                return gui.dukeResponse("Enter a valid date in the format yyyy-mm-dd\n");
            }
        }
    }
    
    private String makeDeadlineTask(String userInput) {
        return updateDeadline(userInput, tasks.getSize() + 1);
    }
    
    private String updateEvent(String userInput, int index){
        if (userInput.indexOf("/at")  < 8 ) {
            return gui.dukeResponse("Enter a valid event activity description\n");
        } else if (userInput.length() <= userInput.indexOf("/at") +  4) {
            return gui.dukeResponse("Enter a valid event time\n");
        } else {
            String description = userInput.substring(6, userInput.indexOf("/at") - 1);
            String at = userInput.substring(userInput.indexOf("/at") + 4);
            Task t =  new Event(description, at);
            if (index == tasks.getSize() + 1) {
                tasks.addTask(t);
                storage.addNewTask(t);
                return sayAction(t.toString(), "added");
            } else {
                tasks.updateTask(t, index);
                storage.writeToFile(tasks);
                return sayAction(t.toString(), "updated");
            }

        }
    }
    
    private String makeEventTask(String userInput) {
        return updateEvent(userInput, tasks.getSize() + 1);
    }
    
    private String updateTodo(String userInput, int index){
        try {
            readActivity(userInput.substring(5));
            String description = userInput.substring(5);
            Task t = new ToDo(description);
            if (index == tasks.getSize() + 1) {
                tasks.addTask(t);
                storage.addNewTask(t);
                return sayAction(t.toString(), "added");
            } else {
                tasks.updateTask(t, index);
                storage.writeToFile(tasks);
                return sayAction(t.toString(), "updated");
            }
        } catch (DukeException e) {
            return gui.dukeResponse(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            assert userInput.length() < 6
                    : "todo request was supposed to be smaller than 6 characters";
            return gui.dukeResponse("Enter a valid todo activity\n");
        }
    }
    
    private String makeTodoTask(String userInput) {
        return updateTodo(userInput, tasks.getSize() + 1);
    }
    
    private static void readActivity(String userTask) throws DukeException {
        if (userTask.length() <= 1) {
            throw new DukeException("Enter valid " + "todo" + " activity description\n");
        }
    }
    
}
