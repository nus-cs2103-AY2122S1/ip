package duke.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.InvalidParamException;
import duke.main.TaskList;

/**
 * Encapsulates a storage that has methods to save Tasks from a TaskList in the hard disk,
 * as well as loading that saved data back into the TaskList upon running Duke.
 */
public class Storage {

    /** The path of the text file used to store the Tasks from a TaskList */
    private final String FILEPATH = "tasklist.txt";
    /** The file used to store the Tasks from a TaskList */
    private final File SAVE_FILE = new File(FILEPATH);
    /** A distinct string separating pieces of information saved in the save text file */
    private final String SEPARATOR = "~SEPARATION_STRING~";
    /** Initial letter of the To-do Task */
    private final char TODO_INITIAL = 'T';
    /** Initial letter of the Deadline Task */
    private final char DEADLINE_INITIAL = 'D';
    /** Initial letter of the Event Task */
    private final char EVENT_INITIAL = 'E';

    /** TaskList that this Storage will copy to and from */
    private TaskList taskList;

    /**
     * Constructs a Storage instance that acts on the given TaskList.
     *
     * @param taskList TaskList that this Storage will copy to and from.
     */
    public Storage(TaskList taskList) {
        try {
            this.taskList = taskList;
            SAVE_FILE.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Copies Tasks from the TaskList in order and saves them in a save text file.
     * Each Task is saved on 1 line in the order and format:
     * Status(1 for done, 0 for not done), Initial of Task, Description, Extra information
     * The above pieces of information are separated by the SEPARATOR string.
     */
    public void writeToFile() {
        try {
            StringBuilder toBeWritten = new StringBuilder();
            FileWriter fileWriter = new FileWriter(FILEPATH);

            for (Task task : taskList) {
                char initialOfTask = task.toString().charAt(1);
                String description = task.getDescription();

                // Add a marker to tell if its done or not i.e. 1 is done, 0 is not
                toBeWritten.append(task.getIsDone() ? "1" : "0").append(SEPARATOR);

                // Add the task type and description
                toBeWritten.append(initialOfTask).append(SEPARATOR).append(description);

                // Add any extra information if needed
                switch (initialOfTask) {
                case TODO_INITIAL:
                    break;
                case DEADLINE_INITIAL:
                    Deadline deadline = (Deadline) task;
                    toBeWritten.append(SEPARATOR).append(deadline.getBy());
                    break;
                case EVENT_INITIAL:
                    Event event = (Event) task;
                    toBeWritten.append(SEPARATOR).append(event.getAt());
                    break;
                default:
                    assert false : initialOfTask;
                }

                // Add new line for next task
                toBeWritten.append("\n");
            }

            fileWriter.write(toBeWritten.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the save text file line by line to interpret and construct the appropriate Tasks to add into the TaskList.
     */
    public void copyFromFileToList() {
        try {
            Scanner sc = new Scanner(SAVE_FILE);

            while (sc.hasNext()) {
                Task newTask = null;
                String[] inputArray = sc.nextLine().split(SEPARATOR);
                boolean isDone = inputArray[0].equals("1");
                char initialOfTask = inputArray[1].charAt(0);

                switch (initialOfTask) {
                case TODO_INITIAL:
                    newTask = Todo.setTodo(inputArray[2]);
                    break;
                case DEADLINE_INITIAL:
                    newTask = Deadline.setDeadline(inputArray[2] + Deadline.getSeparator() + inputArray[3]);
                    break;
                case EVENT_INITIAL:
                    newTask = Event.setEvent(inputArray[2] + Event.getSeparator() + inputArray[3]);
                    break;
                default:
                    assert false : initialOfTask;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                taskList.addTask(newTask);
            }
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        } catch (InvalidParamException e2) {
            assert false : e2.getMessage();
        }
    }
}
