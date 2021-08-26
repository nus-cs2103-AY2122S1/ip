import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String directory;
    private final String file;
    private final List<String> fileContent;

    public Storage(String directory, String file) {
        this.directory = directory;
        this.file = file;
        this.fileContent = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of Task from the saved data
     * @return an ArrayList of Task from the saved data
     * @throws DukeException upon load error
     */
    public ArrayList<Task> load() throws DukeException{
        // Make directory and/or file if they don't exist
        File dataDir = new File(directory);
        dataDir.mkdirs();
        File dataFile = new File(directory + "/" + file);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
            return null;
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String rawData = fileReader.nextLine();
                fileContent.add(rawData);
                String[] data = rawData.split(" \\| ");
                String taskType = data[0];
                boolean isDone = data[1].equals("1");
                Task task = null;
                switch (taskType) {
                case "T":
                    // Add a todo task.
                    task = new Todo(data[2]);

                    break;
                case "D":
                    // Add a deadline task.
                    task = new Deadline(data[2], data[3]);

                    break;
                case "E":
                    // Add an event task.
                    task = new Event(data[2], data[3]);
                }
                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(DukeException.Errors.FILE_NOT_FOUND.toString());
        }
        return tasks;
    }


    public String getFileLine(int id) throws DukeException {
        try {
            return fileContent.get(id);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString());
        }
    }


    public void addToFile(String task) throws DukeException {
        fileContent.add(task);
        commitChanges();
    }


    public void removeFromFile(int id) throws DukeException {
        fileContent.remove(id);
        commitChanges();
    }


    public void updateLineFile(int id, String task) throws DukeException {
        fileContent.set(id, task);
        commitChanges();
    }


    private void commitChanges() throws DukeException {
        try {
            Files.write(Paths.get(directory + "/" + file), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(DukeException.Errors.SAVE_FAIL.toString());
        }
    }
}
