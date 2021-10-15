package me.yukun99.ip.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import me.yukun99.ip.tasks.Task;

/**
 * Handles searching of tasks by keyword.
 */
public class TaskFinder {
    private final HashMap<String, List<Task>> wordTaskMap = new HashMap<>();

    /**
     * Adds a new task to the TaskFinder instance.
     *
     * @param task Task to be added to the TaskFinder instance.
     * @param name Name of task to be added.
     */
    public void addTask(Task task, String name) {
        for (String word : parseWords(name)) {
            List<Task> tasks = new ArrayList<>();
            if (wordTaskMap.containsKey(word)) {
                tasks = wordTaskMap.get(word);
            }
            tasks.add(task);
            wordTaskMap.put(word, tasks);
        }
    }

    /**
     * Deletes a task from the TaskFinder instance.
     *
     * @param task Task to be deleted from the TaskFinder instance.
     * @param name Name of task to be deleted.
     */
    public void deleteTask(Task task, String name) {
        for (String word : parseWords(name)) {
            if (!wordTaskMap.containsKey(word)) {
                continue;
            }
            List<Task> tasks = wordTaskMap.get(word);
            tasks.remove(task);
            wordTaskMap.put(word, tasks);
        }
    }

    /**
     * Deletes all tasks from the TaskFinder instance.
     */
    public void deleteAllTasks() {
        this.wordTaskMap.clear();
    }

    /**
     * Parses the words in a task name.
     *
     * @param name Task name to be parsed.
     * @return List containing all the words in the task name.
     */
    private List<String> parseWords(String name) {
        return new ArrayList<>(Arrays.asList(name.split(" ")));
    }

    /**
     * Gets String representation of all tasks with specified keyword in their name.
     *
     * @param word Keyword to find tasks by.
     * @return String representation of all tasks with specicied keyword in their name.
     */
    public String findTasksByWord(String word) {
        StringBuilder message = new StringBuilder("\nHere are your tasks that contain the word '" + word + "'.");
        List<Task> tasks = new ArrayList<>();
        if (wordTaskMap.containsKey(word)) {
            tasks = wordTaskMap.get(word);
        }
        for (int i = 0; i < tasks.size(); ++i) {
            message.append("\n ").append(i + 1).append(".").append(tasks.get(i));
        }
        if (tasks.size() == 0) {
            message
                    .append("\n  Is this a joke? You have no tasks containing the word '")
                    .append(word)
                    .append("', idiot.");
        }
        return message.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskFinder that = (TaskFinder) o;
        return wordTaskMap.equals(that.wordTaskMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordTaskMap);
    }
}
