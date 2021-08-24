package jarvis.storage;

import jarvis.exception.InvalidStorageTaskException;
import jarvis.exception.StorageFileException;
import jarvis.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) throws StorageFileException {
        this.file = new File(filePath);
        if (!file.isFile()) {
           try {
               this.file.getParentFile().mkdirs();
               this.file.createNewFile();
           } catch (IOException e) {
               throw new StorageFileException("Error while loading storage file!");
           }
        }
    }

    public ArrayList<Task> loadTasksFromFile() throws InvalidStorageTaskException, StorageFileException {
        Scanner s;

        try {
            s  = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new StorageFileException("Storage file not found!");
        }

        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] readLine = s.nextLine().split(";;;", 4);
            if (readLine.length < 3) {
                throw new StorageFileException("Storage file has improper format!");
            }
            String taskType = readLine[0];
            boolean isDone = readLine[1].equals("1");
            Task task;
            switch (taskType) {
                case "T":
                    task = new Todo(readLine[2]);
                    break;
                case "D":
                    task = new Deadline(readLine[2], readLine[3]);
                    break;
                case "E":
                    task = new Event(readLine[2], readLine[3]);
                    break;
                default:
                    throw new InvalidStorageTaskException();
            }
            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }

        return taskList;
    }

    public void addToStorageFile(String entry) throws StorageFileException {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write(entry + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new StorageFileException("Error when writing task to storage file!");
        }
    }

    public void rewriteStorageFile(TaskList taskList) throws StorageFileException {
        try {
            FileWriter writer = new FileWriter(this.file);
            writer.write(taskList.toStorageFormatString());
            writer.close();
        } catch (IOException e) {
            throw new StorageFileException("Error when rewriting task(s) to storage file!");
        }
    }
}
