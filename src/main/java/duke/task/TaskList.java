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
    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList(ArrayList<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public TaskList(Ui ui) {
        this(new ArrayList<>(), ui);
    }

    public int listLength() {
        return taskList.size();
    }

    public void addTaskToList(Task task) {
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
    }

    public void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("\t%s."+ taskList.get(i).toString() + "%n", i + 1);
        }
    }

    public void deleteFromList(int deleteNumber) throws DukeException {
        Task task = taskList.get(deleteNumber);
        taskList.remove(deleteNumber);
        ui.showTaskDeleted(task, taskList.size());
    }

    public void setTaskAsDone(int doneNumber) throws DukeException {
        Task task = taskList.get(doneNumber);
        task.setDone();
        ui.showTaskDone(task);
    }


    public void addDeadlineToList(String description, String by) throws DukeException {
        try {
            LocalDate date = Parser.parseDate(by);
            Deadline deadline = new Deadline(description, date);
            addTaskToList(deadline);
        } catch (DateTimeParseException | ParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public void addEventToList(String description, String at) throws DukeException {
        try {
            LocalDate date = Parser.parseDate(at);
            Event event = new Event(description, date);
            addTaskToList(event);
        } catch (DateTimeParseException | ParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    public void addTodoToList(String description) {
        Todo todo = new Todo(description);
        addTaskToList(todo);
    }

    public void saveTasksInStorage(FileWriter writer) throws IOException {
        for (Task tasks : taskList) {
            writer.write(tasks.saveTaskFormat() + System.lineSeparator());
        }
    }

}
