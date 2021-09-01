package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements a Storage object that deals with loading and saving tasks from a file.
 */
public class Storage {
    private File myTask;

    /**
     * Constructs a Storage object.
     * @param fileName Name of the file to be created.
     */
    public Storage(String fileName) {
        try {
            myTask = new File(fileName);
            myTask.getParentFile().mkdir();
            myTask.createNewFile();
        } catch (IOException e) {
            System.out.println("cannot create file leh");
            e.printStackTrace();
        }

    }

    /**
     * Writes the task list from Duke to a file.
     * @param list The list of task.
     */
    public void saveTask(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(this.myTask, false);
            for (Task task : list) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("cannot save the file leh");
        }
    }

    /**
     * Loads the task list from a file to Duke.
     * @return The list of tasks.
     * @throws DukeException Thrown in the event the file cannot be found.
     */
    public ArrayList<Task> loadTask() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(myTask);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                char taskType = data.charAt(1);
                switch (taskType) {
                case 'T':
                    loadToDo(data, list);
                    break;
                case 'D':
                    loadDeadline(data, list);
                    break;
                case 'E':
                    loadEvent(data, list);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + taskType);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("cannot find file");
        }
        return list;
    }

    private void loadToDo(String data, ArrayList<Task> list) {
        list.add(new Todo(data.substring(7)));
    }

    private void loadDeadline(String data, ArrayList<Task> list) {
        String name = data.substring(7, data.indexOf("(by:") - 1);
        String time = data.substring(data.indexOf("(by:") + 5, data.length() - 1);
        list.add(new Deadline(name, time));
    }

    private void loadEvent(String data, ArrayList<Task> list) {
        String name = data.substring(7, data.indexOf("(at:") - 1);
        String time = data.substring(data.indexOf("(at:") + 5, data.length() - 1);
        list.add(new Deadline(name, time));
    }
}
