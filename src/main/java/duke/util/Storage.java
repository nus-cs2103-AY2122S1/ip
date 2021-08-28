package duke.util;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private File dir;
    private File file;
    private FileWriter fw;

    /**
     * Constructor for Storage class.
     * Initializes storage file.
     *
     * @param filePathToStorageFile file path to the file used for storing details
     */
    public Storage(String filePathToStorageFile) {
        this.filePath = filePathToStorageFile;
        this.file = new File(this.filePath);
        this.dir = this.file.getParentFile(); //may be null
        //create directory and file if doesnt exist
        if (!this.dir.exists()) {
            dir.mkdirs();
        }
        if (!this.file.exists()) {
            createFile(this.file);
        }
    }

    /**
     * Creates file. Handles IO exception and prints stack trace.
     *
     * @param file storage file of type File
     */
    public void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //getter
    public File getFile() {
        return this.file;
    }

    /**
     * Writes text to the file initialized.
     *
     * @param text text to be written to file
     */
    public void write(String text)  {
        try {
            this.fw = new FileWriter(this.filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts tasks into text and writes it to the file initialized.
     *
     * @param tasks Tasks to be written to file
     */
    public void write(ArrayList<Task> tasks) {
        String text = tasks
                .stream()
                .map(Task::toStorageFormat)
                .reduce("",
                    (stringRes, stringTask) ->
                        stringRes + stringTask + System.getProperty("line.separator"));
        this.write(text);
    }
}
