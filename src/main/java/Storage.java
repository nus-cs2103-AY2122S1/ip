import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    // The relative path to the directory
    private final String directory;

    // The file
    private final String file;

    // The contents of the file as a List of Strings
    private final List<String> fileContent;

    /**
     * Constructor
     * @param fileDir The path to the directory
     * @param file The file name
     */
    public Storage(String fileDir, String file) {
        this.directory = fileDir;
        this.file = file;
        this.fileContent = new ArrayList<>();
    }

    /**
     * reads file. If file does not exist, create file
     * @return List containing all tasks in file originally
     */
    public ArrayList<Task> loadData() throws DukeException {
        // Make directory and/or file if they don't exist
        File dataFolder = new File(directory);
        boolean folderCreated = dataFolder.mkdirs();
        File dataFile = new File(directory + "/" + file);
        try {
            boolean fileCreated = dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
            return null;
        }

        ArrayList<Task> fileList = new ArrayList<>();
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
                        task.doneTask();
                    }
                    fileList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("No saved data found");
        }
        return fileList;
    }

    /**
     * Adds a task to the saved file
     * @param task The task as a String
     * @throws DukeException When saving the file fails
     */
    public void addToFile(String task) throws DukeException {
        fileContent.add(task);
        saveToFile();
    }

    /**
     * Removes a task from the saved file
     * @param id The line to be removed
     * @throws DukeException When saving the file fails
     */
    public void deleteFromFile(int id) throws DukeException {
        fileContent.remove(id);
        saveToFile();
    }

    /**
     * updates a line in the file at the given index
     * @param id index at which task is to be updated
     * @param task new task to be updated
     * @throws DukeException when saving file fails
     */
    public void updateListTask(int id, String task) throws DukeException {
        fileContent.set(id, task);
        saveToFile();
    }

    /**
     * Returns the String of the queried line
     * @param index The line number
     * @return The String of the queried line
     * @throws DukeException When the line is not found
     */
    public String getFileLine(int index) throws DukeException{
        try {
            return fileContent.get(index - 1);
        } catch (Exception e) {
            throw new DukeException("Task doesn't exist");
        }
    }

    /**
     * Saving the file content to the hard drive
     * @throws DukeException when saving the file fails
     */
    private void saveToFile() throws DukeException {
        try {
            Files.write(Paths.get(directory + "/" + file), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error: could not save to file.");
        }
    }

}
