package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Handle database data and operations for Duke.
 * Source of truth for the task list as well as able to write to and read from
 * the database as necessary.
 */
public class Storage {

    /**
     * Checks for the save file, and if it exists, read from it to restore tasks.
     * Relies on the Task class to handle conversions to and from a task object to
     * a string stored in the database.
     */
    public TaskList readFromDatabase() {
        String dukeLocation = System.getProperty("user.dir");
        Path filePath = Paths.get(dukeLocation, "data", "tasks");

        if (!Files.isRegularFile(filePath)) {
            return new TaskList(new ArrayList<>());
        }

        System.out.println("Reading last save  - " + filePath);
        ArrayList<String> failedParses = new ArrayList<>();

        ArrayList<Task> taskArr = new ArrayList<>();
        Stream<String> databaseToString;
        try {
            databaseToString = Files.lines(filePath);
            databaseToString.forEach(convertToTaskAndAppendTo(taskArr, failedParses));
            databaseToString.close();
        } catch (IOException e) {
            System.out.println("Save file could not be read. It will be wiped on the next save.");
        }

        if (failedParses.size() > 0) {
            String status = String.format(
                "Save file was read partially. %d tasks added, %d tasks not understood:\n",
                taskArr.size(),
                failedParses.size()
            );
            status += "Failed to parse\n:";
            status += failedParses.stream().map(x -> x + "\n").reduce("", (a, b) -> a + b);
            System.out.println(status);
        }
        return new TaskList(taskArr);
    }

    /**
     * Updates the user save file with the latest list of tasks.
     * If such a save file does not exist, it will create it.
     */
    public void writeToDatabase(TaskList taskList) {
        String dukeLocation = System.getProperty("user.dir");
        Path folderPath = Paths.get(dukeLocation, "data");
        Path filePath = Paths.get(dukeLocation, "data", "tasks");

        StringBuilder save = new StringBuilder();

        taskList
            .stream()
            .forEach(x -> save.append(x.taskToString()).append(System.lineSeparator()));
        byte[] saveResult = save.toString().getBytes();

        System.out.println("Saving tasks  - " + filePath);
        System.out.println("DEBUG: Text to be saved \n" + save);

        try {
            if (!Files.isDirectory(folderPath)) {
                Files.createDirectory(folderPath);
            }
            if (Files.isRegularFile(filePath)) {
                Files.delete(filePath);
            }
            Files.write(filePath, saveResult);
        } catch (IOException e) {
            // Potentially add why it failed (e.g. no write permissions in folder)
            System.out.println("File could not be saved. - " + e.getMessage());
        }
    }

    private static Consumer<String> convertToTaskAndAppendTo(ArrayList<Task> taskArr, ArrayList<String> failedParses) {
        return taskString -> {
            try {
                Task task = Task.stringToTask(taskString);
                taskArr.add(task);
            } catch (IllegalArgumentException e) {
                failedParses.add(taskString);
            }
        };
    }
}
