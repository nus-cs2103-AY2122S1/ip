package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage deals with the file that stores the items in the user's local memory so that
 * the user can retrieve his tasks when he loads up Duke in the future after exiting.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public File load() {
        return file;
    }

    public File doneChanger(String description, String cl, File dukeData) throws IOException {
        Scanner sc = new Scanner(dukeData);
        int count = 0;
        boolean changed = false;
        File temp = new File("data/temp.txt");
        FileWriter fw = new FileWriter(temp, true);
        BufferedWriter bw = new BufferedWriter(fw);

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if (!nextLine.equals("")) {
                String[] parts = nextLine.split("\\|", 10);
                if (parts[2].equals(description) && parts[0].equals(cl) && !changed) {
                    if (!(count == 0)) {
                        bw.newLine();
                    }
                    bw.write(parts[0] + "|1|" + parts[2] + "|" + parts[3]);
                    count++;
                    changed = true;
                    continue;
                }
                if (!(count == 0)) {
                    bw.newLine();
                }
                bw.write(nextLine);
                count++;
            }
        }
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
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
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
            file = doneChanger(description, cl, file);
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            throw new DukeException("oh oh! Something went wrong!");
        }
    }

    public File deleteLine(String description, String cl, File dukeData) throws IOException {
        Scanner sc = new Scanner(dukeData);
        boolean deleted = false;
        int count = 0;
        File temp = new File("data/temp.txt");
        FileWriter fw = new FileWriter(temp, true);
        BufferedWriter bw = new BufferedWriter(fw);

        while (sc.hasNextLine()) {

            String nextLine = sc.nextLine();
            if (!nextLine.equals("")) {
                String parts[] = nextLine.split("\\|", 10);
                if (parts[2].equals(description) && parts[0].equals(cl) && !deleted) {
                    deleted = true;
                    continue;
                }
                if (!(count == 0)) {
                    bw.newLine();
                }
                bw.write(nextLine);
                count++;
            }
        }
        bw.flush();
        bw.close();
        dukeData.delete();
        File dukeRenewed = new File("data/duke.txt");
        temp.renameTo(dukeRenewed);
        return dukeRenewed;
    }

    /**
     * A method that deletes a specified task from the local memory using the method deleteLine
     * function.
     *
     * @param toDelete The task that is to be deleted.
     * @throws DukeException An error that needs to be caught due to IOException possibly being thrown
     *                       by FileWriter
     */
    public void deleteFromFile(Task toDelete) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
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
            file = deleteLine(description, cl, file);
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            throw new DukeException("    Oh oh! Something went wrong!");
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
            throw new DukeException("oh oh! Something went wrong!");
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
