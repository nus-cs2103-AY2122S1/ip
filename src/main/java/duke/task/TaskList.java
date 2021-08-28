package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import duke.command.MalformedCommandException;
import duke.storage.StorageException;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void loadTasks(File file) throws StorageException {
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] splitTaskString = taskString.split(Task.STORAGE_DELIMITER);
                Task task = null;

                switch (splitTaskString[0]) {
                case Todo.IDENTIFIER:
                    task = new Todo(splitTaskString[1], Boolean.parseBoolean(splitTaskString[2]));
                    break;
                case Event.IDENTIFIER:
                    task = new Event(splitTaskString[1], Boolean.parseBoolean(splitTaskString[2]), splitTaskString[3]);
                    break;
                case Deadline.IDENTIFIER:
                    task =
                        new Deadline(splitTaskString[1], Boolean.parseBoolean(splitTaskString[2]), splitTaskString[3]);
                    break;
                }

                if(task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new StorageException("Txt file for loading tasks is wrongly formatted. Some tasks were not loaded");
        }
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public Task markTaskDone(int taskIndex) throws MalformedCommandException {
        try {
            Task task = tasks.get(taskIndex);
            task.markDone();
            return task;
        } catch(IndexOutOfBoundsException e) {

            throw new MalformedCommandException("You only have " + numTasks() + " tasks currently. " +
                "Please provide a task index from that list");
        }

    }

    public Task delete(int taskIndex) throws MalformedCommandException {
        try {
            return tasks.remove(taskIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new MalformedCommandException("You only have " + numTasks() + " tasks currently. " +
                "Please provide a task index from that list");
        }

    }

    public int numTasks() {
        return tasks.size();
    }

    /**
     * Returns a TaskList with filtered tasks matching the search term.
     *
     * @param searchTerm Serch term to filter tasks.
     * @return TaskList with filtered tasks matching the search term.
     */
    public TaskList findTasks(String searchTerm) {
        TaskList filteredTaskList = new TaskList();
        List<Task> filteredTasks = new ArrayList<>(tasks);
        filteredTasks.removeIf(task -> !(task.toString()).contains(searchTerm));
        filteredTaskList.setTasks(filteredTasks);
        return filteredTaskList;
    }

    @Override
    public String toString() {
        StringBuilder taskListStringRepresentation = new StringBuilder();
        for(int i = 0; i < numTasks(); i++) {
            taskListStringRepresentation.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return taskListStringRepresentation.toString();
    }

    public String toStorageFormat() {
        StringBuilder taskStorageRepresentation = new StringBuilder();
        for(int i = 0; i < numTasks(); i++) {
            taskStorageRepresentation.append(tasks.get(i).toStorageFormat()).append("\n");
        }
        return taskStorageRepresentation.toString();
    }

    private void setTasks(List<Task> newTasks) {
        tasks = newTasks;
    }
}
