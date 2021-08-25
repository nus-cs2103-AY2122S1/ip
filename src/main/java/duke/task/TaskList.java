package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskList {
    private final LinkedList<Task> tasks = new LinkedList<>();

    public TaskList(File file) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskCode = s.nextLine();
                tasks.add(decode(taskCode));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TaskList() {

    }

    public int getSize() {
        return tasks.size();
    }

    private Task decode(String taskCode) {
        String[] taskDetails = taskCode.split("\\|");
        String taskType = taskDetails[0];
        boolean done = taskDetails[1].equals("1");
        switch (taskType) {
            case "T":
                return new Todo(done, taskDetails[2]);
            case "E": {
                String info = taskDetails[3];
                return new Event(done, taskDetails[2], LocalDateTime.parse(info));
            }
            case "D": {
                String info = taskDetails[3];
                return new Deadline(done, taskDetails[2], LocalDateTime.parse(info));
            }
            default:
                return null;
        }
    }

    public String list() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return result.toString();
    }

    public Task markAsDone(int taskNumber) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }


    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    public String compileTasks() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(String.format("%s\n", task.encode()));
        }
        return result.toString();
    }

    public Task[] findTasksWithName(String searchTerm) {
        return tasks
                .stream()
                .parallel()
                .filter(task -> task.getTaskName().contains(searchTerm))
                .toArray(Task[]::new);
    }
}
