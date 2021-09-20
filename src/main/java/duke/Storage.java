package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final File dataFile;
    private final TaskList taskList;

    /**
     * Constructor for storage, includes hardcoded file path for duke.txt.
     *
     * @param taskList Fills taskList at initialization and saves taskList data as .txt file.
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
        String DIR_NAME = "data";
        String FILE_NAME = "duke.txt";
        dataFile = retrieveFile(DIR_NAME, FILE_NAME);
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws IOException, DukeException {
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            boolean isDone = taskString.charAt(4) != ' ';
            char taskType = taskString.charAt(1);
            taskList.addTask(generateTask(taskString, isDone, taskType));

        }
    }

    private Task generateTask(String taskString, boolean isDone, char taskType) {
        switch (taskType) {
        case 'T':
            return ToDo.create(taskString, isDone);
        case 'E':
            return Event.create(taskString, isDone);
        case 'D':
            return Deadline.create(taskString, isDone);
        default:
            return null;
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
        return dataFile;
    }

    /**
     * Writes data down into duke.txt on 'bye' command.
     *
     * @throws IOException When data writing fails.
     */
    public void writeData() throws IOException {
        FileWriter writer = new FileWriter(dataFile.getPath());
        for (Task task: taskList.getTasks()) {
            writer.write(task.toString() + System.lineSeparator());
        }
        writer.close();
    }
}
