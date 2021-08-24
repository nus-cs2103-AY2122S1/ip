package duke.task;

import duke.storage.Storage;
import duke.ui.UserInterface;
import duke.exception.DukeException;

import java.util.ArrayList;

public class TaskList {
    protected String description;
    private boolean isDone;
    protected static final Storage file = new Storage("data/tasks.txt");
    protected static final ArrayList<TaskList> tasks = new ArrayList<>();
    protected static final UserInterface userInterface = new UserInterface();

    protected TaskList(String description) {
        this.description = description;
        this.isDone = false;
        tasks.add(this);
    }

    public TaskList() {}

    public void setDone() {
        this.isDone = true;
        file.overwriteList(tasks);
    }

    public boolean markDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            userInterface.invalidIntegerWarning();
            return false;
        } else {
            tasks.get(index).setDone();
            return true;
        }
    }

    public void delete(int index) {
        tasks.remove(index);
        file.overwriteList(tasks);
    }

    public void add(String input) {
        input = input.trim();
        String emptyTimelineError = null;
        String[] splitTask = input.split(" ", 2);
        boolean emptyDescription = false;

        switch (splitTask[0]) {
        case "todo":
            if (splitTask.length == 1) {
                emptyDescription = true;
                break;
            }
            new Todo(splitTask[1], false);
            break;
        case "deadline":
            if (splitTask.length == 1) {
                emptyDescription = true;
                break;
            }

            String[] splitTime = splitTask[1].split("/", 2);
            if (splitTime.length == 1) {
                emptyTimelineError = "deadline";
                break;
            }

            new Deadline(splitTime[0], splitTime[1], false);
            break;
        case "event":
            if (splitTask.length == 1) {
                emptyDescription = true;
                break;
            }

            String[] splitTimeEvent = splitTask[1].split("/", 2);
            if (splitTimeEvent.length == 1) {
                emptyTimelineError = "event";
                break;
            }

            new Event(splitTimeEvent[0], splitTimeEvent[1], false);
            break;
        default:
            throw new DukeException.InvalidCommandException(input);
        }

        if (emptyDescription) {
            throw new DukeException.EmptyDescriptionException();
        } else if (emptyTimelineError != null) {
            throw new DukeException.EmptyTimelineDescription(emptyTimelineError);
        }
    }

    public TaskList addExisting(char taskChar, char taskStatus, String description, String timeline) {
        TaskList currentTask;

        if (taskChar == 'T') {
            currentTask = new Todo(description, true);
        } else if (taskChar == 'D') {
            currentTask = new Deadline(description, timeline, true);
        } else {
            currentTask = new Event(description, timeline, true);
        }

        if (taskStatus == 'X') {
            currentTask.setDone();
        }
        return currentTask;
    }

    public void loadArrayList() {
        file.loadFile();
        file.overwriteList(tasks);
    }

    public static TaskList getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            userInterface.invalidIntegerWarning();
            return null;
        } else {
            return tasks.get(index);
        }
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public static int listLength() {
        return tasks.size();
    }

    public static void displayList() {
        userInterface.taskListHeader();
        file.printTaskFile();
    }
}
