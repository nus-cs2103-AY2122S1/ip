package blue;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import blue.task.Deadline;
import blue.task.Event;
import blue.task.Task;
import blue.task.ToDo;

/**
 * Reads tasks from file and writes tasks to file.
 */
class Storage {
    private static final String CANNOT_CREATE_TASK_MESSAGE = "No task can be created from the representation!";
    private static final String DIVIDER = "\n\n";
    private static final String DELIMITER = "\n";
    private static final String DONE = "done";
    private static final String NOT_DONE = "not done";
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the entire file into a List of Tasks and returns it.
     *
     * @return A List of Tasks.
     */
    List<Task> load() throws BlueException {
        List<Task> taskList = new ArrayList<>();
        try {
            String allContent = Files.readString(Path.of(filePath)).strip();
            String[] taskRepresentations = allContent.split(DIVIDER);
            addAll(taskList, taskRepresentations);
            return taskList;
        } catch (IOException e) {
            throw new BlueException("An error occurred when loading from " + filePath);
        }
    }

    private void addAll(List<Task> tasks, String[] taskRepresentations) throws BlueException {
        for (String taskRepresentation : taskRepresentations) {
            addTaskIfValid(tasks, taskRepresentation);
        }
    }

    private void addTaskIfValid(List<Task> tasks, String taskRepresentation) throws BlueException {
        if (taskRepresentation.length() > 0) {
            Task task = makeTask(taskRepresentation);
            tasks.add(task);
        }
    }

    /**
     * Returns a blue.Task constructed based on the given representation.
     *
     * @param taskRepresentation Representation of a task.
     * @return blue.Task constructed from its representation.
     * @throws BlueException If no tasks can be created from the taskRepresentation.
     */
    private Task makeTask(String taskRepresentation) throws BlueException {
        String[] lines = taskRepresentation.split(DELIMITER);
        String classRepr = lines[0];
        String title = lines[1];

        Task task;
        switch (classRepr) {
        case ToDo.REPRESENTATION:
            task = new ToDo(title);
            break;
        case Deadline.REPRESENTATION:
            String by = lines[3];
            task = new Deadline(title, by);
            break;
        case Event.REPRESENTATION:
            String at = lines[3];
            task = new Event(title, at);
            break;
        default:
            throw new BlueException(CANNOT_CREATE_TASK_MESSAGE);
        }

        boolean isDone = getDoneStatus(lines);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    private boolean getDoneStatus(String[] lines) throws BlueException {
        if (lines[2].equals(DONE)) {
            return true;
        } else if (lines[2].equals(NOT_DONE)) {
            return false;
        } else {
            throw new BlueException(CANNOT_CREATE_TASK_MESSAGE);
        }
    }

    void save(TaskList taskList) {
        try {
            createIfNotExists(filePath);
            BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath));
            writeAllTasks(writer, taskList.getAll());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeAllTasks(BufferedWriter writer, List<Task> all) throws IOException {
        for (Task task : all) {
            writer.write(makeString(task));
            writer.write(DIVIDER);
        }
    }

    private void createIfNotExists(String filePath) throws IOException {
        if (filePath.contains("/")) {
            int dirFileDividerIndex = filePath.lastIndexOf('/');
            String directories = filePath.substring(0, dirFileDividerIndex);
            if (!Files.isDirectory(Path.of(directories))) {
                Files.createDirectories(Path.of(directories));
            }
        }
        if (Files.notExists(Path.of(filePath))) {
            Files.createFile(Path.of(filePath));
        }
    }

    private String makeString(Task task) {
        List<String> lines = new ArrayList<>();
        if (task instanceof ToDo) {
            lines.add(ToDo.REPRESENTATION);
        } else if (task instanceof Deadline) {
            lines.add(Deadline.REPRESENTATION);
        } else if (task instanceof Event) {
            lines.add(Event.REPRESENTATION);
        }
        lines.add(task.getTitle());
        lines.add(task.isDone() ? DONE : NOT_DONE);
        if (task instanceof Deadline) {
            lines.add(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            lines.add(((Event) task).getAt());
        }
        return String.join(DELIMITER, lines);
    }
}
