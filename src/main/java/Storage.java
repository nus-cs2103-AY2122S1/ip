import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public TaskList loadData() throws IOException {
        List<String> contents = Files.readAllLines(filePath);
        TaskList taskList = new TaskList();
        for (String content : contents) {
            String[] taskDetails = content.split("=");

            if (taskDetails[0].equals("ToDo")) {
                ToDo todo = new ToDo(taskDetails[2]);
                if (taskDetails[1].equals("true")) {
                    todo.markAsCompleted();
                }
                taskList.add(todo);
            } else if (taskDetails[0].equals("Deadline")) {
                Deadline deadline = new Deadline(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("true")) {
                    deadline.markAsCompleted();
                }
                taskList.add(deadline);
            } else {
                Event event = new Event(taskDetails[2], taskDetails[3]);
                if (taskDetails[1].equals("true")) {
                    event.markAsCompleted();
                }
                taskList.add(event);
            }
        }
        return taskList;
    }

    public void saveData(TaskList taskList) throws IOException {
        List<String> lines = new ArrayList<>();
        Ui ui = new Ui();
        for (int i = 0; i < taskList.getSize(); i++) {
            try {
                Task task = taskList.get(i);
                if (task instanceof ToDo) {
                    String line = String.format("ToDo=%s=%s", String.valueOf(task.getCompleted()), task.getTaskName());
                    lines.add(line);
                } else if (task instanceof Event) {
                    String line = String.format("Event=%s=%s=%s", String.valueOf(task.getCompleted()), task.getTaskName(), ((Event) task).getTimePeriod());
                    lines.add(line);
                } else {
                    String line = String.format("Deadline=%s=%s=%s", String.valueOf(task.getCompleted()), task.getTaskName(), ((Deadline) task).getDeadline());
                    lines.add(line);
                }
            } catch (InvalidTaskIDException e) {
                ui.displayError(e);
            }
        }
        Files.write(filePath, lines, StandardCharsets.UTF_8);
    }
}
