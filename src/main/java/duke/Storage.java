package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                list.add(parseInput(str));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found. Here is your error message: " + e.getMessage());
        }

        return list;
    }

    public void update(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                bw.write(task.toFileFormat());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            throw new DukeException("File not updated properly. Here is your error message: " + e.getMessage());
        }
    }

    private static Task parseInput(String str) throws DukeException {
        String[] taskArr = str.split(" \\| ");
        Task newTask;

        switch (taskArr[0]) {
            case "T":
                Todo todo = new Todo(taskArr[2]);
                if (taskArr[1].equals("1")) {
                    todo.setDone();
                }
                newTask = todo;
                break;
            case "E":
                Event event = new Event(taskArr[2], LocalDate.parse(taskArr[3]));
                if (taskArr[1].equals("1")) {
                    event.setDone();
                }
                newTask = event;
                break;
            case "D":
                Deadline deadline = new Deadline(taskArr[2], LocalDate.parse(taskArr[3]));
                if (taskArr[1].equals("1")) {
                    deadline.setDone();
                }
                newTask = deadline;
                break;
            default:
                throw new DukeException("File is corrupted. Please either create a new file or check your existing file.");
        }

        return newTask;
    }
}
