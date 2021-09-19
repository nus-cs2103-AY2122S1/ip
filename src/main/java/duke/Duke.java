package duke;

import java.io.File;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A CLI bot which is based off Duke.
 *
 * @author mrmrinal
 */
public class Duke extends Application {

    private static final Storage STORAGE = new Storage("." + File.separator + "data"
            + File.separator + "duke.txt");
    private static final TaskList TASKS = new TaskList(STORAGE.loadIntoDuke());
    private static final Gui GUI = new Gui();

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @param input the input for which the bot has to make a response
     * @return The string that dukebot outputs
     */
    public String getResponse(String input) {
        RequestType userRequest;
        if (input.equals("bye")) {
            STORAGE.writeToFile(TASKS);
            return GUI.farewellMessage();
        } else {
            userRequest = duke.Parser.parse(input);
            switch (userRequest) {
            case FIND:
                int taskNumber = 5;
                return TASKS.find(input.substring(taskNumber));
            case UNUSUAL:
                return GUI.unusualRequest();
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
                return TASKS.list();
            }
        }
    }

    /**
     * Prints the following statement once an action is performed
     * @param userInput Name of task
     * @param actionType Type of action
     */
    public String sayAction(String userInput, String actionType) {
        assert actionType.equals("removed") || actionType.equals("added") || actionType.equals("updated")
                : "Unknown action type found in echo";
        return GUI.dukeResponse("Got it sir, I have " + actionType + " this task:\n "
                + userInput + "\nNow you have " + TASKS.getSize() + " tasks in the list.\n");

    }

    private String makeDone(String userInput) {
        try {
            return makeDoneHelper(userInput);
        } catch (NumberFormatException e) {
            return GUI.dukeResponse("Task number formatted incorrectly. Try again\n");
        } catch (StringIndexOutOfBoundsException e) {
            return GUI.dukeResponse("Enter number of the task you want to delete");
        }
    }

    private String makeDoneHelper(String userInput) {
        int doneFirstChar = 0;
        int doneLastChar = 4;
        assert userInput.substring(doneFirstChar, doneLastChar).equals("done") : "first 4 characters should be done";
        int task = Integer.parseInt(userInput.substring(5));
        int zeroTasks = 0;
        if (task > zeroTasks && task <= TASKS.getSize()) {
            TASKS.markDone(task - 1);
            String response;
            response = GUI.dukeResponse("One task down sir. Here is the task I checked off:\n");
            response = response + GUI.dukeResponse("    " + TASKS.getTask(task).toString() + "\n");
            return response;
        } else {
            String invalidTaskError = "You have entered an invalid task number Sir, please input again.\n";
            return GUI.dukeResponse(invalidTaskError);
        }
    }

    private String deleteTask(String userInput) {
        try {
            int taskCharAt = 7;
            int task = Integer.parseInt(userInput.substring(taskCharAt));
            int zeroTasks = 0;
            if (task > zeroTasks && task <= TASKS.getSize()) {
                Task t = TASKS.deleteTask(task);
                return sayAction(t.toString(), "removed");
            } else {
                String invalidTaskNumber = "You have entered an invalid task number Sir, please input again.\n";
                return GUI.dukeResponse(invalidTaskNumber);
            }
        } catch (NumberFormatException e) {
            String formattedIncorrectlyError = "Task number formatted incorrectly. Try again\n";
            return GUI.dukeResponse(formattedIncorrectlyError);
        }
    }

    private String updateTask(String userInput) {
        try {
            int taskNumberCharacter = 4;
            int task = Integer.parseInt(userInput.substring(userInput.indexOf("/no") + taskNumberCharacter));
            return updateTaskHelper(task, userInput);
        } catch (Exception e) {
            return "Error with input";
        }
    }

