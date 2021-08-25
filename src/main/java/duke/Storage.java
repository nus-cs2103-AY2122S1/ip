package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {

    /**
     * Creates directory/file if it does not exist yet.
     *
     * @throws IOException
     */
    public void createDirectoryFile() throws IOException {
        if (Files.notExists(Path.of("src/data"))) {
            Files.createDirectory(Path.of("src/ata"));
        }
        if (Files.notExists(Path.of("src/data/duke.txt"))) {
            Files.createFile(Path.of("src/data/duke.txt"));
        }
    }
    /**
     * Loads the TaskList stored in predetermined file.
     *
     * @return duke.TaskList
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        Parser p = new Parser();
        try {
            createDirectoryFile();
            File tasksFile = new File("src/data/duke.txt");
            Scanner s = new Scanner(tasksFile);
            while (s.hasNext()) {
                String[] parsedFromFile = p.parseFromFile(s.nextLine());
                switch (parsedFromFile[0]) {
                case "todo":
                    tasks.addTask(new Todo(parsedFromFile));
                    break;
                case "deadline":
                    tasks.addTask(new Deadline(parsedFromFile));
                    break;
                case "event":
                    tasks.addTask(new Event(parsedFromFile));
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Writes TaskList into file for storage.
     *
     * @param taskList TaskList to be stored.
     */
    public void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter("src/data/duke.txt");
            for (int i = 0; i < taskList.getLength(); i++) {
                String taskToWrite = taskList.getTaskByIndex(i).toWrite();
                fw.write(taskToWrite);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
