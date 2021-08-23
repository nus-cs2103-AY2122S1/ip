package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public TaskList readTaskList() throws IOException {
        Files.createDirectories(this.path.getParent());
        if (!Files.exists(this.path)) {
            Files.createFile(this.path);
        }
        return new TaskList(
            Files.lines(this.path).map((taskRow) -> parseTaskRow(taskRow))
                    .collect(Collectors.toList())
        );
    }

    public void writeTaskList(TaskList tasks) throws IOException {
        Stream<String> linesStream = tasks.stream()
                .map((task) -> genTaskRow(task));
        Files.write(this.path, (Iterable<String>) linesStream::iterator);
    }

    private static Task parseTaskRow(String taskRow) {
        var taskParts = taskRow.split(" \\| ", 3);
        var taskType = TaskType.convertTaskIcon(taskParts[0]);
        var taskIsDone = Boolean.parseBoolean(taskParts[1]);
        var taskLine = taskParts[2];

        var task = Parser.parseTaskLine(taskLine, taskType);
        task.toggle(taskIsDone);
        return task;
    }

    public static String genTaskRow(Task task) {
        return String.format(
            "%s | %b | %s",
            TaskType.identifyTask(task).getTaskIcon(),
            task.getIsDone(),
            genTaskLine(task)
        );
    }

    public static String genTaskLine(Task task) {
        var taskType = TaskType.identifyTask(task);
        switch (taskType) {
        case TODO:
            return task.getDescription();
        case DEADLINE:
            var deadline = (Deadline) task;
            return String.format(
                "%s /by %s",
                deadline.getDescription(),
                deadline.getDeadline()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
        case EVENT:
            var event = (Event) task;
            return String.format(
                "%s /at %s",
                event.getDescription(),
                event.getTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
        default:
            throw new UnsupportedOperationException(
                "task type is not a valid enum value"
            );
        }
    }
}
