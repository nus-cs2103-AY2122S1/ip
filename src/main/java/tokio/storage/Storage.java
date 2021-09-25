package tokio.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import tokio.exceptions.DukeException;
import tokio.tasks.Deadlines;
import tokio.tasks.Events;
import tokio.tasks.Task;
import tokio.tasks.Todos;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final String filePath;
    private File file;

    /**
     * Initialises storage with file path.
     *
     * @param filePath File path of file that contains all tasks.
     * @throws IOException If file path does not exist.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Reads lines from txt file, convert to respective tasks and add to task list.
     *
     * @return List of Tasks.
     * @throws FileNotFoundException If txt file does not exist.
     * @throws DukeException If line read is missing any information.
     * @throws ParseException If string cannot be parsed to date.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException, DukeException, ParseException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String readStr = sc.nextLine();
            assert readStr.length() != 0 : "Line to be read cannot be empty";
            String[] readLineArray = readStr.split("]", 3);
            String taskInstruction = readLineArray[0].substring(1);
            String taskIsDone = readLineArray[1].substring(1);
            String taskDesc = readLineArray[2].trim();
            if (taskDesc.isBlank()) {
                throw new DukeException("Task description cannot be blank!!");
            }
            switch (taskInstruction) {
            case "T":
                Todos addTodo = new Todos(taskDesc);
                if (!taskIsDone.isBlank()) {
                    addTodo.setDone();
                }
                taskList.add(addTodo);
                break;
            case "D":
                String[] descDateArray = taskDesc.split("\\(by: ");
                //convert date to the correct input format
                DateFormat inputDeadlineDateFormat = new SimpleDateFormat("MMM dd yyyy");
                Date inputDeadlineDate = inputDeadlineDateFormat.parse(descDateArray[1]);
                DateFormat outputDeadlineDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String outputDeadlineDate = outputDeadlineDateFormat.format(inputDeadlineDate);
                Deadlines addDeadline = new Deadlines(descDateArray[0].trim(), outputDeadlineDate);
                if (!taskIsDone.isBlank()) {
                    addDeadline.setDone();
                }
                taskList.add(addDeadline);
                break;
            case "E":
                String[] eventDescTimeArray = taskDesc.split(" \\(at: ");
                int colonIndex = eventDescTimeArray[1].indexOf(":");
                String dateStr = eventDescTimeArray[1].substring(0, colonIndex - 2);
                String timeStr = eventDescTimeArray[1].substring(colonIndex - 2, colonIndex + 3);
                //convert date to the correct input format
                DateFormat inputEventDateFormat = new SimpleDateFormat("MMM dd yyyy");
                Date inputEventDate = inputEventDateFormat.parse(dateStr.trim());
                DateFormat outputEventDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String outputEventDate = outputEventDateFormat.format(inputEventDate);
                Events addEvent = new Events(eventDescTimeArray[0].trim(), outputEventDate, timeStr.trim());
                if (!taskIsDone.isBlank()) {
                    addEvent.setDone();
                }
                taskList.add(addEvent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + taskInstruction);
            }
        }
        return taskList;
    }

    /**
     * Adds a line with the task description in the txt file.
     *
     * @param task Task to be added.
     * @throws IOException If file does not exist in filePath.
     */
    public void writeTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.formatToStorage() + System.lineSeparator());
        fw.close();
    }

    /**
     * Edits given task in storage file.
     *
     * @param task Task that requires changes.
     * @param replace String to be updated to in the storage file.
     * @throws IOException If temp file could not be created or temp file is a directory
     */
    public void editTask(Task task, String replace) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt", file.getParentFile());
        FileWriter fw = new FileWriter(tempFile, true);
        Scanner sc = new Scanner(file);
        String lineDone = task.formatToStorage();
        boolean isDelete = replace.isBlank();
        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            boolean isTask = currLine.equals(lineDone);
            if (!isTask) {
                fw.write(currLine + System.lineSeparator());
            } else if (isDelete) {
                fw.write(replace);
            } else {
                task.setDone();
                fw.write(task.formatToStorage() + System.lineSeparator());
            }
        }
        fw.close();
        tempFile.renameTo(file);
    }
}
