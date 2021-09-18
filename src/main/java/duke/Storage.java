package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;


    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves existing tasks to the storage file.
     */
    public void saveTasksToFile() {
        // @author aakanshanarain - reused
        // Reused from https://github.com/Wincenttjoi/CS2103T-duke-chatbot
        // with minor modifications
        try {
            BufferedWriter taskWriter = new BufferedWriter(new FileWriter(filePath));
            String tasks = "";
            for (Task task : TaskList.getTaskList()) {
                tasks += task.toStringForStorage() + "\n";
            }
            taskWriter.write(tasks);
            taskWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry! The file cannot be found!, " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file to the UI.
     *
     * @throws IOException if the file cannot be found.
     */
    public void loadTasksToUI() throws IOException {
        // @author aakanshanarain - solution inspired by
        // https://github.com/Wincenttjoi/CS2103T-duke-chatbot
        File file = new File(this.filePath);

        // creates data directory if it does not exist
        file.getParentFile().mkdirs();

        // creates tasks.txt if it does not exist
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String savedTask = sc.nextLine();
            Task task;
            int isDoneIndex = savedTask.indexOf("/");
            String isTaskDone = savedTask.substring(isDoneIndex + 2);
            if (savedTask.startsWith("todo")) {
                task = new Todo(savedTask.substring(5, isDoneIndex));
            } else if (savedTask.startsWith("deadline")) {
                int charIndex = savedTask.indexOf("|");
                int byIndex = charIndex + 2;
                String by = savedTask.substring(byIndex, isDoneIndex);
                String deadlineTask = savedTask.substring(9, charIndex - 1);
                task = new Deadline(deadlineTask, by);
            } else if (savedTask.startsWith("event")) {
                int charIndex = savedTask.indexOf("|");
                int atIndex = charIndex + 2;
                String at = savedTask.substring(atIndex, isDoneIndex);
                String eventTask = savedTask.substring(6, charIndex - 1);
                task = new Event(eventTask, at);
            } else {
                System.out.println("Sorry! There was an error loading your tasks!");
                break;
            }
            if (isTaskDone.equals("true")) {
                task.markAsDone();
            }
            TaskList.getTaskList().add(task);
        }
        sc.close();
    }
}
