package duke;
import java.time.LocalDate;

import duke.Task.*;

/**
 * Class to handle parsing of user input into Tasks / Commands
 */
public class Parser {

    /** Method to handle userInput and modify the specified task list
     *
     * @param userInput, string of user input
     * @param tasks task list to be modified accordingly
     */
    public static void handleInput(String userInput, TaskList tasks) {
        assert userInput != null : "should have user Input";
        if (userInput.equals("bye")) {
            Ui.displayQuitMessage();
            return;
        } else if (userInput.equals("list")) {
            Ui.displayTaskList(tasks);
        } else if (userInput.startsWith("done")) {
            int id = Integer.parseInt(userInput.substring(5));
            Task currTask = null;
            try {
                currTask = tasks.getTaskById(id - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
            if (currTask != null) {
                currTask.markAsDone();
                Ui.displayMarkedTaskAsDone(currTask);
            }

        } else if (userInput.startsWith("delete")) {
            int DELETE_CHARACTER_LENGTH = 6;

            int id = Integer.parseInt(userInput.substring(DELETE_CHARACTER_LENGTH).trim());

            try {
                Task removedTask = tasks.getTaskById(id - 1);
                tasks.removeTaskById(id - 1);
                Ui.displaySuccessfulRemoval(removedTask);
            } catch (Exception e) {
                Ui.displayErrorMessage(e);
            }
        } else if(userInput.startsWith("find")) {
            int FIND_CHARACTER_LENGTH = 4;
            String keyword = userInput.substring(5);
            keyword = keyword.trim();
            try {
                TaskList filteredTask = tasks.find(keyword);
                Ui.displayTaskList(filteredTask);
            } catch (Exception e) {
                Ui.displayErrorMessage(e);
            }
        } else if(userInput.startsWith("sort")){
            tasks.sort();
            Ui.displayTaskList(tasks);
        } else {
            Task newTask = null;
            try {
                newTask = handleTaskInput(userInput);
            } catch (DukeException e) {
                Ui.displayErrorMessage(e);
            }
            if (newTask != null) {
                tasks.addTask(newTask);
                System.out.println("Duke: Added Task " + userInput);

            }
            Ui.displayLineBreak();
        }
    }

    /**
     * Returns a Task Object that is parsed from the string of user task Input.
     * The method might return an exception when the user task input is invalid.
     *
     * @param userInput, the string containing a task
     * @return task object based on the string input
     * @throws DukeException
     */

    public static Task handleTaskInput(String userInput) throws DukeException{
        assert userInput != null : "should have user Input";
        if(userInput.startsWith("todo")) {
            int TODO_COMMAND_LENGTH = 4;
            int id = userInput.indexOf("todo") + TODO_COMMAND_LENGTH;
            String task = userInput.substring(id);
            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Todo needs to have description !");
            }
            return new Todo(task, false);
        } else if(userInput.startsWith("deadline")) {
            int start_id = userInput.indexOf("deadline");
            int task_id = userInput.indexOf("/by");
            int DEADLINE_COMMAND_LENGTH = 9;
            int BY_SPECIFIER_LENGTH = 3;
            if(task_id == -1) {
                throw new DukeException("You need to specify at using /by !");
            }
            String task = userInput.substring(start_id + DEADLINE_COMMAND_LENGTH, task_id);
            String date = userInput.substring(task_id + BY_SPECIFIER_LENGTH);

            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Task needs to have description !");
            }
            if(date.replaceAll("\\s+","").equals("")) {
                //if remaining string is whitespace or empty
                throw new DukeException("deadline needs to have dates !");
            }
            return new Deadline(task, false, LocalDate.parse(date.trim()));
        } else if(userInput.startsWith("event")) {
            int start_id = userInput.indexOf("event");
            int task_id = userInput.indexOf("/at");
            int EVENT_COMMAND_LENGTH = 6;
            int AT_SPECIFIER_LENGTH = 3;
            if(task_id == -1) {
                throw new DukeException("You need to specify at using /at !");
            }
            String task = userInput.substring(start_id + EVENT_COMMAND_LENGTH, task_id);
            String date = userInput.substring(task_id + AT_SPECIFIER_LENGTH);

            return new Event(task, false, LocalDate.parse(date.trim()));
        } else {
            throw new DukeException("I don't understand what you are talking about !");
        }
    }
}


