package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<String> taskListStrings) {
        this.taskList = convertToTasks(taskListStrings);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void find(String searchTerm, Ui ui) throws DukeException {
        if (searchTerm.isEmpty()) {
            throw new DukeException("Please provide a search term after the find command in the following format: find searchterm");
        }
        try {
            List<Task> matchingTaskList = this.taskList.stream()
                    .filter(task -> task.toString().contains(searchTerm))
                    .collect(Collectors.toList());
            ui.showMatchingTasks(new ArrayList<Task>(matchingTaskList));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please provide a search term after the find command in the following format: find searchterm");
        }
    }

    public void done(String num, Storage storage, Ui ui) throws DukeException {

        try {
            int listNum = Integer.parseInt(num);
            this.taskList.get(listNum - 1).setIsDone(true);
            Task doneTask = this.taskList.get(listNum - 1);
            storage.replaceFileLine(doneTask.getFileString(), listNum - 1);
            ui.doneMessage(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a number after the done command that is within the total number of tasks: " + this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The done command needs a number after it in the following format: done number");
        }
    }

    public void delete(String num, Storage storage, Ui ui) throws DukeException {
        try {
            int listNum = Integer.parseInt(num);
            Task deletedTask = this.taskList.remove(listNum - 1);
            storage.deleteFileLine(listNum - 1);
            ui.deleteMessage(deletedTask);
            ui.taskListSize(this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a number after the delete command that is within the total number of tasks: " + this.taskList.size());
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The delete command needs a number after it in the following format: delete number");
        }
    }

    public void todo(String description, Storage storage, Ui ui) {
        Task addedTask = new Todo(description);
        this.taskList.add(addedTask);
        storage.appendToFile(addedTask.getFileString());
        ui.addMessage(addedTask);
        ui.taskListSize(this.taskList.size());
    }


    public void deadline(String description, Storage storage, Ui ui) throws DukeException {
        try {
            String newDescription = description.substring(0, description.indexOf(" /by "));
            String by = description.substring(description.indexOf("/by ") + 4);
            Task addedTask = new Deadline(newDescription, by);
            this.taskList.add(addedTask);
            storage.appendToFile(addedTask.getFileString());
            ui.addMessage(addedTask);
            ui.taskListSize(this.taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write your date and time in the following format: " +
                    "D/MM/YYYY HHMM");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please write your deadline command in the following format: " +
                    "deadline task /by datetime");
        }
    }

    public void event(String description, Storage storage, Ui ui) throws DukeException {
        try {
            String newDescription = description.substring(0, description.indexOf(" /at "));
            String at = description.substring(description.indexOf("/at ") + 4);
            Task addedTask = new Event(newDescription, at);
            this.taskList.add(addedTask);
            storage.appendToFile(addedTask.getFileString());
            ui.addMessage(addedTask);
            ui.taskListSize(this.taskList.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please write your date and time in the following format: " +
                    "D/MM/YYYY HHMM");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please write your event command in the following format: " +
                    "event task /at datetime");
        }
    }


    private ArrayList<Task> convertToTasks(ArrayList<String> taskListStrings) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String ts : taskListStrings) {
            String[] tsData = ts.split(" \\| ");
            String taskType = tsData[0];

            switch (taskType) {
                case "T":
                    tasks.add(new Todo(tsData[1], tsData[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(tsData[1], tsData[2], tsData[3]));
                    break;
                case "E":
                    tasks.add(new Event(tsData[1], tsData[2], tsData[3]));
                    break;
            }
        }

        return tasks;
    }

}
