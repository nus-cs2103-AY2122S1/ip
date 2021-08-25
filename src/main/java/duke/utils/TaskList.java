package duke.utils;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final Consumer<String> loadDataAction = s -> {
        String[] loadedLine = s.split(",");
        char typeOfTask = loadedLine[0].charAt(0);
        boolean isDone = loadedLine[1].charAt(0) == '1';
        String description = loadedLine[2];
        Task loadedTask;
        switch (typeOfTask) {
        case 'T':
            loadedTask = new ToDos(description, isDone);
            tasks.add(loadedTask);
            break;
        case 'D':
            String byPart = loadedLine[3];
            loadedTask = new Deadlines(description, byPart, isDone);
            tasks.add(loadedTask);
            break;
        case 'E':
            String atPart = loadedLine[3];
            loadedTask = new Events(description, atPart, isDone);
            tasks.add(loadedTask);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + typeOfTask);
        }
    };

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }
    public void markTaskAsDone(int taskNum) {
        Task currTask = tasks.get(taskNum);
        currTask.markAsDone();
    }
    public TaskList(Stream<String> persistedData) {
        persistedData.forEach(loadDataAction);
    }

    public Task getTask(int taskNum) {
        return tasks.get(taskNum);
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a new instance of TaskList containing the tasks that matches the keyword.
     *
     * @param keyword the word in which to search within all the tasks.
     * @return a new instance of TaskList containing the tasks that matches the keyword.
     */
    public HashMap<String, Task> getMatchingTasks(String keyword) {
        HashMap<String, Task> matchingTasks = new HashMap<>();
        int counter = 1; // Start from 1 because that is how we print
        for (Task currTask: tasks) {
            if (currTask.toString().contains(keyword)) {
                matchingTasks.put(String.valueOf(counter), currTask);
            }
            counter += 1;
        }
        return matchingTasks;
    }
}
