import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class FileManager {

    private final String FILEPATH = "tasklist.txt";
    private final File SAVEFILE = new File(FILEPATH);
    private final String SEPARATOR = "~SEPARATION_STRING~";

    public FileManager() {
        try {
            SAVEFILE.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(ArrayList<Task> taskList) {
        try {
            StringBuilder toBeWritten = new StringBuilder();
            FileWriter fileWriter = new FileWriter(FILEPATH);

            for (Task task : taskList) {
                char initialOfTask = task.toString().charAt(1);
                String description = task.getDescription();

                // Add a marker to tell if its done or not
                toBeWritten.append(task.getIsDone() ? "1" : "0").append(SEPARATOR);

                //Add the task type and description
                toBeWritten.append(initialOfTask).append(SEPARATOR).append(description);

                switch (initialOfTask) {
                case 'T':
                    break;
                case 'D':
                    Deadline deadline = (Deadline) task;
                    toBeWritten.append(SEPARATOR).append(deadline.getBy());
                    break;
                case 'E':
                    Event event = (Event) task;
                    toBeWritten.append(SEPARATOR).append(event.getAt());
                    break;
                }

                toBeWritten.append("\n");
            }

            fileWriter.write(toBeWritten.toString());
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void copyFromFileToList(ArrayList<Task> taskList) { //STOPPED HERE
        try {
            Scanner sc = new Scanner(SAVEFILE);

            while (sc.hasNext()) {
                Task newTask = null;
                String[] inputArray = sc.nextLine().split(SEPARATOR);
                boolean isDone = inputArray[0].equals("1");

                switch (inputArray[1].charAt(0)) {
                case 'T':
                    newTask = new Todo(inputArray[2]);
                    break;
                case 'D':
                    newTask = new Deadline(inputArray[2], inputArray[3]);
                    break;
                case 'E':
                    newTask = new Event(inputArray[2], inputArray[3]);
                    break;
                }

                if (isDone) {
                    newTask.markAsDone();
                }

                taskList.add(newTask);
            }
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
