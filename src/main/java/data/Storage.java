package data;

import task.Deadline;
import task.Task;
import task.ToDo;
import task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** File containing the user's saved tasks */
    private File userData;
    private String pathname;

    public Storage(String pathname) {
        this.pathname = pathname;
        try {
            userData = new File(pathname);
            userData.createNewFile();
        } catch (IOException e) {
            System.out.println("File not created");
        }
    }

    /**
     * Returns an ArrayList containing the user's saved tasks.
     * If user has no saved tasks, returns empty ArrayList.
     *
     * @return an ArrayList of tasks
     * @throws DukeException If file is not found
     */
    public ArrayList load() throws DukeException {
        ArrayList<Task> userDataList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(userData);
            while (sc.hasNextLine()) {
                String[] newTaskArray = sc.nextLine().split("\\|");
                String taskType = newTaskArray[0];
                boolean isDone = newTaskArray[1].equals("1");
                Task newTask = taskType.equals("D") ? new Deadline(newTaskArray[2], LocalDate.parse(newTaskArray[3]))
                        : taskType.equals("E") ? new Event(newTaskArray[2], newTaskArray[3])
                        : new ToDo(newTaskArray[2]);
                if (isDone) {
                    newTask.markAsDone();
                }
                userDataList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file is not found");
        }
        return userDataList;
    }

    /**
     * Updates the USERDATA.TXT to reflect changes that the user made.
     *
     * @param listOfUserTasks Updated ArrayList
     */
    public void save(TaskList listOfUserTasks) {
        try {
            FileWriter newFile = new FileWriter(pathname);
            for (int i = 0; i < listOfUserTasks.getSize(); i++) {
                newFile.write(listOfUserTasks.getTask(i).toData());
            }
            newFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