    private String updateTaskHelper(int task, String userInput) {
        if (task > 0 && task <= TASKS.getSize()) {
            int taskCharAt = 7;
            String newTask = userInput.substring(taskCharAt, userInput.indexOf("/no") - 1);
            RequestType taskType = duke.Parser.parse(newTask);
            if (taskType == RequestType.DEADLINE) {
                return updateDeadline(newTask, task);
            } else if (taskType == RequestType.TODO) {
                return updateTodo(newTask, task);
            } else if (taskType == RequestType.EVENT) {
                return updateEvent(newTask, task);
            }
            return "Enter a valid task";
        }

        return "Error with input. Please try again";
    }

    private String taskStatus(int index, Task task) {
        if (index == TASKS.getSize() + 1) {
            TASKS.addTask(task);
            STORAGE.addNewTask(task);
            return sayAction(task.toString(), "added");
        } else {
            TASKS.updateTask(task, index);
            STORAGE.writeToFile(TASKS);
            return sayAction(task.toString(), "updated");
        }
    }

    private String updateDeadline(String userInput, int index) {
        if (index > TASKS.getSize() + 1 || index < 1) {
            return "Enter a valid task number";
        } else if (userInput.indexOf("/by") < 11) {
            return GUI.dukeResponse("Enter a valid deadline activity description\n");
        } else if (userInput.length() <= userInput.indexOf("/by") + 4) {
            return GUI.dukeResponse("Enter a valid deadline time\n");
        } else {
            int deadlineTaskCharAt = 9;
            int deadlineTimeCharAt = 4;
            String description = userInput.substring(deadlineTaskCharAt, userInput.indexOf("/by") - 1);
            String by = userInput.substring(userInput.indexOf("/by") + deadlineTimeCharAt);
            return updateDeadlineHelper(description, by, index);
        }
    }

    private String updateDeadlineHelper(String description, String by, int index) {
        try {
            LocalDate.parse(by);
            Task t = new Deadline(description, by);
            return taskStatus(index, t);
        } catch (Exception e) {
            assert e.toString().startsWith("java.time.format.DateTimeParseException:")
                    : e.toString();
            return GUI.dukeResponse("Enter a valid date in the format yyyy-mm-dd\n");
        }
    }

    private String makeDeadlineTask(String userInput) {
        return updateDeadline(userInput, TASKS.getSize() + 1);
    }

    private String updateEvent(String userInput, int index) {
        int insufficientDescriptionChar = 8;
        int invalidTimeChar = 4;
        if (userInput.indexOf("/at") < insufficientDescriptionChar) {
            return GUI.dukeResponse("Enter a valid event activity description\n");
        } else if (userInput.length() <= userInput.indexOf("/at") + invalidTimeChar) {
            return GUI.dukeResponse("Enter a valid event time\n");
        } else {
            int descriptionStartChar = 6;
            String description = userInput.substring(descriptionStartChar, userInput.indexOf("/at") - 1);
            String at = userInput.substring(userInput.indexOf("/at") + invalidTimeChar);
            Task t = new Event(description, at);
            return taskStatus(index, t);
        }
    }

    private String makeEventTask(String userInput) {
        return updateEvent(userInput, TASKS.getSize() + 1);
    }

    private String updateTodo(String userInput, int index) {
        int todoDescriptionCharAt = 5;
        try {
            readActivity(userInput.substring(todoDescriptionCharAt));
            String description = userInput.substring(todoDescriptionCharAt);
            Task t = new ToDo(description);
            return taskStatus(index, t);
        } catch (DukeException e) {
            return GUI.dukeResponse(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            assert userInput.length() < todoDescriptionCharAt + 1
                    : "todo request was supposed to be smaller than 6 characters";
            return GUI.dukeResponse("Enter a valid todo activity\n");
        }
    }

    private String makeTodoTask(String userInput) {
        return updateTodo(userInput, TASKS.getSize() + 1);
    }

    private static void readActivity(String userTask) throws DukeException {
        if (userTask.length() <= 1) {
            String invalidActivityError = "Enter valid " + "todo" + " activity description\n";
            throw new DukeException(invalidActivityError);
        }
    }
}
