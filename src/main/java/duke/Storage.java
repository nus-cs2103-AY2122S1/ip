package duke;

import duke.Tasks.*;
import duke.exceptions.FileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final File f;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }


    public void loadData(TaskManager taskList)
            throws FileNotFoundException, FileFormatException {

        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String entry = s.nextLine();

            // Check if entry is in the correct format
            if(!entry.matches(RegexType.TODO_REGEX.getRegexType())
                    && !entry.matches(RegexType.EVENT_DEADLINE_REGEX.getRegexType())) {
                throw new FileFormatException();
            }

            String[] entrySplit= entry.split(RegexType.SPLIT_REGEX.getRegexType());
            String taskType = entrySplit[0];
            boolean isDone = entrySplit[1].equals("1");
            String description = entrySplit[2];
            String atBy = entrySplit.length >= 4 ? entrySplit[3] : "";


            switch (taskType) {
                case "T":
                    taskList.tasks.add(new Todo(description, isDone));

                    break;
                case "D":
                    taskList.tasks.add(new Deadline(description, atBy, isDone));

                    break;
                case "E":
                    taskList.tasks.add(new Event(description, atBy, isDone));

                    break;
            }
        }
    }

    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        f.createNewFile();
    }

    public TaskManagerUi initialiseTaskList(){
        TaskManager taskList = new TaskManager();
        String message = "";

        try {
            loadData(taskList);
        } catch (FileNotFoundException e){
            message = message + "File not found";
            try {
                createFile();
                message = message + "\n" + "New file data/duke.txt created";

            } catch (IOException e1){
                message = message + "\n" + "Error creating new file";
            }

        } catch (FileFormatException e){
            message = message + "\n" + "File formatting error";
            taskList.clearList();
        }

        return new TaskManagerUi(taskList, message);
    }

    private void _writeToFile(TaskManager taskList) throws IOException{
        FileWriter fw = new FileWriter(f);
        for (Task task : taskList.tasks){
            fw.write(task.saveTask() + "\n");
        }
        fw.close();
    }

    public String writeToFile(TaskManager taskList){
        try {
            _writeToFile(taskList);
        } catch (IOException e) {
            return "Error writing to file";
        }

        return "";
    }
}