package duke.command;

import duke.exception.DuplicateException;
import duke.exception.InvalidTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Dealing with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private File src;
    private TaskList taskList;

    /**
     * Constructor of Storage class asking for a filePath and a taskList
     *
     * @param filePath The path of the input file.
     * @param taskList The TaskList stored tasks' information.
     * @throws IOException Throws IOException when the program cannot create a new File with given src.
     */
    public Storage(String filePath, TaskList taskList) throws IOException {
        this.src = new File(filePath);
        this.taskList = taskList;
        this.src.createNewFile();
    }

    /**
     * Deals with updating the tasks file when taskList is modified.
     *
     * @throws IOException Throws IOException when the fileWriter cannot be created with the given src.
     */
    public void modifyTasks() throws IOException {
        FileWriter fileWriter = new FileWriter(this.src);
        fileWriter.close();
        fileWriter = new FileWriter(this.src, true);
        for (int i = 0; i < taskList.size(); i++) {
            fileWriter.write(taskList.getTask(i).toStoredString());
            if (i != taskList.size() - 1) {
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
    }

    /**
     * Reads through the src file and update the taskList.
     *
     * @param sc The scanner for the src file.
     * @throws DuplicateException Throws when there are duplicate tasks in the source file.
     * @throws InvalidTaskException Throws when the format of input task in the source file
     * is not valid.
     */
    public void readEvents(Scanner sc) throws DuplicateException, InvalidTaskException {
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            if (task.equals("\n")) {
                continue;
            }
            char taskType = task.charAt(0);
            char taskDone = task.charAt(4);
            String taskValue = task.substring(8);
            boolean isFinished = (taskDone == '1');
            Task curTask = new Task("");
            int separatorPosition;

            switch (taskType) {
            case 'T':
                curTask = new Todo(taskValue, isFinished);
                break;
            case 'E':
                separatorPosition = taskValue.indexOf('|');
                String eventName = taskValue.substring(0, separatorPosition - 1);
                String eventTime = taskValue.substring(separatorPosition + 2);
                curTask = new Event(eventName, isFinished, eventTime);
                break;
            case 'D':
                separatorPosition = taskValue.indexOf('|');
                String deadlineName = taskValue.substring(0, separatorPosition - 1);
                String deadlineTime = taskValue.substring(separatorPosition + 2);
                curTask = new Deadline(deadlineName, isFinished, deadlineTime);
                break;
            }
            taskList.addTask(curTask);
        }
    }

    /**
     * Adds new task to the file when it is added to the taskList.
     *
     * @param task The task want to store in the src file.
     * @throws IOException Throws IOException when FileWriter cannot be created with the given src.
     */
    public void saveNewTask(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(this.src, true);
        fileWriter.write(task.toStoredString() + "\n");
        fileWriter.close();
    }

    /**
     * Loads tasks saved on the src file.
     *
     * @throws DuplicateException Throws when readEvents throws this exception.
     * @throws IOException Throws IOException when Scanner cannot be created with the given src.
     * @throws InvalidTaskException Throws when readEvents throws this exception.
     */
    public void loadSavedTasks() throws DuplicateException, IOException, InvalidTaskException {
        Scanner sc = new Scanner(src);
        readEvents(sc);
        sc.close();
    }
}
