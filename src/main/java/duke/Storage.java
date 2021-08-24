package duke;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        //if file does not exist create one, if exist then ignore
        this.file.createNewFile();
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.file);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String str = taskList.get(i).saveToFile() + "\n";
            sb.append(str);
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException, EmptyFieldException, InvalidCommandException {
        // create a Scanner using the File as the source
        Scanner s = new Scanner(this.file);
        ArrayList<Task> temp = new ArrayList<>();
        while (s.hasNext()) {
            Task task = textToTask(s.nextLine());
            temp.add(task);
        }
        return temp;
    }

    private Task textToTask(String fileInput) throws EmptyFieldException, InvalidCommandException {
        String[] taskString = fileInput.split("\\|");
        Task temp;
        for (int i = 0; i < taskString.length; i++) {
            taskString[i] = taskString[i].strip();
        }

        if (taskString[0].equals("T")) {
            temp = new ToDo(taskString[2]);
        } else if (taskString[0].equals("E")) {
            temp = new Event(taskString[2], taskString[3]);
        } else {
            temp = new Deadline(taskString[2], taskString[3]);
        }

        if (taskString[1].equals("1")) {
            temp.setIsDone(true);
        }
        return temp;
    }
}
