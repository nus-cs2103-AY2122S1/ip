package poseidon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import poseidon.task.Deadline;
import poseidon.task.Event;
import poseidon.task.Task;
import poseidon.task.Todo;

public class Storage {

    private File taskDataFile;

    /**
     * Constructs a Storage object and initializes the taskDataFile File object.
     */
    public Storage() {
        try {
            File taskDataDir = new File(Paths.get("data").toString());
            if (!taskDataDir.exists()) {
                taskDataDir.mkdir();
            }
            File taskDataFile = new File(Paths.get("data", "taskData.txt").toString());
            if (!taskDataFile.exists()) {
                taskDataFile.createNewFile();
            }
            this.taskDataFile = taskDataFile;
        } catch (IOException ex) {
            throw new PoseidonException(ex.getMessage() + "\n"
                    + "Couldn't access/create necessary file to store tasks."
                    + "Please exit the bot if you don't want to lose new tasks.");
        }
        assert taskDataFile.exists() : "File for storage is supposed to exist";
    }

    /**
     * Returns a list containing all the tasks after reading through a text file saved on the hard disk.
     *
     * @return List containing all the saved tasks.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner loadScan = new Scanner(taskDataFile);
            while (loadScan.hasNextLine()) {
                String[] nextLineArr = loadScan.nextLine().split("%");
                switch (nextLineArr[0]) {
                case "T":
                    tasks.add(new Todo(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1])));
                    break;
                case "E":
                    tasks.add(new Event(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1]),
                            LocalDateTime.parse(nextLineArr[3]), LocalDateTime.parse(nextLineArr[4])));
                    break;
                case "D":
                    tasks.add(new Deadline(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1]),
                            LocalDateTime.parse(nextLineArr[3])));
                    break;
                default:
                    break;
                }
            }
            loadScan.close();
            return tasks;
        } catch (IOException ex) {
            throw new PoseidonException(ex.getMessage() + "\n"
                    + "Couldn't load data from stored file."
                    + "Please exit the bot if you don't want to lose new tasks.");
        }
    }

    /**
     * Writes a new Task to the storage document of the Bot.
     *
     * @param taskStorage Storage string version of the new Task.
     * @throws IOException If file access or modification is obstructed.
     */
    public void storeAdd(String taskStorage) throws IOException {
        FileWriter taskDataWriter = new FileWriter(taskDataFile, true);
        taskDataWriter.write(taskStorage);
        taskDataWriter.close();
    }

    /**
     * Writes an existing Task as done in the storage document of the Bot.
     *
     * @param index Index of the Task to be modified.
     * @param taskStorage Storage string version of the modified Task.
     * @throws IOException If file access or modification is obstructed.
     */
    public void storeDone(int index, String taskStorage) throws IOException {
        BufferedReader taskDataReader = new BufferedReader(new FileReader(taskDataFile));
        StringBuilder newTaskData = new StringBuilder();

        for (int i = 0; i < index - 1; i++) {
            newTaskData.append(taskDataReader.readLine() + "\n");
        }

        taskDataReader.readLine();
        newTaskData.append(taskStorage);

        while (true) {
            String nextLine = taskDataReader.readLine();
            if (nextLine == null) {
                break;
            } else {
                newTaskData.append(nextLine + "\n");
            }
        }

        FileWriter taskDataWriter = new FileWriter(taskDataFile);
        taskDataWriter.write(newTaskData.toString());
        taskDataWriter.close();
        taskDataReader.close();
    }

    /**
     * Deletes a Task from the storage document of Bot.
     *
     * @param index Index of the deleted Task.
     * @throws IOException If file access or modification is obstructed.
     */
    public void storeDelete(int index) throws IOException {
        BufferedReader taskDataReader = new BufferedReader(new FileReader(taskDataFile));
        StringBuilder newTaskData = new StringBuilder();

        for (int i = 0; i < index - 1; i++) {
            newTaskData.append(taskDataReader.readLine() + "\n");
        }

        taskDataReader.readLine();

        while (true) {
            String nextLine = taskDataReader.readLine();
            if (nextLine == null) {
                break;
            } else {
                newTaskData.append(nextLine + "\n");
            }
        }

        FileWriter taskDataWriter = new FileWriter(taskDataFile);
        taskDataWriter.write(newTaskData.toString());
        taskDataWriter.close();
        taskDataReader.close();
    }
}
