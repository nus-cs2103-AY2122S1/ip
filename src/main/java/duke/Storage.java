package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents Duke's storage to store the list.
 */
public class Storage {
    private final String filePath;

    /**
     * Returns a <code>Storage</code> object that can store tasks.
     * Duke stores the tasks at the specified <code>filePath</code>.
     * @param filePath The file path to store the data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method loads the tasks from the hard disk.
     * @param tasks The list to transfer the tasks to from storage.
     */
    public void loadTasksFromFile(TaskList tasks) {
        File f = new File(this.filePath);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        int currentTask = 1;
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] commands = currentLine.split(",");
            Task.TaskType type = Duke.convertToTaskType(commands[0]);
            switch (type) {
            case TODO:
                tasks.addTask(commands[2]);
                break;
            case EVENT:
            case DEADLINE:
                String[] dateAndTime = commands[3].split(" ");
                tasks.addTask(commands[2], type, LocalDate.parse(dateAndTime[0]), dateAndTime[1]);
                break;
            default:
                break;
            }
            if (commands[1].equals("[X]")) {
                tasks.getTask(currentTask).markAsDone();
            }
            currentTask++;
        }
    }

    /**
     * Creates the file if file does not exist at the <code>filePath</code>.
     * @throws DukeException Thrown if there is an <code>IOException</code>
     */
    public void createFileIfNotFound() throws DukeException {
        String[] fileSplit = this.filePath.split("/");
        StringBuilder directory = new StringBuilder();
        for (int i = 0; i < fileSplit.length - 1; i++) {
            directory.append(fileSplit[i]);
        }
        File dir = new File(directory.toString());
        dir.mkdir();
        File yourFile = new File(this.filePath);
        try {
            yourFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Writes the data to the hard disk.
     *
     * @param tasks The task list to write to the hard disk.
     * @throws DukeException Thrown when <code>IOException</code> is encountered.
     */
    public void writeData(TaskList tasks) throws DukeException {
        try {
            this.createFileIfNotFound();
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 1; i < tasks.getTasksLength() + 1; i++) {
                Task task = tasks.getTask(i);
                switch (task.getType()) {
                case DEADLINE:
                    Deadline dl = (Deadline) task;
                    fw.write("deadline," + task.getStatusIcon() + "," + task.getTaskDescription() + "," + dl.getBy());
                    break;
                case TODO:
                    fw.write("todo," + task.getStatusIcon() + "," + task.getTaskDescription());
                    break;
                case EVENT:
                    Event e = (Event) task;
                    fw.write("event," + task.getStatusIcon() + "," + task.getTaskDescription() + "," + e.getAt());
                    break;
                default:
                    break;
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

}
