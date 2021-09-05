package botto.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import botto.BottoException;
import botto.task.Deadline;
import botto.task.Event;
import botto.task.Task;
import botto.task.Todo;

/**
 * This class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private File file;

    /**
     * Constructor for the Botto's storage
     *
     * @param filePath Filepath of the file that keeps track of the tasks
     */
    public Storage(String filePath) {
        assert filePath != null : "The file path cannot be null";
        this.file = new File(filePath);
    }

    /**
     * read the file and load the tasks stored inside it
     *
     * @return a list of tasks recorded in the file
     * @throws BottoException whenever an read operation is failed
     */
    public List<Task> load() throws BottoException {
        List<Task> tasks = new LinkedList<>();

        try {
            this.file.getParentFile().mkdirs();

            // file does not exist
            if (file.createNewFile()) {
                return tasks;
            }

            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                Task task;
                String next = scanner.nextLine();
                boolean isDone = next.charAt(4) == 'X';
                String[] info = next.substring(7).split(" [(]..: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");

                switch (next.charAt(1)) {
                case 'T' :
                    task = new Todo(info[0]);
                    break;
                case 'D' :
                    String deadline = info[1].substring(0, info[1].length() - 1);
                    task = new Deadline(info[0], LocalDateTime.parse(deadline, formatter));
                    break;
                case 'E' :
                    String eventTime = info[1].substring(0, info[1].length() - 1);
                    task = new Event(info[0], LocalDateTime.parse(eventTime, formatter));
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.markAsDone();
                }

                tasks.add(task);
            }

        } catch (IOException e) {
            throw new BottoException("Something went wrong when loading data: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * save the tasks in the assigned file
     *
     * @param tasks list of tasks to be stored
     * @throws BottoException  whenever an write operation is failed
     */
    public void save(List<Task> tasks) throws BottoException {
        assert tasks != null : "tasks cannot be null";

        try {
            FileWriter fw = new FileWriter("data/botto.txt");
            StringBuilder data = new StringBuilder();

            for (Task task : tasks) {
                data.append(task).append(System.lineSeparator());
            }

            fw.write(data.toString());
            fw.close();
        } catch (IOException e) {
            throw new BottoException("Something went wrong when saving data: " + e.getMessage());
        }
    }
}
