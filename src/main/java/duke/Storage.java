package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createDirIfNotExists() {
        Path path = Paths.get("data/");

        // check if data directory exists in the current working directory
        // if doesn't, create the directory
        if (!Files.exists(path)) {
            new File("data/").mkdir();
        }

    }

    public void writeToFile(List<Task> items) {
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

            if (taskText.charAt(4) == 'X') {
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
        int index = taskText.lastIndexOf(" (by: ");
        String description = taskText.substring(7, index);
        String date = taskText.substring(index + 6, taskText.length() - 1);
        date = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyy")).toString();
        return new Deadline(description, date);

    }

    private Event addEvent(String taskText) {

        // [E][ ] interview meeting (at: Aug 7 2021 9:00 PM)

        int index = taskText.lastIndexOf(" (at: ");
        String description = taskText.substring(7, index);
        String dateAndTime = taskText.substring(index + 6, taskText.length() - 1);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        String localDateTime = LocalDateTime.parse(dateAndTime, format).toString();

        String[] dateArr = localDateTime.split("T");
        String date = dateArr[0];
        String time = dateArr[1];

        return new Event(description, date, time);

    }

}
