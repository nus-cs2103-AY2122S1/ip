package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
                    String[] parameters = data.split(" // ");
                    switch (parameters[0]) {
                    case "T":
                        ToDo toDo = new ToDo(parameters[2]);
                        setParameters(toDo, parameters, 3, taskList);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parameters[2], LocalDate.parse(parameters[3]));
                        setParameters(deadline, parameters, 4, taskList);
                        break;
                    case "E":
                        Event event = new Event(parameters[2], LocalDate.parse(parameters[3]));
                        setParameters(event, parameters, 4, taskList);
                        break;
                    default:
                        break;
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
     * @param content User's list of tasks in file format.
     * @throws DukeException  If error occurred while saving.
     */
    public void saveList(String content) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred. :-(\n");
        }
    }

    private String[] getTagsFromFile(String[] parameters, int parameterNumber) {
        if (parameters.length > parameterNumber) {
            return parameters[parameterNumber].split("/");
        } else {
            return null;
        }
    }
    private void setParameters(Task task, String[] parameters, int tagsIndex, ArrayList<Task> taskList) {
        task.setDoneStatus(Integer.parseInt(parameters[1]));
        task.addTags(getTagsFromFile(parameters, tagsIndex));
        taskList.add(task);
    }
}
