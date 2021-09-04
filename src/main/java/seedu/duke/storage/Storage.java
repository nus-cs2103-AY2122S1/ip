package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.action.DukeActionOutOfBoundException;
import seedu.duke.exceptions.storage.DukeStorageDeleteException;
import seedu.duke.exceptions.storage.DukeStorageLoadException;
import seedu.duke.exceptions.storage.DukeStorageSaveException;
import seedu.duke.exceptions.storage.DukeStorageUpdateException;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Events;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.ToDos;

public class Storage {
    private static final String STORAGE_ISDONE_FALSE = "| 0 |";
    private static final String STORAGE_ISDONE_TRUE = "| 1 |";

    private final File data;

    /**
     * Primary Constructor.
     */
    public Storage() {
        this.data = new File(Ui.DATA_LOCATION);
    }

    /**
     * Loads the saved Tasks from the txt file.
     * 
     * @return an {@code ArrayList<Task>} that contains all the previous Tasks.
     * @throws DukeStorageLoadException when {@code File} is not found in the given
     *                                  location.
     * @see java.util.ArrayList
     */
    public ArrayList<Task> loadData() throws DukeStorageLoadException {
        ArrayList<Task> currList = new ArrayList<>();
        String currLine;
        String eventType;
        String[] storageDataArray;
        String storageIsDone;

        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                currLine = sc.nextLine();
                storageDataArray = currLine.replace("|", "/").split(" / ");
                eventType = currLine.split("")[0];
                storageIsDone = storageDataArray[1];
                switch (eventType) {

                case "T":
                    ToDos todos = new ToDos(getDescriptions(storageDataArray), getIsDoneFromStorage(storageIsDone));
                    currList.add(todos);
                    break;

                case "D":
                    Deadline deadline = new Deadline(getDescriptions(storageDataArray),
                            getDateTimeLocation(storageDataArray), getIsDoneFromStorage(storageIsDone));
                    currList.add(deadline);
                    break;

                case "E":

                    Events event = new Events(getDescriptions(storageDataArray), getDateTimeLocation(storageDataArray),
                            getIsDoneFromStorage(storageIsDone));
                    currList.add(event);
                    break;

                default:
                    break;
                }
            }
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageLoadException(Ui.ERROR_MESSAGE_STORAGE_LOAD);
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeStorageLoadException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
        return currList;
    }

    /**
     * Appends a new line in the txt file which saves all the Task.
     * 
     * @param textToAppend {@code String} to append into the txt file.
     * @throws DukeStorageSaveException when FileWriter is not able to read or
     *                                  locate the file from the given file path.
     */
    public void appendToData(String textToAppend) throws DukeStorageSaveException {
        try {
            FileWriter fileWriter = new FileWriter(this.data, true);
            fileWriter.append(textToAppend + "\n");
            fileWriter.close();
        } catch (IOException err) {
            throw new DukeStorageSaveException(err.toString());
        }
    }

    /**
     * Updates the done status in the txt file.
     * 
     * @param number is the {@code Task} id.
     * @throws DukeStorageUpdateException when {@code Scanner} is not able to read
     *                                    or locate the {@code File} from the given
     *                                    file path.
     */
    public void updateDone(int index) throws DukeStorageUpdateException {
        int count = 0;
        String currLine;
        String stringToAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                count++;
                if (count == index) {
                    currLine = currLine.replace(Storage.STORAGE_ISDONE_FALSE, Storage.STORAGE_ISDONE_TRUE);
                }
                stringToAppend += currLine + "\n";
            }
            clearsFileAndWrite(stringToAppend);
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
    }

    /**
     * Deletes the Task from the saved txt file.
     * 
     * @param number is the {@code Task} id.
     * @throws DukeStorageDeleteException when {@code Scanner} is not able to read
     *                                    or locate the {@code File} from the given
     *                                    file path.
     */
    public void deleteTaskFromData(int index) throws DukeStorageDeleteException {
        int count = 0;
        String currLine;
        String stringToAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                count++;
                if (count != index) {
                    stringToAppend += currLine + "\n";
                }
            }
            clearsFileAndWrite(stringToAppend);
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageLoadException(Ui.ERROR_MESSAGE_FILE_NOT_FOUND);
        }
    }

    private boolean getIsDoneFromStorage(String storageIsDone) {
        assert !storageIsDone.equals(null) : "Storage isDone value should not be null";
        if (storageIsDone.contains("1")) {
            return true;
        }
        return false;
    }

    private String getDescriptions(String[] storageDataArray) {
        assert storageDataArray.length > 1 : "storageDataArray length should be greater than 1";
        try {
            return storageDataArray[2];
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
    }

    private String getDateTimeLocation(String[] storageDataArray) {
        assert storageDataArray.length > 2 : "storageDataArray length should be greater than 2";
        try {
            return storageDataArray[3];
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeActionOutOfBoundException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
    }

    private void clearsFileAndWrite(String stringToAppend) {
        try {
            FileWriter fileWriter = new FileWriter(this.data, false);
            fileWriter.append(stringToAppend);
            fileWriter.close();
        } catch (IOException err) {
            throw new DukeStorageDeleteException(err.toString());
        }
    }
}
