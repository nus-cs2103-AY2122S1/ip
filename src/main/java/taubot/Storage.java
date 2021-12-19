package taubot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents Taubot's storage to store the list.
 */
public class Storage {
    private final String filePath;

    /**
     * Returns a <code>Storage</code> object that can store tasks.
     * Taubot stores the tasks at the specified <code>filePath</code>.
     * @param filePath The file path to store the data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method loads the tasks from the hard disk.
     *
     * @param tasks The list to transfer the tasks to from storage.
     */
    public void loadTasksFromFile(TaskList tasks) {
        if (tasks.getTasksLength() == 0) {
            File f = new File(filePath);
            createFileIfNotFound();
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
                Task.TaskType type = Taubot.convertStringToTaskType(commands[0]);
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
    }

    /**
     * Creates the file if file does not exist at the <code>filePath</code>.
     *
     * @throws TaubotException Thrown if there is an <code>IOException</code>
     */
    public void createFileIfNotFound() throws TaubotException {
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
            throw new TaubotException(e.getMessage());
        }
    }

    /**
     * Writes the data to the hard disk.
     *
     * @param tasks The task list to write to the hard disk.
     * @throws TaubotException Thrown when <code>IOException</code> is encountered.
     */
    public void writeData(TaskList tasks) throws TaubotException {
        try {
            this.createFileIfNotFound();
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 1; i < tasks.getTasksLength() + 1; i++) {
                Task task = tasks.getTask(i);
                String taskStatusIconAndDescription = task.getStatusIcon() + "," + task.getTaskDescription();
                switch (task.getType()) {
                case DEADLINE:
                    Deadline dl = (Deadline) task;
                    fw.write("deadline," + taskStatusIconAndDescription + "," + dl.getBy());
                    break;
                case TODO:
                    fw.write("todo," + taskStatusIconAndDescription);
                    break;
                case EVENT:
                    Event e = (Event) task;
                    fw.write("event," + taskStatusIconAndDescription + "," + e.getAt());
                    break;
                default:
                    break;
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new TaubotException(e.getMessage());
        }

    }

}
