package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task addToDo(String input) throws DukeException.NoNameException, DukeException.NoNameException {
        if (input.length() < 5) {
            throw new DukeException.NoNameException("Duke says: You can't add a task with no name");
        }
        Task.ToDo newToDo = new Task.ToDo(input.substring(5));
        tasks.add(newToDo);
        return newToDo;
    }

    public Task addDeadline(String input) throws DukeException.NoTimeSpecifiedException, DukeException.NoNameException {
        Task addedDeadline = null;
        try {
            Task.Deadline newDeadline = new Task.Deadline(input.substring(9).split(" /")[0],
                    LocalDateTime.parse(input.substring(9).split(" /")[1],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            tasks.add(newDeadline);
            addedDeadline = newDeadline;
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new DukeException.NoTimeSpecifiedException("Duke.Duke says: Please include a deadline");
        } catch (DateTimeParseException e) {
            System.out.println("Duke says: Please use the format YYYY-MM-DD HH:MM when entering deadline \n " +
                    "E.g. 2021-08-28 18:30");
        }
        return addedDeadline;
    }

    public Task addEvent(String input) throws DukeException.NoTimeSpecifiedException, DukeException.NoNameException {
        Task addedEvent = null;
        try {
            Task.Event newEvent = new Task.Event(input.substring(6).split(" /")[0],
                    LocalDateTime.parse(input.substring(6).split(" /")[1],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            tasks.add(newEvent);
            addedEvent = newEvent;
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new DukeException.NoTimeSpecifiedException("Duke.Duke says: Please include a time");
        } catch (DateTimeParseException e) {
            System.out.println("Duke says: Please use the format YYYY-MM-DD HH:MM when entering when the event is \n " +
                    "E.g. 2021-08-28T18:30");
        }
        return addedEvent;
    }

    public Task completeTask(String input) throws DukeException.InvalidInputException {
        Task completedTask = null;
        try {
            int taskIndex = Integer.parseInt(String.valueOf(input.charAt(5)));
            if (!(taskIndex > tasks.size())) {
                completedTask = tasks.get(taskIndex - 1);
                completedTask.completeTask();
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException.InvalidInputException("Duke says: Please enter the index of the completed task");
        }
        return completedTask;
    }

    public Task deleteTask(String input) throws DukeException.InvalidInputException {
        Task deletedTask = null;
        try {
            int taskIndex = Integer.parseInt(String.valueOf(input.charAt(7)));

            if (!(taskIndex > tasks.size())) {
                System.out.println("Duke says: You have deleted the task " +
                        tasks.get(taskIndex - 1).getName());
                deletedTask = tasks.remove(taskIndex - 1);
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new DukeException.InvalidInputException("Duke says: Please enter the index of the task you want to delete");
        }
        return deletedTask;
    }

}
