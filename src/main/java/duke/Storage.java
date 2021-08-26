package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Acts as a storing and reading helper that helps Duke save the user's todo list and  reads the user's
 * todo list on his or her device.
 */
public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file at the location given by the Storage object's file path.
     *
     * @return True if a file is created successfully.
     * False if the file already exists or an error occurred.
     */
    public boolean createdFile() {
        try {
            File file = new File(this.filePath);
            if (file.createNewFile()) {
                System.out.println("File created.");
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Reads the file given by the Storage object's file path.
     *
     * @return An ArrayList of Task objects read from the file given by the Storage object's file path.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws DukeException         If Duke cannot understand the content in file.
     */
    public ArrayList<Task> readFile() throws FileNotFoundException, DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            parseLineInFile(line, tasks);
        }
        return tasks;
    }

    private void parseLineInFile(String string, ArrayList<Task> tasks) throws DukeException {
        //format of string should be typeOfTask||status||description||time
        if (string.length() < 7) {
            throw new DukeException("Cannot read file.");
        }
        char type = string.charAt(0);
        char status = string.charAt(3);
        boolean isDone;
        if (status == ' ') {
            isDone = false;
        } else if (status == 'X') {
            isDone = true;
        } else {
            throw new DukeException("Cannot read file.");
        }
        String description = string.substring(6);
        if (type == 'T') {
            tasks.add(new ToDo(description, isDone));
        } else if (type == 'E') {
            int index = description.indexOf("||");
            String time = description.substring(index + 2);
            tasks.add(new Event(description.substring(0, index), isDone, time));
        } else if (type == 'D') {
            int index = description.indexOf("||");
            String time = description.substring(index + 2);
            tasks.add(new Deadline(description.substring(0, index), isDone, time));
        } else {
            throw new DukeException("Cannot read file.");
        }
    }

    /**
     * Writes the given ArrayList of Task objects (the user's todo list) to the file
     * given by the Storage object's file path.
     *
     * @param tasks The user's todo list.
     */
    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String line = task.saveTaskToFile() + "\n";
                if (i == tasks.size() - 1) {
                    line = task.saveTaskToFile();
                }
                fileWriter.write(line);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot update todo list to the file.");
        }
    }
}
