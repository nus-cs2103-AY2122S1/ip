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
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public int listLength() {
        return taskList.size();
    }

    public String addTaskToList(Task task) {
        taskList.add(task);
        return ui.showTaskAdded(task, taskList.size());
    }

    public void printTasksInList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.showTask(taskList.get(i), i + 1);
        }
    }

    public void findMatchingTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                ui.showTask(task, i);
                i++;
            }
        }
    }

    public String deleteFromList(int deleteNumber) {
        Task task = taskList.get(deleteNumber);
        taskList.remove(deleteNumber);
        return ui.showTaskDeleted(task, taskList.size());
    }

    public String setTaskAsDone(int doneNumber) {
        Task task = taskList.get(doneNumber);
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
        for (Task tasks : taskList) {
            writer.write(tasks.saveTaskFormat() + System.lineSeparator());
        }
    }

    public String getTasks() {
        String tasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += String.format("\t%s.%s\n", i + 1, taskList.get(i).toString());
        }
        return tasks;
    }

}
