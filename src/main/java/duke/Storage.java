package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
        createFile();
    }

    public boolean createFile() {
        try {
            File file = new File(this.filePath);
            if (file.createNewFile()) {
                System.out.println("File created.");
                return true;
            } else {
                System.out.println("File fetched.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    public ArrayList<Task> readFile() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                boolean hasNoError = parseLineInFile(line, tasks);
                if (!hasNoError) {
                    tasks = new ArrayList<Task>();
                    break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    //format of string should be typeOfTask||status||description||time
    private boolean parseLineInFile(String string, ArrayList<Task> tasks) {
        try {
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
            return true;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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
            System.out.println("Cannot update file.");
        }
    }
}
