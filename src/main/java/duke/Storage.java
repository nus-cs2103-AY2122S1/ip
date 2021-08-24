package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                tasks.addTask(commands[2], Task.TaskType.TODO, "");
                break;
            case EVENT:
            case DEADLINE:
                String[] dateAndTime = commands[3].split(" ");
                tasks.addTask(commands[2], type, LocalDate.parse(dateAndTime[0]), dateAndTime[1]);
                break;
            }
            if (commands[1].equals("[X]")) {
                tasks.getTask(currentTask).markAsDone();
            }
            currentTask++;
        }
    }

    public static void createFileIfNotFound(String filePath) throws DukeException {
        String[] fileSplit = filePath.split("/");
        String directory = "";
        for (int i = 0; i < fileSplit.length - 1; i++) {
            directory += fileSplit[i];
        }
        File dir = new File(directory);
        dir.mkdir();
        File yourFile = new File(filePath);
        try {
            yourFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Writes data to data.txt in data directory.
     *
     * @param tasks Tasklist in duke.Duke.
     * @throws IOException
     */
    public void writeData(TaskList tasks) throws DukeException {
        try {
            createFileIfNotFound(filePath);
            FileWriter fw = new FileWriter(filePath);
            for (int i = 1; i < tasks.getTasksLength() + 1; i++) {
                Task task = tasks.getTask(i);
                switch (task.type) {
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
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

}
