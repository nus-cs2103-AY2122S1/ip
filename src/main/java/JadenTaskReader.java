import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class JadenTaskReader {

    File inFile;

    public JadenTaskReader(String fileName) {
        this.inFile = new File(fileName);
    }

    public TaskList readTasks() {
        TaskList tasks = new TaskList();
        try {
            Scanner fileReader = new Scanner(inFile);
            while (fileReader.hasNextLine()) {
                String taskLine = fileReader.nextLine();
                if(taskLine.length() > 1) {
                    Task newTask = decodeTask(taskLine);
                    if(newTask != null) tasks.addTask(newTask, false);
                } else {
                    break;
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            Session.output("Your Input Document Is Deeply Flawed, Like Many Of Us.");
        } finally {
            return tasks;
        }
    }

    public Task decodeTask(String taskString) {
        String[] taskParts = taskString.split(" TTT ");
        boolean taskDone = taskParts[1].contains("X");
        Task decodedTask = null;
        System.out.println(taskString + "\n" + Arrays.toString(taskParts));
        if(taskParts[1].contains("T")) {
            decodedTask = new ToDoTask(taskParts[2]);
        } else if(taskParts[1].contains("D")) {
            String[] splitInput = taskParts[2].split(" ");
            int byIndex = ParsedInput.findFirstIndexOf("(by:", splitInput);
            String taskDescription = ParsedInput.joinStrings(splitInput, 0, byIndex - 1);
            String deadline = ParsedInput.joinStrings(splitInput, byIndex + 1, splitInput.length - 1);
            deadline = deadline.substring(0, deadline.length() - 1); // shave off trailing )
            decodedTask = new DeadlineTask(taskDescription, deadline);
        } else if(taskParts[1].contains("E")) {
            String[] splitInput = taskParts[2].split(" ");
            int atIndex = ParsedInput.findFirstIndexOf("(at:", splitInput);
            String taskDescription = ParsedInput.joinStrings(splitInput, 1, atIndex - 1);
            String eventDuration = ParsedInput.joinStrings(splitInput, atIndex + 1, splitInput.length - 1);
            eventDuration = eventDuration.substring(0, eventDuration.length() - 1); // shave off trailing )
            decodedTask = new EventTask(taskDescription, eventDuration);
        }
        if(taskDone && decodedTask != null) decodedTask.markAsDone();
        return decodedTask;
    }
}
