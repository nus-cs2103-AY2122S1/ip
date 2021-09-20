package duke;

import duke.tasks.*;
import duke.exceptions.FileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Class to facilitate the storing of tasks
 */
public class Storage {

    private final String filePath;
    private final File f;

    /**
     * Constructor for the storage class
     * @param filePath - refers to the path where the data has been stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }


    /**
     * To load the existing data of tasks from the hard disk
     * @param taskList - the list of tasks
     * @throws FileNotFoundException - the file does not exist
     * @throws FileFormatException - incorrect format
     */
    public void loadData(TaskManager taskList)
            throws FileNotFoundException, FileFormatException {

        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String entry = s.nextLine();

            // Check if entry is in the correct format
            if(!entry.matches(RegexType.TODO_REGEX.getRegexType())
                    && !entry.matches(RegexType.EVENT_DEADLINE_REGEX.getRegexType())) {
                throw new FileFormatException("File formatting error");
            }

            String[] entrySplit= entry.split(RegexType.SPLIT_REGEX.getRegexType());
            String taskType = entrySplit[0];
            boolean isDone = entrySplit[1].equals("1");
            String description = entrySplit[2];
            String atBy = entrySplit.length >= 4 ? entrySplit[3] : "";


            switch (taskType) {
                case "T":
                    taskList.tasks.add(new Todo(description, isDone));

                    break;
                case "D":
                    taskList.tasks.add(new Deadline(description, atBy, isDone));

                    break;
                case "E":
                    taskList.tasks.add(new Event(description, atBy, isDone));

                    break;
            }
        }
    }

    /**
     * Creates a new File
     * @throws IOException - Standard Input Output Exception
     */
    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        f.createNewFile();
    }

    /**
     * Initialises the task list
     * @return - Returns the taskList with a message
     */
    public TaskManagerUi initialiseTaskList(){
        TaskManager taskList = new TaskManager();
        String message = "";

        try {
            loadData(taskList);
        } catch (FileNotFoundException e){
            message = message + "File not found";
            try {
                createFile();
                message = message + "\n" + "New file data/duke.txt created";

            } catch (IOException e1){
                message = message + "\n" + "Error creating new file";
            }

        } catch (FileFormatException e){
            message = message + "\n" + "File formatting error";
            taskList.clearList();
        }

        return new TaskManagerUi(taskList, message);
    }

    /**
     * Writes data into the file that stores the list of tasks.
     * the taskList argument must specify the list of tasks.
     * @param taskList - the list of tasks
     * @throws IOException - Standard Input Output Exception
     */
    private void _writeToFile(TaskManager taskList) throws IOException{
        FileWriter fw = new FileWriter(f);
        for (Task task : taskList.tasks){
            fw.write(task.saveTask() + "\n");
        }
        fw.close();
    }

    /**
     * Writes data into the file that stores the list of tasks.
     * the taskList argument must specify the list of tasks.
     * @param taskList - the list of tasks
     * @return - empty String
     */
    public String writeToFile(TaskManager taskList){
        try {
            _writeToFile(taskList);
        } catch (IOException e) {
            return "Error writing to file";
        }

        return "";
    }
}