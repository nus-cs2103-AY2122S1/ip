import java.io.*;

public class Storage {
    private final String pathName;
    private final String fileName;

    public Storage(String pathName, String fileName) {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    public File initialiseDirectory() throws DukeException {
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

    public File initialiseFile(File directory) throws IOException {
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

    public void loadTasksFromFile(File dataFile, TaskList tasks) throws IOException {
        FileReader fileReader = new FileReader(dataFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] task = line.trim().split("\\|");
            String type = task[0].trim();
            boolean isDone = Boolean.parseBoolean(task[1].trim());
            String description = task[2].trim();
            String dateTime;

            switch (type) {
            case "T":
                Task todoTask = new ToDo(TaskType.TODO, description, isDone);
                tasks.add(todoTask);
                break;
            case "D":
                dateTime = task[3].trim();
                Task deadlineTask = new Deadline(TaskType.DEADLINE, description, dateTime, isDone);
                tasks.add(deadlineTask);
                break;
            case "E":
                dateTime = task[3].trim();
                Task eventTask = new Event(TaskType.EVENT, description, dateTime, isDone);
                tasks.add(eventTask);
                break;
            }
        }

        bufferedReader.close();
    }

    public void saveTasksToFile(File dataFile, TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(dataFile,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            TaskType type = task.getType();
            boolean isDone = task.isDone();
            String description = task.getDescription();
            String dateTime;

            if (type == TaskType.TODO) {
                dateTime = "";
            } else {
                dateTime = ((TaskWithDateTime) task).getDateTimeInput();
            }

            String taskDetails = taskDetailsSaveFormat(type, isDone, description, dateTime);
            bufferedWriter.write(taskDetails + System.lineSeparator());
        }

        bufferedWriter.close();
    }

    private String taskDetailsSaveFormat(TaskType type, boolean isDone, String description, String dateTime) {
        if (dateTime.equals("")) {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description;
        } else {
            return type.getAbbr() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime;
        }
    }
}
