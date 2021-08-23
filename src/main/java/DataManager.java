import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;

public class DataManager {
    private final File data;

    public DataManager(String folderName, String fileName) {
        this.data = new File(Paths.get(folderName, fileName).toString());
    }

    private Task lineToTask(String line) throws NyxException {
        String[] splitLine = line.split(", ");

        boolean isDone = splitLine[1].equals("1");

        Task task;

        switch(splitLine[0]) {
        case "T":
            task = new ToDo(splitLine[2], isDone);
            break;
        case "D":
            task = new Deadline(splitLine[2], splitLine[3], isDone);
            break;
        case "E":
            task = new Event(splitLine[2], splitLine[3], isDone);
            break;
        default:
            throw new NyxException("This seems to be an invalid task symbol!");
        }

        return task;
    }

    ArrayList<Task> loadData() throws IOException, NyxException {
        ArrayList<Task> taskList= new ArrayList<Task>();

        if (data.exists()) {
            ArrayList<String> lines = new ArrayList<String>();
            Files.lines(data.toPath()).forEach(lines::add);
            for (String line: lines) {
                taskList.add(lineToTask((line)));
            }
        } else {
            data.getParentFile().mkdirs();
            data.createNewFile();
        }

        return taskList;
    }

    void overwriteData(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(data.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        taskList.stream().map(Task::dataFormat).forEach(sb::append);
        fw.write(sb.toString());
        fw.close();
    }

    void addData(Task task) throws IOException {
        FileWriter fw = new FileWriter(data.getAbsolutePath(), true);
        fw.write(task.dataFormat());
        fw.close();
    }
}
