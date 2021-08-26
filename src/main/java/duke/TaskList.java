package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> xs = new ArrayList<>();

    public TaskList(File file) throws DukeException {
        try {
            fileCopy(file);
        } catch (IOException e) {
            throw new DukeException("Error, file cannot be found, creating new destination");
        }
    }

    public TaskList() {
        File data = new File("data/duke.txt");
        File directory = new File("data");
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!data.exists()) {
                data.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public TaskList(ArrayList<Task> xs) {
        this.xs = xs;
    }

    public LocalDateTime dateFormatting(String stringDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(stringDate, dateTimeFormatter);
        return localDate;
    }


    public void fileCopy(File dukeData) throws IOException {
        Scanner fileScanner = new Scanner(dukeData);
        while (fileScanner.hasNextLine()) {
            String s = fileScanner.nextLine();
            if (s.equals("") || s.equals((" "))) {
                continue;
            }
            String parts[] = s.split("\\|", 5);
            if (parts[0].equals("D")) {
                Deadline dl = new Deadline(parts[2], dateFormatting(parts[3]));
                if (parts[1].equals("1")) {
                    dl.changeIsDone(true);
                }
                xs.add(dl);
            } else if (parts[0].equals("T")) {
                Todo td = new Todo(parts[2]);
                if (parts[1].equals("1")) {
                    td.changeIsDone(true);
                }
                xs.add(td);
            } else if (parts[0].equals("E")) {
                Event e = new Event(parts[2], dateFormatting(parts[3]));
                if (parts[1].equals("1")) {
                    e.changeIsDone(true);
                }
                xs.add(e);
            }
        }
        fileScanner.close();
    }

    public boolean printItems() throws DukeException {
        boolean allDone = true;
        if (xs.size() == 0) {
            throw new DukeException("    There are no items in the list!");
        }
        for (int i = 0; i < xs.size(); i++) {
            System.out.println("    " + (i + 1) + ": " + xs.get(i));
            if (!xs.get(i).getIsDone() || xs.size() == 0) {
                allDone = false;
            }
        }
        return allDone;
    }

    public Task retrieveTask(int startOfString) throws DukeException {
        if (startOfString > xs.size() || startOfString < 0) {
            throw new DukeException("    Uh oh! Item " + startOfString + " does not seem to exist!");
        }
        Task taskToChange = xs.get(startOfString - 1);
        taskToChange.changeIsDone(true);
        return taskToChange;
    }

    public Task deleteTask(int startOfString) throws DukeException {
        if (startOfString > xs.size() || startOfString < 0) {
            throw new DukeException("    Uh oh! Item " + startOfString + " does not seem to exist!");
        }
        Task taskToDelete = xs.get(startOfString - 1);
        xs.remove(startOfString - 1);
        return taskToDelete;
    }

    public int addToList(String type, String description, String time) throws DukeException {
        try {
            LocalDateTime ld = LocalDateTime.now();
            if (!(time.equals("NA"))) {
                ld = dateFormatting(time);
            }
            if (ld.compareTo(LocalDateTime.now()) < 0 && (type.equals("D") || type.equals("E"))) {
                throw new DukeException("    Please key in a date that's not in the past!");
            }
            Task toAdd;
            if (type.equals("D")) {
                toAdd = new Deadline(description, ld);
            } else if (type.equals("E")) {
                toAdd = new Event(description, ld);
            } else {
                toAdd = new Todo(description);
            }
            xs.add(toAdd);
            return xs.size();
        } catch (DateTimeParseException e) {
            throw new DukeException("    Oh oh! Please follow the format strictly and key in a suitable date!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaskList)) {
            return false;
        }
        TaskList other = (TaskList) obj;
        return this.xs.equals(other.xs);
    }
}
