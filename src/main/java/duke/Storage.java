package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * Constructor for a Storage object.
     *
     * @param filePath filepath to store text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method to load tasks from storage and return the list of tasks.
     *
     * @return an ArrayList of Task
     * @throws DukeException if file cannot be loaded
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File storage = new File(filePath);
            storage.createNewFile();

            Scanner savedTasks = new Scanner(storage);

            ArrayList<Task> tasks = new ArrayList<>();

            ArrayList<Integer> tasksToStartAfter = new ArrayList<>();

            while (savedTasks.hasNextLine()) {
                String storedTask = savedTasks.nextLine();
                parseStoredTask(storedTask, tasks, tasksToStartAfter);
            }

            setTaskAfterTaskForAll(tasks, tasksToStartAfter);

            return tasks;

        } catch (IOException e) {
            throw new DukeException("There is a problem loading saved data.");
        }
    }

    private void parseStoredTask(String storedTask, ArrayList<Task> tasks,
            ArrayList<Integer> tasksToStartAfter
    ) throws DukeException {
        if (storedTask.startsWith("[T]")) {
            addToDo(storedTask, tasks, tasksToStartAfter);
        } else if (storedTask.startsWith("[E]")) {
            addEvent(storedTask, tasks, tasksToStartAfter);
        } else if (storedTask.startsWith("[D]")) {
            addDeadline(storedTask, tasks, tasksToStartAfter);
        }
    }

    private void addToDo(String storedTask, ArrayList<Task> tasks,
            ArrayList<Integer> tasksToStartAfter) throws DukeException {
        String[] taskDetails = storedTask.split(" / ");
        String taskDescription = taskDetails[0];

        Task currentTask = new ToDo(taskDescription.substring(7));

        if (storedTask.startsWith("[T][X]")) {
            currentTask.markAsDone();
        }

        setTaskAfterDateTime(storedTask, currentTask);
        registerTaskAfterTask(storedTask, tasksToStartAfter);

        tasks.add(currentTask);
    }

    private void addEvent(String storedTask, ArrayList<Task> tasks,
            ArrayList<Integer> tasksToStartAfter) throws DukeException {
        int at = storedTask.lastIndexOf(" (at: ");
        int end = storedTask.lastIndexOf(")");
        assert at != -1;
        assert end != -1;
        Task currentTask = new Event(
                storedTask.substring(7, at),
                LocalDateTime.parse(storedTask.substring(at + 6, end),
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
        );

        if (storedTask.startsWith("[E][X]")) {
            currentTask.markAsDone();
        }

        setTaskAfterDateTime(storedTask, currentTask);
        registerTaskAfterTask(storedTask, tasksToStartAfter);

        tasks.add(currentTask);
    }

    private void addDeadline(String storedTask, ArrayList<Task> tasks,
            ArrayList<Integer> tasksToStartAfter) throws DukeException {
        int by = storedTask.lastIndexOf(" (by: ");
        int end = storedTask.lastIndexOf(")");
        assert by != -1;
        assert end != -1;
        Task currentTask = new Deadline(
                storedTask.substring(7, by),
                LocalDateTime.parse(storedTask.substring(by + 6, end),
                        DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
        );

        if (storedTask.startsWith("[D][X]")) {
            currentTask.markAsDone();
        }

        setTaskAfterDateTime(storedTask, currentTask);
        registerTaskAfterTask(storedTask, tasksToStartAfter);

        tasks.add(currentTask);
    }

    private void setTaskAfterDateTime(String storedTask, Task currentTask) throws DukeException {
        try {
            String[] taskDetails = storedTask.split(" / ");

            String afterDateTime = taskDetails[taskDetails.length - 2];
            LocalDateTime refDateTime = LocalDateTime.parse(
                    afterDateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            currentTask.setDoAfterDateTime(refDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Storage Date Time parse error");
        }
    }


    private void registerTaskAfterTask(String storedTask, ArrayList<Integer> tasksToStartAfter) throws DukeException {
        try {
            String[] taskDetails = storedTask.split(" / ");

            int refTask = Integer.parseInt(taskDetails[taskDetails.length - 1]);

            tasksToStartAfter.add(refTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Storage Task parse error");
        }
    }

    private void setTaskAfterTaskForAll(ArrayList<Task> tasks,
            ArrayList<Integer> tasksToStartAfter) throws DukeException {
        for (int i = 0; i < tasks.size(); i++) {
            int taskNumToStartAfter = tasksToStartAfter.get(i);
            Task taskToDoAfter = tasks.get(i);

            if (taskNumToStartAfter == 0) {
                taskToDoAfter.setDoAfterTask(EmptyTask.EMPTY_TASK);
            } else {
                Task taskToDoBefore = tasks.get(taskNumToStartAfter - 1);
                taskToDoAfter.setDoAfterTask(taskToDoBefore);
            }
        }
    }

    /**
     * A method to continuously save the current list of tasks.
     *
     * @param tasks a TaskList to be saved
     */
    public void saveTasks(TaskList tasks) {
        String textToAdd = "";

        for (int i = 0; i < tasks.numOfTasks(); i++) {
            textToAdd = textToAdd + tasks.getTask(i).toStorageString(tasks) + System.lineSeparator();
        }

        try {
            FileWriter file = new FileWriter(filePath);
            file.write(textToAdd);
            file.close();
        } catch (IOException e) {
            System.out.println("There is a problem writing saved data.");
        }
    }
}
