package duke.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.task.TASKTYPE;
import duke.task.Task;

/**
 * @author Dr-Octavius
 *
 * Class that represents Storage for all Tasks
 *
 * @version 1.0
 */
public class Storage {

    private static final String tempFileName = "temp.txt";
    private final File storageFile;
    private final String fileName;
    private final String folderPath;
    private final String storagePath;

    /**
     * Class Constructor that takes 2 parameters
     *
     * @param folderPath Path to the parent folder of the File
     * @param fileName Name of the File
     */
    public Storage(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
        storagePath = folderPath
                .concat("\\")
                .concat(fileName);
        storageFile = getFile();
    }

    /**
     * Returns storage file as a Java File Object
     *
     * @return File Object of the storage file
     */
    public File getFile() {
        try {
            createFile(folderPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(storagePath);
    }

    private void createFile(String folderPath, String fileName) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("\t" + "Internal error occurred while creating storage path");
            }
        }
        File fileObj = new File(folderPath
                .concat("\\")
                .concat(fileName)
        );
        if (!fileObj.exists()) {
            if (!fileObj.createNewFile()) {
                throw new IOException("\t" + "Internal error occurred while creating storage path");
            }
        }
    }

    /**
     * Adds a new task into the storage file
     *
     * @param t Task to be added into the storage
     */
    public void saveEntry(Task t) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile, true));
            writer.write(t.getType().name()
                    .concat(",")
                    .concat(Boolean.toString(t.getState()))
                    .concat(",")
                    .concat(t.getDescription())
            );
            if (t.getType().equals(TASKTYPE.D)) {
                writer.write(",".concat(t.getBy()));
            }
            if (t.getType().equals(TASKTYPE.E)) {
                writer.write(",".concat(t.getDate()));
                writer.write(",".concat(t.getTime()));
            }
            writer.write(",");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes task from storage file
     *
     * @param t Task to be deleted from storage
     */
    public void deleteEntry(Task t) {
        try {
            File tempFile = new File(folderPath
                    .concat("\\")
                    .concat(tempFileName)
            );
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, true));
            BufferedReader reader = new BufferedReader(new FileReader(storageFile));
            String toDelete = t.getType().name()
                    .concat(",")
                    .concat(Boolean.toString(t.getState()))
                    .concat(",")
                    .concat(t.getDescription())
                    .concat(",");
            String curr;
            while (true) {
                curr = reader.readLine();
                if (curr == null) {
                    break;
                }
                if (curr.trim().equals(toDelete)) {
                    continue;
                }
                writer.write(curr);
                writer.newLine();
            }
            writer.close();
            reader.close();
            if (!storageFile.delete()) {
                System.out.println("unsuccessful delete");
            }
            if (tempFile.renameTo(storageFile)) {
                System.out.println("unsuccessful rename");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
