package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class that encapsulates file creation and writing.
 */
public class Storage {

    private String filePath;

    private final String DIRECTORY = "data/";

    private final int TASK_STATUS_INDEX = 4;

    private final int START_OF_DESC_INDEX = 7;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The file location that the data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createDirIfNotExists() {
        Path path = Paths.get(DIRECTORY);

        // check if data directory exists in the current working directory
        // if doesn't, create the directory
        if (!Files.exists(path)) {
            new File(DIRECTORY).mkdir();
        }

    }

    /**
     * Iterates through list of task items and
     * store them in file in the hard disk.
     *
     * @param items List of Task objects.
     */
    public void writeToFile(List<Task> items) {

        assert items != null : "Task list is not initialized!";

        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            String content = "";
            for (Task item : items) {
                content += item.toString() + "\n";
            }
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Loads tasks from file to a list of Task objects.
     *
     * @return A list of Task objects.
     * @throws IOException IOException
     */
    public List<Task> load() throws IOException {

        List<Task> tasksList = new ArrayList<>();

        this.createDirIfNotExists();

        File file = new File(filePath);

        // check if the duke.txt file is already created
        if (file.createNewFile()) {
            return tasksList;
        }

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String taskText;

        while ((taskText = reader.readLine()) != null) {
            Task currentTask;

            switch (taskText.charAt(1)) {
            case 'T':
                currentTask = this.addTodo(taskText.substring(7));
                break;
            case 'D':
                currentTask = this.addDeadline(taskText);
                break;
            case 'E':
                currentTask = this.addEvent(taskText);
                break;
            default:
                throw new IllegalArgumentException();
            }

            if (taskText.charAt(TASK_STATUS_INDEX) == 'X') {
                currentTask.markAsDone();
            }

            tasksList.add(currentTask);
        }

        reader.close();

        return tasksList;
    }

    private Todo addTodo(String description) {
        return new Todo(description);
    }

    private Deadline addDeadline(String taskText) {

        // [D][X] return laptop (by: Aug 3 2021)
        int lastIndexOfDesc = taskText.lastIndexOf(" (by: ");
        String description = taskText.substring(START_OF_DESC_INDEX, lastIndexOfDesc);
        String date = taskText.substring(lastIndexOfDesc + 6, taskText.length() - 1);
        date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyy")).toString();
        return new Deadline(description, date);

    }

    private Event addEvent(String taskText) {

        // [E][ ] interview meeting (at: Aug 7 2021 9:00 PM)
        int index = taskText.lastIndexOf(" (at: ");
        String description = taskText.substring(START_OF_DESC_INDEX, index);
        String dateAndTime = taskText.substring(index + 6, taskText.length() - 1);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        String localDateTime = LocalDateTime.parse(dateAndTime, format).toString();

        String[] dateArr = localDateTime.split("T");
        String date = dateArr[0];
        String time = dateArr[1];

        return new Event(description, date, time);

    }

}
