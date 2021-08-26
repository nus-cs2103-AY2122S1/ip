package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getFile() throws DukeException {
        try {
            File localFile = new File(filePath);
            localFile.getParentFile().mkdir();
            if (localFile.createNewFile()) {
                return new ArrayList<>();
            } else {
                ArrayList<Task> taskList = new ArrayList<>();
                Scanner fileScanner = new Scanner(localFile);
                while (fileScanner.hasNextLine()) {
                    String data = fileScanner.nextLine();
                    String[] parameters = data.split(" / ");
                    switch (parameters[0]) {
                    case "T":
                        ToDo toDo = new ToDo(parameters[2]);
                        toDo.setDone(Integer.parseInt(parameters[1]));
                        taskList.add(toDo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parameters[2], LocalDate.parse(parameters[3]));
                        deadline.setDone(Integer.parseInt(parameters[1]));
                        taskList.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(parameters[2], LocalDate.parse(parameters[3]));
                        event.setDone(Integer.parseInt(parameters[1]));
                        taskList.add(event);
                        break;
                    }
                }
                fileScanner.close();
                return taskList;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("An error occurred. :-(\n");
        }
    }

    public void saveList(ArrayList<Task> taskList) throws DukeException {
        StringBuilder content = new StringBuilder();
        for (Task task : taskList) {
            content.append(task.toFileFormat()).append("\n");
        }
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content.toString());
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred. :-(\n");
        }
    }
}
