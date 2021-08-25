package myjournal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import myjournal.task.Deadline;
import myjournal.task.Event;
import myjournal.task.Task;
import myjournal.task.Todo;

public class Storage {
    private File file;

    public Storage(String filePath) {
        File file = new File(filePath);
        this.file = file;
    }

    public void saveFile(String newFile) throws IOException {
        FileWriter writer = new FileWriter(this.file, false);
        writer.write(newFile);
        writer.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String currLine;
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            String taskDescription = currLine.substring(5);
            boolean isDone = currLine.charAt(2) == '1';
            if (currLine.charAt(0) == 'T') {
                Todo todo = new Todo(taskDescription);
                todo.setState(isDone);
                tasks.add(todo);
            } else {
                String[] arr = taskDescription.split(" ");
                String description = "";
                String time = "";
                boolean check = false;
                for (String s : arr) {
                    if (s.equals("|")) {
                        check = true;
                    } else {
                        if (check) {
                            time = time + " " + s;
                        } else {
                            description = description + s + " ";
                        }
                    }
                }
                if (currLine.charAt(0) == 'E') {
                    Event event = new Event(description, time);
                    event.setState(isDone);
                    tasks.add(event);
                } else {
                    Deadline deadline = new Deadline(description, time);
                    deadline.setState(isDone);
                    tasks.add(deadline);
                }
            }
        }
        return tasks;
    }
}
