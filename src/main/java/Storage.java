import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String FILEPATH = DIRECTORY + "data.txt";
    private File file;

    public Storage() {
        this.file = new File(FILEPATH);
        if (!file.exists()) {
            try {
                System.out.println("data.txt file does not exist. Creating new file...");
                File directory = new File(DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                file.createNewFile();
                System.out.println("data.txt created successfully! :>");
            } catch (IOException e) {

            }
        }
    }

    /**
     * Returns an ArrayList containing all the tasks in the data.txt file.
     * If data.txt is empty, an Empty ArrayList is returned.
     *
     * @param file File Object for data.txt.
     * @return ArrayList containing tasks.
     * @throws FileNotFoundException If file is missing.
     * @throws DukeException If file is corrupted.
     */
    public ArrayList<Task> retrieveData() throws FileNotFoundException, DukeException {
        Scanner fileReader = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        if (file.length() == 0) {
            System.out.println("No tasks to load!");
            return taskList;
        }
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            String[] dataBreakdown = data.split(" \\| ");
            Task task;
            switch (dataBreakdown[0]) {
            case "T":
                task = new ToDo(dataBreakdown[2]);
                taskList.add(task);
                break;
            case "D":
                task = new Deadline(dataBreakdown[2], dataBreakdown[3]);
                taskList.add(task);
                break;
            case "E":
                task = new Event(dataBreakdown[2], dataBreakdown[3]);
                taskList.add(task);
                break;
            default:
                throw new DukeException("File has been corrupted @_@");
            }
            if (dataBreakdown[1].equals("1")) {
                task.markDone();
            }
        }
        fileReader.close();
        System.out.println("YAY! File has been loaded Successfully! :>");
        return taskList;
    }

    /**
     * Saves data into data.txt files after changes have been made by user.
     *
     * @param data Formatted TaskList to be stored in data.txt.
     */
    public void save(String data) throws DukeException{
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            fileWriter.write(data);
            fileWriter.close();
            System.out.println("File Saved successfully!");
        } catch (IOException e) {
            throw new DukeException("I/O");
        }
    }
}
