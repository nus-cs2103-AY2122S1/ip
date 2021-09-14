package ailurus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ailurus.task.Task;
import ailurus.task.TaskList;


public class Storage {
    private String directoryName;
    private String filename;
    private File directory = null;
    private File ailurusObj = null;
    private FileWriter writer = null;

    /**
     * Constructor for storage
     *
     * @param directoryName name of directory to be created and for storage of data
     * @param filename name of file to be created and for storage of data
     */
    public Storage(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        this.directory = new File(directoryName);
        this.ailurusObj = new File(directoryName.concat(filename));

        if (!directory.exists()) {
            // create new directory
            directory.mkdir();
        }

        if (!ailurusObj.exists()) {
            // create new file
            try {
                ailurusObj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Reads and loads the tasks in the data file, and creates a FileWriter
     * that must be followed with unload() to close writer
     *
     * @return list of tasks that is loaded from data file
     */
    public ArrayList<Task> load() throws AilurusException {
        ArrayList<Task> list = new ArrayList<>();
        // load tasks from file
        try {
            Scanner reader = new Scanner(ailurusObj);
            while (reader.hasNextLine()) {
                Task task = Parser.parseData(reader.nextLine());
                if (task != null) {
                    list.add(task);
                }
            }
            this.writer = new FileWriter(this.directoryName.concat(this.filename));
        } catch (IOException e) {
            throw new AilurusException(AilurusException.Error.LOAD);
        }
        return list;
    }

    /**
     * Unloads and writes all the tasks to file, must be preceded by load()
     *
     * @param list list of tasks to be written to the data file
     */
    public void unload(TaskList list) throws AilurusException {
        // write all tasks to file
        if (this.writer != null) {
            try {
                for (int i = 0; i < list.length(); i++) {
                    Task task = list.getTask(i);
                    task.log(writer);
                }
                writer.close();
            } catch (IOException e) {
                throw new AilurusException(AilurusException.Error.LOAD);
            }
        }
    }
}
