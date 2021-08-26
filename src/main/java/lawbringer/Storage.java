package lawbringer;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    protected void rewriteFile(TaskList taskList) throws IOException {
        StringBuilder newFileText = new StringBuilder();
        for (Task task : taskList.getList()) {
            newFileText.append(task.toString()).append("\n");
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(String.valueOf(newFileText));
        fw.close();
    }

    protected List<Task> createTaskList() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            char taskType = line.charAt(1);
            boolean isDone = line.charAt(4) == 'X';
            switch (taskType) {
            case 'T':
                ToDo todo = new ToDo(line.substring(7));
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case 'D':
                int byIndex = line.indexOf(" (by: ");
                Deadline deadline = new Deadline(line.substring(7, byIndex),
                        line.substring(byIndex + 6, line.length() - 1));
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case 'E':
                int atIndex = line.indexOf(" (at: ");
                Event event = new Event(line.substring(7, atIndex),
                        line.substring(atIndex + 6, line.length() - 1));
                if (isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            }
        }
        return tasks;
    }
}
