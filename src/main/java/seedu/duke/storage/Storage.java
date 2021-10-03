package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.commands.Ui;
import seedu.duke.exceptions.storage.DukeStorageDeleteException;
import seedu.duke.exceptions.storage.DukeStorageLoadException;
import seedu.duke.exceptions.storage.DukeStorageSaveException;
import seedu.duke.exceptions.storage.DukeStorageUpdateException;
import seedu.duke.tasks.Task;

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
     * @return an {@code ArrayList<Task>} that contains all the previous
     *         {@code Tasks} saved.
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
            this.data.createNewFile();
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                currLine = sc.nextLine();
                storageDataArray = currLine.split(" \\| ");
                eventType = currLine.split(" ")[0];
                storageIsDone = storageDataArray[1];
                switch (eventType) {

                    case "T":
                        StorageAddToDos getToDosCommand = new StorageAddToDos();
                        currList.add(getToDosCommand.execute(currLine, storageDataArray, storageIsDone));
                        break;

                    case "D":
                        StorageAddDeadline getDeadlineCommand = new StorageAddDeadline();
                        currList.add(getDeadlineCommand.execute(currLine, storageDataArray, storageIsDone));
                        break;

                    case "E":
                        StorageAddEvents getEventsCommand = new StorageAddEvents();
                        currList.add(getEventsCommand.execute(currLine, storageDataArray, storageIsDone));
                        break;

                    case "TT":
                        StorageAddTimedTask getTimedTaskCommand = new StorageAddTimedTask();
                        currList.add(getTimedTaskCommand.execute(currLine, storageDataArray, storageIsDone));
                        break;

                    case "PT":
                        StorageAddPeriodTask getPeriodTaskCommand = new StorageAddPeriodTask();
                        currList.add(getPeriodTaskCommand.execute(currLine, storageDataArray, storageIsDone));
                        break;

                    case "ST":
                        StorageAddScheduledTask getScheduledTaskCommand = new StorageAddScheduledTask();
                        currList.add(getScheduledTaskCommand.execute(currLine, storageDataArray, storageIsDone));
                        break;

                    default:
                        break;
                }
            }
            sc.close();
        } catch (IOException err) {
            System.out.println(err.toString());
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
     * @throws DukeStorageSaveException when {@code FileWriter} is not able to read
     *                                  or locate the file from the given file path.
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
     * @param index is the {@code Task} id.
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
                currLine = updateStringIfIsTheCurrentIndex(count, index, currLine);
                stringToAppend += currLine + "\n";
                count++;
            }
            this.clearsFileAndWrite(stringToAppend);
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
    }

    /**
     * Updates a {@code Task} into a {@code Task} with an {@code AfterTask} tied to
     * it.
     * 
     * @param index       is the index of the {@code Task}.
     * @param description is the description of the {@code AfterTask}.
     */
    public void updateAfterTask(int index, String description) {
        int count = 0;
        String currLine;
        String stringToAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                currLine = amendIfIsToUpdateAfterTask(count, index, currLine, description);
                stringToAppend += currLine + "\n";
                count++;
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
                stringToAppend = amendStringIfCountNotIndex(count, index, stringToAppend, currLine);
                count++;
            }
            clearsFileAndWrite(stringToAppend);
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageLoadException(Ui.ERROR_MESSAGE_FILE_NOT_FOUND);
        }
    }

    public void updateTags(int index, String tags) {
        int count = 0;
        String currLine;
        String stringToAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                currLine = updateTagsIfIsTheCurrentIndex(count, index, currLine, tags);
                stringToAppend += currLine + "\n";
                count++;
            }
            this.clearsFileAndWrite(stringToAppend);
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
    }

    public void deleteTags(int index) {
        int count = 0;
        String currLine;
        String stringToAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                currLine = deleteTagsIfIsTheCurrentIndex(count, index, currLine);
                stringToAppend += currLine + "\n";
                count++;
            }
            this.clearsFileAndWrite(stringToAppend);
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
    }

    private String deleteTagsIfIsTheCurrentIndex(int count, int index, String curr) {
        String currLine = curr;
        if (count == index && curr.contains(" | #tags ")) {
            currLine = currLine.split(" \\| #tags ")[0];
        }
        return currLine;
    }

    private String updateTagsIfIsTheCurrentIndex(int count, int index, String curr, String tags) {
        String currLine = curr;
        if (count == index) {
            if (currLine.contains(" | #tags ")) {
                currLine += " " + tags;
            } else {
                currLine += " | #tags " + tags;
            }
        }
        return currLine;
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

    private String updateStringIfIsTheCurrentIndex(int count, int index, String curr) {
        String currLine = curr;
        if (count == index) {
            currLine = currLine.replace(Storage.STORAGE_ISDONE_FALSE, Storage.STORAGE_ISDONE_TRUE);
            currLine = amendStringIfHasAfterTask(currLine);
        }
        return currLine;
    }

    private String amendStringIfHasAfterTask(String curr) {
        String currLine = curr;
        if (currLine.contains(" | after")) {
            if (currLine.split(" \\| after")[1].contains("#tags")) {
                String tags = currLine.split(" \\| #tags ")[1];
                currLine = currLine.split(" \\| after")[0];
                currLine += " | #tags " + tags;
            }
            currLine = currLine.split(" \\| after")[0];
        }
        return currLine;
    }

    private String amendIfIsToUpdateAfterTask(int count, int index, String curr, String description) {
        String currLine = curr;
        if (count == index) {
            if (currLine.contains("#tags")) {
                String tags = currLine.split(" \\| #tags ")[1];
                currLine = currLine.split(" \\| #tags ")[0] + " | after " + description;
                currLine += " | #tags " + tags;
            } else {
                currLine += " | after " + description;
            }
        }
        return currLine;
    }

    private String amendStringIfCountNotIndex(int count, int index, String stringToAppend, String curr) {
        String currLine = curr;
        String currStringToAppend = stringToAppend;

        if (count != index) {
            currStringToAppend += currLine + "\n";
        }
        return currStringToAppend;
    }
}
