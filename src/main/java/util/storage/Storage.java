package util.storage;

import util.tasks.Task;
import util.tasks.DateTaskTable;
import util.tasks.DukeException;
import util.tasks.TaskList;
import util.tasks.DatedTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The storage class is the class representing the stored
 * information.
 *
 */
public class Storage {

    private String tempFilePath;
    private String saveFilePath;
    private DateTaskTable dateTaskTable;
    //Use a custom ui for storage?


    /**
     * Constructor for the storage class.
     *
     * @param saveFilePath The path to write to.
     * @param tempFilePath The temp file to use.
     * @param dateTaskTable The dateTaskTable to store tasks with dates.
     */
    public Storage(String saveFilePath, String tempFilePath, DateTaskTable dateTaskTable) {
        this.tempFilePath = tempFilePath;
        this.saveFilePath = saveFilePath;
        this.dateTaskTable = dateTaskTable;
    }


    /**
     * Command to write the input list of tasks onto the
     * hard disk.
     *
     * @param inputs The list of input tasks to encode onto the hardisk.
     * @throws IOException When the writing has failed.
     */
    public void write(ArrayList<? extends Task> inputs) throws IOException {

        File dir = new File("./data");
        if (!dir.exists()) dir.mkdirs();
        //creating the files
        File saveFile = new File(saveFilePath);
        File tempfile = new File(tempFilePath);
        //if the file does not exist
        if (!saveFile.exists()) {
            //When the old save file does not exist
            //do not have to use the tempfile
            saveFile.createNewFile();
            FileWriter wr = new FileWriter(saveFilePath);
            writeTasks(wr, inputs);
            wr.close();

        } else {
            //could use a temp file and switch after writing?
            FileWriter wr = new FileWriter(tempFilePath);
            writeTasks(wr, inputs);
            wr.close();
            saveFile.delete();
            tempfile.renameTo(saveFile);

        }


    }



    private void writeTasks(FileWriter writer, ArrayList<? extends Task> inputs) throws IOException {
        for (int i = 0; i < inputs.size(); i++) {
            writer.write(inputs.get(i).encode());
            writer.write("\n");
        }


    }

    /**
     * The method used to read from the current save File. --Use a save object?
     * Can allow for multiple saves --> could use such an object to handle
     * the saves in an arraylist.
     *
     * @return The arraylist containing the read result.
     * @throws IOException When the read fails.
     * @throws  DukeException When the format of the text to be read is wrong.
     */
    public TaskList read() throws IOException, DukeException {
        TaskList inputs = new TaskList();
        File f = new File(saveFilePath);
        //when the file does not exist, returns an empty arraylist.
        if (!f.exists()) return inputs;
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            Task t = Task.decode(sc.nextLine());
            inputs.add(t);
            if (t instanceof DatedTask) {
                dateTaskTable.add((DatedTask) t);
            }
        }
        sc.close();
        return inputs;

    }
}
