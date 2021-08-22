package util.storage;

import util.tasks.DukeException;
import util.tasks.Task;
import util.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import util.tasks.*;


public class Storage {

    private String tempFilePath;
    private String saveFilePath;
    private DateTaskTable dateTaskTable;
    //Use a custom ui for storage?


    public Storage(String saveFilePath, String tempFilePath, DateTaskTable dateTaskTable) {
        this.tempFilePath = tempFilePath;
        this.saveFilePath = saveFilePath;
        this.dateTaskTable = dateTaskTable;
    }


    /**
     * The method that runs when the Duke needs to log the
     * list of Tasks. Occurs every time there is a modification to the
     * List of tasks.
     *
     *
     * maybe its not better though?
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
