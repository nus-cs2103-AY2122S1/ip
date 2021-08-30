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
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Events;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.ToDos;

public class Storage {
    private final File data;

    /**
     * Primary Constructor.
     */
    public Storage() {
        this.data = new File("src/main/java/seedu/data/data.txt");
    }

    /**
     * Loads the saved Tasks from the txt file.
     * 
     * @return ArrayList<Task> of Tasks containing all the previous Task
     * @throws DukeStorageLoadException when file is not found in the given location.
     */
    public ArrayList<Task> loadData() throws DukeStorageLoadException {
        ArrayList<Task> currList = new ArrayList<>();

        String currLine;
        String type;
        String descriptions;
        String[] stringArr;
        String dateTimeLocation;
        boolean isDone;
        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNext()) {
                currLine = sc.nextLine();
                stringArr = currLine.replace("|", "/").split(" / ");
                type = currLine.split("")[0];
                isDone = false;
                switch (type) {
                    case "T":
                        descriptions = stringArr[2];
                        ToDos todos;
                        if (stringArr[1].contains("1")) {
                            isDone = true;
                        }
                        todos = new ToDos(descriptions, isDone);
                        currList.add(todos);
                        break;

                    case "D":
                        descriptions = stringArr[2];
                        dateTimeLocation = stringArr[3];
                        Deadline deadline;
                        if (stringArr[1].contains("1")) {
                            isDone = true;
                        }
                        deadline = new Deadline(descriptions, dateTimeLocation, isDone);
                        currList.add(deadline);
                        break;

                    case "E":
                        descriptions = stringArr[2];
                        dateTimeLocation = stringArr[3];
                        Events event;
                        if (stringArr[1].contains("1")) {
                            isDone = true;
                        }
                        event = new Events(descriptions, dateTimeLocation, isDone);
                        currList.add(event);
                        break;

                    default:
                        break;
                }
            }
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageLoadException(Ui.ERROR_MESSAGE_STORAGE_LOAD);
        } catch (IndexOutOfBoundsException err) {
            throw new DukeStorageLoadException(Ui.ERROR_MESSAGE_STORAGE_LOAD_OUT_OF_BOUND);
        }
        return currList;
    }

    /**
     * Appends a new line in the txt file which saves all the Task.
     * 
     * @param textToAppend String to append into the txt file.
     * @throws DukeStorageSaveException when FileWriter is not able to read or locate the file
     *                     from the given file path.
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
     * Helps to update the done status in the txt file.
     * 
     * @param number is the Task id.
     * @throws DukeStorageUpdateException when Scanner is not able to read or locate the file from
     *                     the given file path.
     */
    public void updateDone(String number) throws DukeStorageUpdateException {
        int index = Integer.parseInt(number);
        int count = 0;
        String currLine;
        String finalAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                count++;
                if (count == index) {
                    currLine = currLine.replace("| 0 |", "| 1 |");
                }
                finalAppend += currLine + "\n";
            }
            FileWriter fw = new FileWriter(this.data, false);
            fw.append(finalAppend);
            fw.close();
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageUpdateException(err.toString());
        }
    }

    /**
     * Helps to delete the Task from the saved txt file.
     * 
     * @param number is the Task id.
     * @throws DukeStorageDeleteException when Scanner is not able to read or locate the file from
     *                     the given file path.
     */
    public void deleteTaskFromData(String number) throws DukeStorageDeleteException {
        int index = Integer.parseInt(number);
        int count = 0;
        String currLine;
        String finalAppend = "";

        try {
            Scanner sc = new Scanner(this.data);
            while (sc.hasNextLine()) {
                currLine = sc.nextLine();
                count++;
                if (count != index) {
                    finalAppend += currLine + "\n";
                }
            }
            FileWriter fw = new FileWriter(this.data, false);
            fw.append(finalAppend);
            fw.close();
            sc.close();
        } catch (IOException err) {
            throw new DukeStorageDeleteException(err.toString());
        }
    }
}
