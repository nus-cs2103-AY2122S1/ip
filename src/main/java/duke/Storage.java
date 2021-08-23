package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.tasktype.Task;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;
import duke.exception.WrongCommandFormatException;

public class Storage {
    private MyList list;

    public Storage(MyList list) {
        this.list = list;
    }

    public void load() {
        try {
            File dataFile = new File("src/main/java/duke/Data.txt");
            Scanner s = new Scanner(dataFile);
            Duke.setFormat(s.nextLine());
            while (s.hasNextLine()) {
                String input = s.nextLine();
                Task t = getTaskFromString(input);
                this.list.loadTask(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No data to load");
        }
    }

    public Task getTaskFromString(String s) {
        String taskType = s.substring(0, 3);
        String taskDescription = s.substring(7);
        Task t = null;
        try {
            if (s.substring(3,6). equals("[X]")) {
                switch (taskType) {
                case "[T]":
                    t = new Todo(taskDescription, true);
                    break;
                case "[D]":
                    t =  new Deadline(taskDescription, true);
                    break;
                case "[E]":
                    t =  new Event(taskDescription, true);
                    break;
                }
            } else {
                switch (taskType) {
                case "[T]":
                    t = new Todo(taskDescription, false);
                    break;
                case "[D]":
                    t = new Deadline(taskDescription, false);
                    break;
                case "[E]":
                    t = new Event(taskDescription, false);
                    break;
                }
            }
        } catch (WrongCommandFormatException e) {
            Ui.formatExceptionMessage(e);
            Ui.loadingError();
        }
        return t;
    }

    public void writeToFile() {
        try {
            int listLength = this.list.getListSize();
            String input = Duke.getFormat() + "\n";
            FileWriter fw = new FileWriter("src/main/java/Data.txt");
            for (int i = 0; i < listLength; i++) {
                Task t = this.list.getTask(i);
                input += t.createData() + "\n";
            }
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
