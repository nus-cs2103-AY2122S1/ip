package meap.util;

import meap.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String FILE_PATH;
    private File dir;
    private File file;
    private FileWriter fileWriter;
    private boolean isExistingUser = true;

    /**
     * Constructor for Storage class.
     * Initializes storage file.
     *
     * @param filePathToStorageFile file path to the file used for storing details
     */
    public Storage(String filePathToStorageFile) {
        FILE_PATH = filePathToStorageFile;
        this.file = new File(FILE_PATH);
        this.dir = this.file.getParentFile(); //may be null
        createDirAndFileIfItDoesntExist();
    }

    private void createDirAndFileIfItDoesntExist() {
        if (!this.dir.exists()) {
            dir.mkdirs();
        }
        if (!this.file.exists()) {
            isExistingUser = false;
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
    private void write(String text)  {
        try {
            fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(text);
            fileWriter.close();
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
