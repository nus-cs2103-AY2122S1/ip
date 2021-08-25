package duke.task;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;



public class TaskList {
    private ArrayList<Task> tasks;
    private final int todoLength = 3;
    private final int deadlineLength = 4;
    private final int eventLength = 4;

    public TaskList(ArrayList<String> loadingStrings) throws DukeException {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < loadingStrings.size(); i++) {
            String taskString = loadingStrings.get(i);
            String[] taskStringArr = taskString.split("\\|");
            String type = taskStringArr[0];
            Task task;
            switch (type) {
                case "T":
                    if (taskStringArr.length != todoLength){
                        throw new DukeException("ERROR READING FROM STORAGE");
                    }
                    task = new Todo(taskStringArr[2]);
                    break;
                case "D":
                    if (taskStringArr.length != deadlineLength){
                        throw new DukeException("ERROR READING FROM STORAGE");
                    }
                    String[] deadlineDateTimeArr = taskStringArr[3].split(" ");
                    String deadlineDateString = deadlineDateTimeArr[0];
                    String deadlineTimeString = deadlineDateTimeArr[1];
                    task = new Deadline(taskStringArr[2], deadlineDateString, deadlineTimeString);
                    break;
                case "E":
                    if (taskStringArr.length != eventLength){
                        throw new DukeException("ERROR READING FROM STORAGE");
                    }
                    String[] eventDateTimeArr = taskStringArr[3].split(" ");
                    String eventDateString = eventDateTimeArr[0];
                    String eventTimeString = eventDateTimeArr[1];
                    task = new Event(taskStringArr[2], eventDateString, eventTimeString);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            if (taskStringArr[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }

    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(int delIndex) {
        Task task = tasks.get(delIndex);
        tasks.remove(delIndex);
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void addTask(Task task) throws DukeException {
        this.tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks from the TaskList whose description matches the given input
     * @param input the given input.
     * @return An ArrayList of tasks that match the given input.
     */
    public ArrayList<Task> find(String input) {
        ArrayList<Task> res = new ArrayList<>();
        String lowerInput = input.toLowerCase();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String lowerDescription = task.getDescription().toLowerCase();
            if (lowerDescription.contains(lowerInput)) {
                res.add(task);
            }
        }
        return res;
    }
}
