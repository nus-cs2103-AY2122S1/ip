package duke;
import java.time.LocalDate;
import java.util.ArrayList;
import duke.Task.*;

public class Parser {
    private String userInput;

    Parser(String userInput) {
        this.userInput = userInput;
    }
    public static void handleInput(String userInput, TaskList tasks) {
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
            int id = Integer.parseInt(userInput.substring(7));

            try {
                Task removedTask = tasks.getTaskById(id - 1);
                tasks.removeTaskById(id);
                Ui.displaySuccessfulRemoval(removedTask);
            } catch (Exception e) {
                Ui.displayErrorMessage(e);
            }
        } else if(userInput.startsWith("find")) {
            String keyword = userInput.substring(5);
            keyword = keyword.trim();
            try {
                TaskList filteredTask = tasks.find(keyword);
                Ui.displayTaskList(filteredTask);
            } catch (Exception e) {
                Ui.displayErrorMessage(e);
            }
        }
        else {
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

    public static Task handleTaskInput(String userInput) throws DukeException{
        if(userInput.startsWith("todo")) {
            int id = userInput.indexOf("todo") + 4;
            String task = userInput.substring(id);
            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Todo needs to have description !");
            }
            return new Todo(task, false);
        } else if(userInput.startsWith("deadline")) {
            int start_id = userInput.indexOf("deadline");
            int task_id = userInput.indexOf("/by");
            if(task_id == -1) {
                throw new DukeException("You need to specify at using /by !");
            }
            String task = userInput.substring(start_id + 9, task_id);
            String date = userInput.substring(task_id + 3);

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
            if(task_id == -1) {
                throw new DukeException("You need to specify at using /at !");
            }
            String task = userInput.substring(start_id + 6, task_id);
            String date = userInput.substring(task_id + 3);

            return new Event(task, false, LocalDate.parse(date.trim()));
        } else {
            throw new DukeException("I don't understand what you are talking about !");
        }
    }
}


