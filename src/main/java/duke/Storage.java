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
                String[] taskArr = str.split(" \\| ");

                switch (taskArr[0]) {
                    case "T":
                        Todo todo = new Todo(taskArr[2]);
                        list.add(todo);
                        if (taskArr[1].equals("1")) {
                            todo.setDone();
                        }
                        break;
                    case "E":
                        Event event = new Event(taskArr[2], LocalDate.parse(taskArr[3]));
                        list.add(event);
                        if (taskArr[1].equals("1")) {
                            event.setDone();
                        }
                        break;
                    case "D":
                        Deadline deadline = new Deadline(taskArr[2], LocalDate.parse(taskArr[3]));
                        list.add(deadline);
                        if (taskArr[1].equals("1")) {
                            deadline.setDone();
                        }
                        break;
                    default:
                        break;
                }
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
                char taskType = task.toString().charAt(2);
                char isDone = '0';
                if (task.getDone()) {
                    isDone = '1';
                }

                switch (taskType) {
                    case 'T':
                        bw.write(taskType + " | " + isDone + " | " + task.getDescription());
                        bw.newLine();
                        break;
                    case 'E':
                    case 'D':
                        bw.write(taskType + " | " + isDone + " | " + task.getDescription() + " | " + task.getDate());
                        bw.newLine();
                        break;
                    default:
                        throw new DukeException("File corrupted. Please either create new file or check your existing file.");
                }
            }

            bw.close();
        } catch (IOException e) {
            throw new DukeException("File not updated properly. Here is your error message: " + e.getMessage());
        }
    }
}
