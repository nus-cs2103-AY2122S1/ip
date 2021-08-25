package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.stream.Stream;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> userTasks;

    public TaskList() {
        this.userTasks = new ArrayList<>();
    }

    public TaskList(Stream<String> tasks) {
        this.userTasks = new ArrayList<>();
        tasks.forEach(x -> userTasks.add(cleanData(x)));
    }

    public String[] getStringArr() {
        String[] tasks = new String[userTasks.size() + 1];
        for (int i = 0; i < userTasks.size(); i++) {
            int tempNum = i + 1;
            tasks[tempNum] = tempNum + ". " + userTasks.get(i);
        }
        return tasks;
    }

    public String markAsDone(int index) {
        userTasks.get(index).markAsDone();
        return "  " + userTasks.get(index).toString();
    }

    public String removeTask(int index) {
        String res = userTasks.get(index).toString();
        userTasks.remove(index);
        return "  " + res;
    }

    public String addToDo(String name) {
        userTasks.add(new ToDo(name));
        return "  " + userTasks.get(userTasks.size() - 1).toString();
    }

    public String addDeadline(String name, String due) {
        userTasks.add(new Deadline(name, due));
        return "  " + userTasks.get(userTasks.size() - 1).toString();
    }

    public String addEvent(String name, String time) {
        userTasks.add(new Event(name, time));
        return "  " + userTasks.get(userTasks.size() - 1).toString();
    }

    public String numTasks() {
        return "Now you have " + userTasks.size() + " tasks in the list.";
    }

    public String toSaveFormat() {
        String dataStr = "";
        for (int i = 0; i < userTasks.size() - 1; i++) {
            Task data = userTasks.get(i);
            dataStr += data.toSaveFormat() + "\n";
        }
        dataStr += userTasks.get(userTasks.size() - 1).toSaveFormat();
        return dataStr;
    }

    private Task cleanData(String data) {
        String[] dataParts = data.split("[|]");
        Task tempTask = new ToDo("temp");
        if (dataParts.length == 3) {
            tempTask = new ToDo(dataParts[2]);
        } else if (dataParts[0].equals("D")) {
            tempTask = new Deadline(dataParts[2], dataParts[3]);
        } else if (dataParts[0].equals("E")) {
            tempTask = new Event(dataParts[2], dataParts[3]);
        }

        //TODO: error handling

        if (dataParts[1].equals("1")) {
            tempTask.markAsDone();
        }

        return tempTask;
    }
}
