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
            if (savedTask.startsWith("todo")) {
                task = Parser.parseStoredTodoTasks(savedTask);
            } else if (savedTask.startsWith("deadline")) {
                task = Parser.parseStoredDeadlineTasks(savedTask);
            } else if (savedTask.startsWith("event")) {
                task = Parser.parseStoredEventTasks(savedTask);
            } else {
                System.out.println("Sorry! There was an error loading your tasks!");
                break;
            }
            TaskList.getTaskList().add(task);
        }
        sc.close();
    }
}
