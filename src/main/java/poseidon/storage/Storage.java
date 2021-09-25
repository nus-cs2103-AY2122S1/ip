package poseidon.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import poseidon.exception.PoseidonStorageException;
import poseidon.exception.PoseidonStorageReadWriteException;
import poseidon.task.Deadline;
import poseidon.task.Event;
import poseidon.task.Task;
import poseidon.task.Todo;

/**
 * Represents a {@code Storage} object for all operations related to storage such as loading, reading and writing
 * to a local file on the hard disk.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Storage {

    /** Separator value to be used for storage to a local text file on the hard disk */
    public static final String STORAGE_SEPARATOR = "\t";

    private File taskDataFile;

    /**
     * Constructs a {@code Storage} object and initializes the taskDataFile {@code File} object.
     *
     * @throws PoseidonStorageException For exceptions that occur when accessing/creating a file for storage
     * on the local hard disk.
     */
    public Storage() throws PoseidonStorageException {
        try {
            File taskDataDir = new File(Paths.get("data").toString());
            if (!taskDataDir.exists()) {
                taskDataDir.mkdir();
            }
            File taskDataFile = new File(Paths.get("data", "taskData.txt").toString());
            if (!taskDataFile.exists()) {
                taskDataFile.createNewFile();
            }
            this.taskDataFile = taskDataFile;
        } catch (IOException ex) {
            throw new PoseidonStorageException("Couldn't access/create necessary file to store tasks."
                    + ex.getMessage());
        }
        assert taskDataFile.exists() : "File for storage is supposed to exist";
    }


    /**
     * Returns a {@code ArrayList} containing all the {@code Task}s after reading through a text {@code File}
     * saved on the hard disk.
     *
     * @return {@code ArrayList} containing all the saved {@code Task}s.
     * @throws PoseidonStorageException Exceptions that occur when accessing a file for storage on the local hard
     * disk.
     * @throws PoseidonStorageReadWriteException Exceptions that occur during reading/writing of the file for storage on
     * the local hard disk.
     */
    public ArrayList<Task> load() throws PoseidonStorageException, PoseidonStorageReadWriteException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner loadScan;
        StringBuilder parseErrorMsg = new StringBuilder();

        try {
            loadScan = new Scanner(taskDataFile);
        } catch (IOException ex) {
            throw new PoseidonStorageException("Couldn't access storage file.\n"
                    + ex.getMessage());
        }

        int lineNo = 1;
        while (loadScan.hasNextLine()) {
            try {
                String[] nextLineArr = loadScan.nextLine().split(STORAGE_SEPARATOR);
                switch (nextLineArr[0]) {
                case "T":
                    tasks.add(new Todo(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1])));
                    break;
                case "E":
                    tasks.add(new Event(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1]),
                            LocalDateTime.parse(nextLineArr[3]), LocalDateTime.parse(nextLineArr[4])));
                    break;
                case "D":
                    tasks.add(new Deadline(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1]),
                            LocalDateTime.parse(nextLineArr[3])));
                    break;
                default:
                    break;
                }
                lineNo++;
            } catch (DateTimeParseException ex) {
                parseErrorMsg.append(ex.getMessage() + " at line " + lineNo + ".\n");
            }
        }

        if (parseErrorMsg.length() > 0) {
            throw new PoseidonStorageReadWriteException("Following errors found during loading:\n\n"
                    + parseErrorMsg);
        }

        loadScan.close();
        return tasks;
    }

    /**
     * Writes a new {@code Task} to the storage document of the Bot.
     *
     * @param taskStorage Storage {@code String} version of the new {@code Task}.
     * @throws PoseidonStorageReadWriteException Exceptions that occur during reading/writing of the file for storage on
     * the local hard disk.
     */
    public void storeAdd(String taskStorage) throws PoseidonStorageReadWriteException {
        try {
            FileWriter taskDataWriter = new FileWriter(taskDataFile, true);
            taskDataWriter.write(taskStorage);
            taskDataWriter.close();
        } catch (IOException ex) {
            throw new PoseidonStorageReadWriteException(ex.getMessage());
        }

    }

    /**
     * Writes an existing {@code Task} as done in the storage document of the Bot.
     *
     * @param index Index of the {@code Task} to be modified.
     * @param taskStorage Storage {@code String} version of the modified {@code Task}.
     * @throws PoseidonStorageReadWriteException Exceptions that occur during reading/writing of the file for storage on
     * the local hard disk.
     */
    public void storeModify(int index, String taskStorage) throws PoseidonStorageReadWriteException {
        try {
            BufferedReader taskDataReader = new BufferedReader(new FileReader(taskDataFile));
            StringBuilder newTaskData = new StringBuilder();

            for (int i = 0; i < index - 1; i++) {
                newTaskData.append(taskDataReader.readLine() + "\n");
            }

            taskDataReader.readLine();
            newTaskData.append(taskStorage);

            while (true) {
                String nextLine = taskDataReader.readLine();
                if (nextLine == null) {
                    break;
                } else {
                    newTaskData.append(nextLine + "\n");
                }
            }

            FileWriter taskDataWriter = new FileWriter(taskDataFile);
            taskDataWriter.write(newTaskData.toString());
            taskDataWriter.close();
            taskDataReader.close();
        } catch (IOException ex) {
            throw new PoseidonStorageReadWriteException(ex.getMessage());
        }
    }

    /**
     * Deletes a {@code Task} from the storage document of Bot.
     *
     * @param index Index of the deleted {@code Task}.
     * @throws PoseidonStorageReadWriteException Exceptions that occur during reading/writing of the file for storage on
     * the local hard disk.
     */
    public void storeDelete(int index) throws PoseidonStorageReadWriteException {
        try {
            BufferedReader taskDataReader = new BufferedReader(new FileReader(taskDataFile));
            StringBuilder newTaskData = new StringBuilder();

            for (int i = 0; i < index - 1; i++) {
                newTaskData.append(taskDataReader.readLine() + "\n");
            }

            taskDataReader.readLine();

            while (true) {
                String nextLine = taskDataReader.readLine();
                if (nextLine == null) {
                    break;
                } else {
                    newTaskData.append(nextLine + "\n");
                }
            }

            FileWriter taskDataWriter = new FileWriter(taskDataFile);
            taskDataWriter.write(newTaskData.toString());
            taskDataWriter.close();
            taskDataReader.close();
        } catch (IOException ex) {
            throw new PoseidonStorageReadWriteException(ex.getMessage());
        }
    }
}
