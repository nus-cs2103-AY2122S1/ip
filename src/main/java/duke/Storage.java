package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath File path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the file to obtain the tasks saved.
     *
     * @return A list of tasks stored in the file.
     * @throws DukeException  If error occurred while reading or creating file.
     */
    public ArrayList<Task> getFile() throws DukeException {
        try {
            File localFile = new File(filePath);
            localFile.getParentFile().mkdir();
            if (localFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                ArrayList<Task> taskList = new ArrayList<>();
                Scanner fileScanner = new Scanner(localFile);
                while (fileScanner.hasNextLine()) {
                    String data = fileScanner.nextLine();
                    String[] parameters = data.split(" / ");
                    switch (parameters[0]) {
                        case "T": {
                            ToDo task = new ToDo(parameters[2]);
                            task.setDone(Integer.parseInt(parameters[1]));
                            taskList.add(task);
                            break;
                        }
                        case "D": {
                            Deadline task = new Deadline(parameters[2], LocalDate.parse(parameters[3]));
                            task.setDone(Integer.parseInt(parameters[1]));
                            taskList.add(task);
                            break;
                        }
                        case "E": {
                            Event task = new Event(parameters[2], LocalDate.parse(parameters[3]));
                            task.setDone(Integer.parseInt(parameters[1]));
                            taskList.add(task);
                            break;
                        }
                    }
                }
                fileScanner.close();
                return taskList;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("An error occurred. :-(\n");
        }
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param taskList User's list of tasks.
     * @throws DukeException  If error occurred while saving.
     */
    public void saveList(ArrayList<Task> taskList) throws DukeException {
        StringBuilder content = new StringBuilder();
        for (Task task : taskList) {
            content.append(task.toFileFormat()).append("\n");
        }
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content.toString());
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred. :-(\n");
        }
    }
}
