package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

/**
 * Represents a helper class to save and retrieve task list.
 */
public class Storage {

    private static final String FILE_PATH = "./data/duke.txt";

    private static final Set<String> VALID_TASK_TYPES = Set.of("T", "D", "E");
    private static final Set<String> VALID_DONE_STATUSES = Set.of("0", "1");

    public static ArrayList<Task> retrieveTaskList() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String dataString = scanner.nextLine();
            Task task = convertStringToTask(dataString);
            taskList.add(task);
        }
        scanner.close();
        return taskList;
    }

    private static Task convertStringToTask(String taskString) throws IllegalArgumentException {
        // Example format: T | 1 | read book
        // Example format: D | 0 | return book | June 6th
        int firstBarIndex = taskString.indexOf("|");
        if (firstBarIndex == -1) {
            throw new IllegalArgumentException("No separator bar | found");
        }
        String taskType = taskString.substring(0,firstBarIndex).strip();
        if (!VALID_TASK_TYPES.contains(taskType)) {
            System.out.println(taskType);
            throw new IllegalArgumentException("Invalid Duke.Task Type (Expects: T, D or E)");
        }
        int secondBarIndex = taskString.indexOf("|", firstBarIndex + 1);
        if (secondBarIndex == -1) {
            throw new IllegalArgumentException("Second separator bar | not found");
        }
        String doneStatus = taskString.substring(firstBarIndex + 1, secondBarIndex).strip();
        if (!VALID_DONE_STATUSES.contains(doneStatus)) {
            System.out.println(doneStatus);
            throw new IllegalArgumentException("Invalid Done Status (Expects: 1 or 0)");
        }
        if (taskType.equals("T")) {
            String description = taskString.substring(secondBarIndex + 1).strip();
            return new Todo(description, doneStatus.equals("1"));
        }
        int lastBarIndex = taskString.lastIndexOf("|");
        assert lastBarIndex != -1; // There should at least be 1 bar
        if (secondBarIndex == lastBarIndex) {
            throw new IllegalArgumentException("Last separator bar | not found");
        }
        String description = taskString.substring(secondBarIndex + 1, lastBarIndex).strip();
        String timing = taskString.substring(lastBarIndex + 1).strip();
        if (taskType.equals("D")) {
            return new Deadline(description, doneStatus.equals("1"), timing);
        } else {
            assert taskType.equals("E");
            return new Event(description, doneStatus.equals("1"), timing);
        }
    }

    public static void saveTaskList(List<Task> taskList) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        assert file.exists();
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            fileWriter.write(task.convertToSavableString() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
