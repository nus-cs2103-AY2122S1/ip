package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.task.*;

import static duke.parser.Parser.dateFormatter;
import static duke.parser.Parser.timeFormatter;

public class Storage {

    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f); // create a Scanner using the File as the source
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitStr = input.split("\\|");
                Task t;
                if (splitStr[0].trim().equals("T")) {
                    t = new Todo(splitStr[2].trim());
                } else if (splitStr[0].trim().equals("E")) {
                    String[] dateTime = splitStr[3].trim().split("\\s+");
                    t = new Event(splitStr[2].trim(), timeFormatter(dateTime[1]), dateFormatter(dateTime[0]));
                } else {
                    String[] dateTime = splitStr[3].trim().split("\\s+");
                    t = new Deadline(splitStr[2].trim(), timeFormatter(dateTime[1]), dateFormatter(dateTime[0]));
                }
                list.add(t);
                if (splitStr[1].trim().equals("1")) {
                    t.markTaskDone();
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("Could not find file to load from.");
        }
        return list;
    }

    public void saveToDisk(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.numberOfTasks(); i++) {
            fw.write(tasks.taskNumber(i).outputFormat() + '\n');
        }
        fw.close();
    }
}
