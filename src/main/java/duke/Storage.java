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
}
