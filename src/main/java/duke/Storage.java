package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class Storage {
    private final File dataFile;
    private final TaskList taskList;

    /**
     * Constructor for storage, includes hardcoded file path for duke.txt.
     *
     * @param taskList
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
        String DIR_NAME = "data";
        String FILE_NAME = "duke.txt";
        dataFile = retrieveFile(DIR_NAME, FILE_NAME);
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("Error loading duke.txt");
        }
    }

    private void loadData() throws IOException, DukeException {
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            boolean isDone = taskString.charAt(4) != ' ';
            String description;
            String dateTime;
            int timeStart;
            LocalDate date;
            switch (taskString.charAt(1)) {
            case 'T':
                description = taskString.substring(7) + " ";
                taskList.addTask(new ToDo(description, isDone));
                break;
            case 'E':
                timeStart = taskString.indexOf("at: ");
                description = taskString.substring(7, timeStart - 2) + " ";
                dateTime = taskString.substring(timeStart + 4, taskString.length() - 1);
                date = LocalDate.parse(dateTime, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                taskList.addTask(new Event(description, date, isDone));
                break;
            case 'D':
                timeStart = taskString.indexOf("by: ");
                description = taskString.substring(7, timeStart - 2) + " ";
                dateTime = taskString.substring(timeStart + 4, taskString.length() - 1);
                date = LocalDate.parse(dateTime, DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                taskList.addTask(new Deadline(description, date, isDone));
            default:
                break;
            }
        }
    }

    private File retrieveFile(String directory, String filename) {
        File dir = new File(directory);
        if (!dir.exists()) dir.mkdirs();
        File dataFile = new File(directory + "/" + filename);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert dataFile != null : "File should be created automatically if it doesn't yet exist";
        return dataFile;
    }

    /**
     * Writes data down into duke.txt on 'bye' command.
     *
     * @throws IOException
     */
    public void writeData() throws IOException {
        FileWriter writer = new FileWriter(dataFile.getPath());
        for (Task task: taskList.getTasks()) {
            writer.write(task.toString() + System.lineSeparator());
        }
        writer.close();
    }
}
