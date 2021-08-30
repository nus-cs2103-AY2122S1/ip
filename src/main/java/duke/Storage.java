package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private File data;

    /**
     * Constructor for Storage.
     *
     * @param pathname of the save file.
     * @param dir      directory of the save file.
     * @throws IOException when an IO operations fails.
     */
    public Storage(String pathname, String dir) throws IOException {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(pathname);
        if (!data.exists()) {
            data.createNewFile();
        }
        this.data = data;
    }

    /**
     * Reads the file data into an ArrayList.
     *
     * @return the ArrayList containing the saved tasks.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner dataScanner = new Scanner(data);
        while (dataScanner.hasNext()) {
            String dataLine = dataScanner.nextLine();
            String[] split = dataLine.split(" ");
            String type = split[0];
            boolean taskDone = split[2].equals("1"); //if taskDone = 1, task was done
            String desc;

            switch (type) {
            case "T":
                StringBuilder todoBuilder = new StringBuilder();
                for (int i = 4; i < split.length; i++) {
                    if (i != 4) {
                        todoBuilder.append(" ");
                    }
                    todoBuilder.append(split[i]);
                }
                desc = todoBuilder.toString();

                tasks.add(new Todo(desc, taskDone));
                break;

            case "D":
                StringBuilder deadlineBuilder = new StringBuilder();
                StringBuilder byBuilder = new StringBuilder();
                String by;
                boolean byFound = false;

                for (int i = 4; i < split.length; i++) {
                    if (byFound) {
                        if (!byBuilder.toString().equals("")) {
                            byBuilder.append(" ");
                        }
                        byBuilder.append(split[i]);
                    } else {
                        if (i == 4) {
                            deadlineBuilder.append(split[i]);
                        } else if (split[i].equals("|")) {
                            byFound = true;
                        } else {
                            deadlineBuilder.append(" ");
                            deadlineBuilder.append(split[i]);
                        }
                    }
                }
                desc = deadlineBuilder.toString();
                by = byBuilder.toString();

                tasks.add(new Deadline(desc, by, taskDone));
                break;

            case "E":
                StringBuilder eventBuilder = new StringBuilder();
                StringBuilder atBuilder = new StringBuilder();
                String at;
                boolean atFound = false;

                for (int i = 4; i < split.length; i++) {
                    if (atFound) {
                        if (!atBuilder.toString().equals("")) {
                            atBuilder.append(" ");
                        }
                        atBuilder.append(split[i]);
                    } else {
                        if (i == 4) {
                            eventBuilder.append(split[i]);
                        } else if (split[i].equals("|")) {
                            atFound = true;
                        } else {
                            eventBuilder.append(" ");
                            eventBuilder.append(split[i]);
                        }
                    }
                }
                desc = eventBuilder.toString();
                at = atBuilder.toString();

                tasks.add(new Event(desc, at, taskDone));
                break;

            default:

            }
        }
        return tasks;
    }

    /**
     * Adds a task to the save file.
     *
     * @param task the task to be added to the save file.
     * @throws IOException when an IO operation fails.
     */
    public void save(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt", true);
        if (task instanceof Todo) {
            fw.write("T | 0 | " + task.getDescription() + "\n");
            fw.close();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            fw.write("D | 0 | " + task.getDescription() + " | " + deadline.getBy() + "\n");
            fw.close();
        } else {
            Event event = (Event) task;
            fw.write("E | 0 | " + task.getDescription() + " | " + event.getAt() + "\n");
            fw.close();
        }
    }

    /**
     * Deletes task from save file.
     *
     * @param deleteIndex the index of the task to be deleted.
     * @throws IOException when an IO operation fails.
     */
    public void delete(int deleteIndex) throws IOException {
        List<String> content = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"), StandardCharsets.UTF_8));
        content.remove(deleteIndex - 1);
        Files.write(Path.of("data/tasks.txt"), content, StandardCharsets.UTF_8);
    }

    /**
     * Marks a task in the save file as done.
     *
     * @param doneIndex the index of the task to be marked as done.
     * @throws IOException when an IO operation fails.
     */
    public void markAsDone(int doneIndex) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"),
                        StandardCharsets.UTF_8));

        String oldLine = fileContent.get(doneIndex - 1);
        StringBuilder newLine = new StringBuilder(oldLine);
        newLine.setCharAt(4, '1');

        fileContent.set(doneIndex - 1, newLine.toString());
        Files.write(Path.of("data/tasks.txt"), fileContent, StandardCharsets.UTF_8);
    }
}
