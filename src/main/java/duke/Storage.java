package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.TaskWithDateTime;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String pathName;
    private String fileName;
    private File dataDirectory;
    private File dataFile;

    public Storage(String pathName, String fileName) throws DukeException, IOException {
        this.pathName = pathName;
        this.fileName = fileName;
        dataDirectory = initialiseDirectory();
        dataFile = initialiseFile(dataDirectory);
    }

    private File initialiseDirectory() throws DukeException {
        File directory = new File(pathName);
        boolean hasDirectory = directory.exists();

        if (!hasDirectory) {
            hasDirectory = directory.mkdir();
        }

        if (hasDirectory) {
            return directory;
        } else {
            throw new DukeException("\t" + "Unable to initialise directory");
        }
    }

    private File initialiseFile(File directory) throws IOException {
        File file = new File(directory + "/" + fileName);
        boolean hasFile = file.exists();

        if (!hasFile) {
            hasFile = file.createNewFile();
        }

        if (hasFile) {
            return file;
        } else {
            throw new IOException("\t" + "Unable to initialise file");
        }
    }

    public TaskList loadTasksFromFile() throws IOException {
        TaskList taskList = new TaskList();

        FileReader fileReader = new FileReader(dataFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] taskDetails = line.trim().split("\\|");
            String type = taskDetails[0].trim();
            boolean isDone = (Integer.parseInt(taskDetails[1].trim()) == 1);
            String description = taskDetails[2].trim();
            String dateTime;

            switch (type) {
            case "T":
                Task todoTask = new ToDo(TaskType.TODO, description, isDone);
                taskList.add(todoTask);
                break;
            case "D":
                dateTime = taskDetails[3].trim();
                Task deadlineTask = new Deadline(TaskType.DEADLINE, description, dateTime, isDone);
                taskList.add(deadlineTask);
                break;
            case "E":
                dateTime = taskDetails[3].trim();
                Task eventTask = new Event(TaskType.EVENT, description, dateTime, isDone);
                taskList.add(eventTask);
                break;
            }
        }

        bufferedReader.close();

        return taskList;
    }

    public void saveTasksToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            TaskType type = task.getType();
            boolean isDone = task.isDone();
            String description = task.getDescription();
            String dateTime;

            if (type == TaskType.TODO) {
                dateTime = "";
            } else {
                dateTime = ((TaskWithDateTime) task).getDateTimeInput();
            }

            String taskToSave = taskSaveFormat(type, isDone, description, dateTime);
            bufferedWriter.write(taskToSave + System.lineSeparator());
        }

        bufferedWriter.close();
    }

    private String taskSaveFormat(TaskType type, boolean isDone, String description, String dateTime) {
        if (dateTime.equals("")) {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description;
        } else {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime;
        }
    }
}
