import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class TodoList {
    private final LinkedList<Task> tasks = new LinkedList<>();
    public TodoList(File file) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskCode = s.nextLine();
                tasks.add(decode(taskCode));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Task decode(String taskCode) {
        String[] taskDetails = taskCode.split("\\|");
        String taskType = taskDetails[0];
        boolean done = taskDetails[1].equals("1");
        switch (taskType) {
            case "T":
                return new Todo(done, taskDetails[2]);
            case "E": {
                String info = taskDetails[3];
                return new Event(done, taskDetails[2], info);
            }
            case "D": {
                String info = taskDetails[3];
                return new Deadline(done, taskDetails[2], LocalDateTime.parse(info));
            }
            default:
                return null;
        }
    }

    public void addTodo(String taskName) {
        Todo todo = new Todo(false, taskName);
        tasks.add(todo);
        PrintResponse.print(String.format("Caan Do!\n  added: %s\n" +
                        "Look at me! " +
                        "%d tasks in the list now!",
                taskName,
                tasks.size()));
    }

    public void list() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        PrintResponse.print(
                String.format("Ooh yeah! Here are your %d tasks\n%s",
                        tasks.size(),
                        result.toString()));
    }

    public void markAsDone(int taskNumber) throws DukeException{
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            String response = String.format("Ooh yeah! Task %d marked as done:\n  %s",
                    taskNumber,
                    task);
            PrintResponse.print(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }

    public void addDeadline(String name, String dateTime) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);

            Deadline deadline = new Deadline(false, name, localDateTime);
            tasks.add(deadline);

            PrintResponse.print(
                    String.format("Caan Do!\n" +
                                    "  added: %s\n" +
                                    "Look at me! %d tasks in the list now!",
                            deadline,
                            tasks.size()));
        } catch (DateTimeParseException e) {
            throw new DukeException(">:( follow datetime format of dd-mm-yyyy HHmm");
        }
    }

    public void addEvent(String name, String dateTime) {
        Event event = new Event(false, name, dateTime);
        tasks.add(event);
        PrintResponse.print(
                String.format("Caan Do!\n" +
                        "  added: %s\n" +
                        "Look at me! %d in the list now!",
                        event,
                        tasks.size()));
    }

    public void deleteTask(int taskNumber) throws DukeException{
        try {
            Task task = tasks.remove(taskNumber - 1);
            String response = String.format("Ooh yeah! Task %d deleted:\n  %s" +
                            "\nNow you have %d tasks in the list.",
                    taskNumber,
                    task,
                    tasks.size());
            PrintResponse.print(response);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Task number %d invalid.", taskNumber));
        }
    }

    public void writeToDisk() throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");

            StringBuilder result = new StringBuilder();
            for (Task task : tasks) {
                result.append(String.format("%s\n", task.encode()));
            }
            fw.write(String.valueOf(result));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
