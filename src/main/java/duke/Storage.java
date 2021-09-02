package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Tasklist;
import ui.LogMessage;
import ui.Parser;
import ui.Ui;

/**
 * Storage for todo list information
 *
 * @author: Wei Yangken
 */
public class Storage {

    private String filepath;
    private Tasklist tasklist;

    /**
     * Constructs a storage that stores information at filepath
     * @param filepath Location of stored information
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads data from filepath
     * @return a tasklist from specified filepath
     */
    public Tasklist load() {
        Ui.printBreakline();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] fields = data.split(",");
                String type = fields[1];
                String isDone = fields[2];
                String taskName = fields[3];
                String remarks;
                try {
                    remarks = fields[4];
                } catch (IndexOutOfBoundsException e) {
                    remarks = "";
                }

                String input = taskName + " " + remarks;
                Task task = Parser.parseStringIntoTask(input, type, Boolean.parseBoolean(isDone));
                tasks.add(task);

            }
            System.out.printf("Loaded old tasklist...\n");
            Ui.printBreakline();

        } catch (FileNotFoundException e) {
            System.out.printf("Initializing new tasklist...\n");
            Ui.printBreakline();
        }
        tasklist = new Tasklist(tasks);
        return tasklist;
    }

    /**
     * Saves the tasklist to the specified filepath
     */
    public LogMessage save() {
        File file = new File(filepath);

        LogMessage logMessages = new LogMessage();

        // Creates all the file directories
        String[] fileDirectories = filepath.split("/");
        int len = fileDirectories.length;
        String currDir = "";

        for (int i = 0; i < len - 1; i++) {
            currDir += fileDirectories[i];
            File currFile = new File(currDir);
            if (currFile.mkdir()) {
                String addDirectoryMsg = String.format("Added directory %s\n", currDir);
                logMessages.add(addDirectoryMsg);
            }
            currDir += "/";
        }

        //Creates file
        try {
            if (file.createNewFile()) {
                String saveFileMsg = String.format("Saved file at %s\n", filepath);
                logMessages.add(saveFileMsg);
            } else {
                String fileExistMsg = String.format("File already exists at %s\n", filepath);
                logMessages.add(fileExistMsg);
            }

        } catch (IOException e) {
            String fileCreationErrorMsg = String.format("An error occurred during creation of file.\n");
            logMessages.add(fileCreationErrorMsg);
        }

        try {
            FileWriter fileWriter = new FileWriter(filepath);
            String headers = "S/n,Type,Status,Name,Remarks\n";
            fileWriter.write(headers);
            for (int i = 0; i < tasklist.size(); i++) {
                Task task = tasklist.get(i);
                String listEntry = String.format("%d,%s,%b,%s,%s\n",
                        i + 1, task.getTaskCat(), task.isDone(), task.getName(), task.getDetail());
                fileWriter.append(listEntry);
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
        }
    }

}
