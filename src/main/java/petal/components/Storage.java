package petal.components;

import petal.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    //Boolean representing if saving should be performed
    public static boolean savedProperly;
    //Relative path of the folder containing Tasks.txt
    private final String folderPath;
    //Relative path of the txt file with the tasks
    private final String filePath;
    private final TaskList taskList;
    private final Ui ui;

    public Storage(TaskList taskList, Ui ui) {
        this.ui = ui;
        this.taskList = taskList;
        this.folderPath = System.getProperty("user.dir") + "/PetalData";
        this.filePath = folderPath + "/Tasks.txt";
        savedProperly = true;
    }

    /**
     * Method to create the main PetalData folder, containing Tasks.txt
     */
    public void createDirectory() {
        try {
            if (retrieveTasks())
                return;
            Path path = Paths.get(folderPath);
            Files.createDirectories(path);
            File petalData = new File(filePath);
            boolean done = petalData.createNewFile();
        } catch (IOException e) {
            savedProperly = false;
            ui.output(Responses.FILE_ERROR);
        }
        ui.output(Responses.START_MESSAGE);
    }

    /**
     * Method to parse the text from Tasks.txt in tasks
     * @return True if tasks were retrieved, false if no tasks (new user) or exception occurred
     */
    public boolean retrieveTasks() {
        try {
            File tasks = new File(filePath);
            Scanner scanner = new Scanner(tasks);
            ArrayList<Task> toBeAdded = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] components = taskLine.split("\\|");
                boolean isDone = Objects.equals(components[1], "X");
                switch (components[0]) {
                    case "T":
                        toBeAdded.add(new ToDo(components[2], isDone));
                        break;
                    case "D":
                        toBeAdded.add(new Deadline(components[2], components[3], isDone));
                        break;
                    case "E":
                        toBeAdded.add(new Event(components[2], components[3], isDone));
                        break;
                }
            }
            taskList.addTask(toBeAdded);
            scanner.close();
            return true;
        } catch (FileNotFoundException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Method to save the tasks. If the folder was not able to be created, Petal does not
     * save any of the tasks.
     * @throws IOException Thrown if tasks are not saved properly
     */
    public void saveTasks() throws IOException {
        if (!savedProperly) {
            return;
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskList.formatForSaving());
        fileWriter.close();
    }

}
