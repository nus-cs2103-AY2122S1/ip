import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        File dukeFile = new File(this.filePath);
        ArrayList<Task> tasksList = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(dukeFile);
            while (fileScanner.hasNext()) {
                String currentLine = fileScanner.nextLine();

                String[] taskStatus = currentLine.split(Pattern.quote(" | "));
                String taskType = taskStatus[0];
                String taskProgress = taskStatus[1];
                String taskDescription = taskStatus[2];
                Task taskToAdd;

                if (taskType.equals("T")) {
                    taskToAdd = new Todo(taskDescription);
                } else if (taskType.equals("D")) {
                    taskToAdd = new Deadline(taskDescription, LocalDate.parse(taskStatus[3]));
                } else {
                    taskToAdd = new Event(taskDescription, LocalDate.parse(taskStatus[3]));
                }

                if (taskProgress.equals("1")) {
                    taskToAdd.markAsDone();
                }

                tasksList.add(taskToAdd);
            }
            return tasksList;
        } catch (FileNotFoundException e) {
            // Checking if directory exists
            java.nio.file.Path directoryPath = Paths.get(System.getProperty("user.home"), "data");
            if (!Files.exists(directoryPath)) {
                File directory = new File(String.valueOf(String.valueOf(directoryPath)));
                directory.mkdir();
            }
            return tasksList;
        }
    }

    public void save(TaskList taskList) {
        String textToAdd = "";
        ArrayList<Task> tasksList = taskList.getTaskList();

        for (Task task : tasksList) {
            String currentLine = "";

            if (task instanceof Todo) {
                currentLine += "T | ";
            } else if (task instanceof Deadline) {
                currentLine += "D | ";
            } else {
                currentLine += "E | ";
            }

            if (task.isDone) {
                currentLine += "1 | ";
            } else {
                currentLine += "0 | ";
            }

            currentLine += task.description + " | ";

            if (task instanceof Deadline) {
                currentLine += ((Deadline) task).by.toString();
            } else if (task instanceof Event) {
                currentLine += ((Event) task).at.toString();
            }

            textToAdd += currentLine + System.lineSeparator();
        }

        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to ./data/dukeFile.txt");
        }
    }
}
