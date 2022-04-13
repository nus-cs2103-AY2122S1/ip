package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage deals with the file that stores the items in the user's local memory so that
 * the user can retrieve his tasks when he loads up duke.Duke in the future after exiting.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public File load() {
        return file;
    }

    /**
     * Changes a single task in the tasklist to be in a 'done' status.
     *
     * @param description Description of the item to be found in the taskList.
     * @param cl Class of the item that will be found in the taskList.
     * @param dukeData The file that is to be deleted so this new file will be written.
     * @return The new file that has taken the place of the old file.
     * @throws IOException To handle the exception where file is not read.
     */
    public File rewriteDone(String description, String cl, File dukeData) throws IOException {
        Scanner sc = new Scanner(dukeData);
        int count = 0;
        boolean isChanged = false;
        File temp = new File("data/temp.txt");
        FileWriter fw = new FileWriter(temp, true);
        BufferedWriter bw = new BufferedWriter(fw);
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("")) {
                continue;
            }
            String[] parts = nextLine.split("\\|", 10);
            if (!(count == 0)) {
                bw.newLine();
            }
            if (parts[2].equals(description) && parts[0].equals(cl) && !isChanged) {
                bw.write(parts[0] + "|1|" + parts[2] + "|" + parts[3]);
                count++;
                isChanged = true;
                continue;
            }
            bw.write(nextLine);
            count++;
        }
        assert count != 0 : "Nothing has been changed";
        bw.flush();
        bw.close();
        dukeData.delete();
        File dukeRenewed = new File("data/duke.txt");
        temp.renameTo(dukeRenewed);
        return dukeRenewed;
    }

    /**
     * Changes the task that is specified to a status that shows that it has been done. It uses
     * doneChanger method to complete the process.
     *
     * @param taskToChange The task that has been completed and requires changing of completion status.
     * @throws DukeException An error that needs to be caught due to IOException possibly being thrown
     *                       by FileWriter
     */
    public void changeDone(Task taskToChange) throws DukeException {
        try {
            String description = taskToChange.getItemName();
            String cl = "X";
            if (taskToChange instanceof Todo) {
                cl = "T";
            }
            if (taskToChange instanceof Deadline) {
                cl = "D";
            }
            if (taskToChange instanceof Event) {
                cl = "E";
            }
            assert !cl.equals("X") : "Class not correct";
            file = rewriteDone(description, cl, file);
            assert file.getPath().equals("data/duke.txt") : "File is in data/duke.txt";
        } catch (IOException e) {
            throw new DukeException("Oh oh! Something went wrong!");
        }
    }

    /**
     * Deletes a line of the task that needs to be deleted from the taskList.
     *
     * @param description Description of the task to be found in the taskList.
     * @param cl Class of the task to be found (Deadline, Event or To do).
     * @param dukeData The original file that needs to be deleted so this new file will take it's place.
     * @return The new file in the same directory.
     * @throws IOException Exception thrown when the file is invalid.
     */
    public File deleteLine(String description, String cl, File dukeData) throws IOException {
        Scanner sc = new Scanner(dukeData);
        boolean isDeleted = false;
        int count = 0;
        File temp = new File("data/temp.txt");
        FileWriter fw = new FileWriter(temp, true);
        BufferedWriter bw = new BufferedWriter(fw);
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("")) {
                continue;
            }
            String[] parts = nextLine.split("\\|", 10);
            if (parts[2].equals(description) && parts[0].equals(cl) && !isDeleted) {
                isDeleted = true;
                continue;
            }
            if (!(count == 0)) {
                bw.newLine();
            }
            bw.write(nextLine);
            count++;
        }
        bw.flush();
        bw.close();
        dukeData.delete();
        File dukeRenewed = new File("data/duke.txt");
        temp.renameTo(dukeRenewed);
        return dukeRenewed;
    }

    /**
     * Deletes a specified task from the local memory using the method deleteLine
     * function.
     *
     * @param toDelete The task that is to be deleted.
     * @throws DukeException An error that needs to be caught due to IOException possibly being thrown
     *                       by FileWriter
     */
    public void deleteFromFile(Task toDelete) throws DukeException {
        try {
            String description = toDelete.getItemName();
            String cl = "X";
            if (toDelete instanceof Todo) {
                cl = "T";
            }
            if (toDelete instanceof Deadline) {
                cl = "D";
            }
            if (toDelete instanceof Event) {
                cl = "E";
            }
            assert !cl.equals("X") : "Class given is wrong";
            file = deleteLine(description, cl, file);
        } catch (IOException e) {
            throw new DukeException("Oh oh! Something went wrong!");
        }
    }

    /**
     * Writes new tasks to be added into the local memory due to any AddCommand being used.
     *
     * @param type the type of task to be added (Deadline, To do or Event).
     * @param description The user's description of the task to be added.
     * @param time The time for the task to be completed or for the task to begin(Only applicable for
     *             Event and Deadline).
     * @throws DukeException An error that needs to be caught due to IOException possibly being thrown
     *                       by FileWriter
     */
    public void addToText(String type, String description, String time) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(type + "|0|" + description + "|" + time);
            bw.flush();
        } catch (IOException e) {
            throw new DukeException("Oh oh! Something went wrong!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Storage)) {
            return false;
        }
        Storage other = (Storage) obj;
        return this.file.equals(other.file);
    }
}
