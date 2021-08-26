package duke.task;

import duke.util.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.util.Parser;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public int listLength() {
        return tasks.size();
    }

    public String addTaskToList(Task task) {
        tasks.add(task);
        return ui.showTaskAdded(task, tasks.size());
    }

    public void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%s."+ tasks.get(i).toString() + "%n", i + 1);
        }
    }

    public String deleteFromList(int deleteNumber) {
        Task task = tasks.get(deleteNumber);
        tasks.remove(deleteNumber);
        return ui.showTaskDeleted(task, tasks.size());
    }

    public String setTaskAsDone(int doneNumber) {
        Task task = tasks.get(doneNumber);
        task.setDone();
        return ui.showTaskDone(task);
    }


    public String addDeadlineToList(String description, String by) throws DukeException {
        try {
            LocalDate date = Parser.parseDate(by);
            Deadline deadline = new Deadline(description, date);
            return addTaskToList(deadline);
        } catch (DateTimeParseException | ParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public String addEventToList(String description, String at) throws DukeException {
        try {
            LocalDate date = Parser.parseDate(at);
            Event event = new Event(description, date);
            return addTaskToList(event);
        } catch (DateTimeParseException | ParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public String addTodoToList(String description) {
        Todo todo = new Todo(description);
        return addTaskToList(todo);
    }

    public void saveTasksInStorage(FileWriter writer) throws IOException {
        for (Task tasks : tasks) {
            writer.write(tasks.saveTaskFormat() + System.lineSeparator());
        }
    }

    public String getTasks() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            list += String.format("\t%s.%s\n", i + 1, tasks.get(i).toString());
        }
        return list;
    }

}
