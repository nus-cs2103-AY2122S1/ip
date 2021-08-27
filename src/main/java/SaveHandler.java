import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

public class SaveHandler {

    private static final String FILE_PATH = "./data/duke.txt";

    public static TaskList retrieveTaskList() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(file);
        TaskList taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String dataString = scanner.nextLine();
            System.out.println(dataString);
            Task task = convertStringToTask(dataString);
            taskList.add(task);
        }
        scanner.close();
        return taskList;
    }

    private static final Set<String> VALID_TASK_TYPE = Set.of("T", "D", "E");
    private static final Set<String> VALID_DONE_STATUS = Set.of("0", "1");

    private static Task convertStringToTask(String taskString) throws IllegalArgumentException {
        // Example format: T | 1 | read book
        // Example format: D | 0 | return book | June 6th
        int firstBarIndex = taskString.indexOf("|");
        if (firstBarIndex == -1) {
            throw new IllegalArgumentException("No separator bar | found");
        }
        String taskType = taskString.substring(0,firstBarIndex).strip();
        if (!VALID_TASK_TYPE.contains(taskType)) {
            System.out.println(taskType);
            throw new IllegalArgumentException("Invalid Task Type (Expects: T, D or E)");
        }
        int secondBarIndex = taskString.indexOf("|", firstBarIndex + 1);
        if (secondBarIndex == -1) {
            throw new IllegalArgumentException("Second separator bar | not found");
        }
        String doneStatus = taskString.substring(firstBarIndex + 1,secondBarIndex).strip();
        if (!VALID_DONE_STATUS.contains(doneStatus)) {
            System.out.println(doneStatus);
            throw new IllegalArgumentException("Invalid Done Status (Expects: 1 or 0)");
        }
        if (taskType == "T") {
            String description = taskString.substring(secondBarIndex + 1).strip();
            return new Todo(description, doneStatus == "1" ? true : false);
        }
        int lastBarIndex = taskString.lastIndexOf("|");
        assert lastBarIndex != -1; // There should at least be 1 bar
        if (secondBarIndex == lastBarIndex) {
            throw new IllegalArgumentException("Last separator bar | not found");
        }
        String description = taskString.substring(secondBarIndex + 1, lastBarIndex).strip();
        String timing = taskString.substring(lastBarIndex + 1).strip();
        if (taskType == "D") {
            return new Deadline(description, doneStatus == "1" ? true : false, timing);
        } else {
            assert taskType == "E";
            return new Event(description, doneStatus == "1" ? true : false, timing);
        }
    }

    public static void saveTaskList(List<Task> taskList) throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            fileWriter.write(task.serialise() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
