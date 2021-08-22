package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(File data) throws FileNotFoundException {
        list = new ArrayList<>();

        Scanner dataScanner = new Scanner(data);
        while (dataScanner.hasNext()) {
            String dataLine = dataScanner.nextLine();
            String[] split = dataLine.split(" ");
            String type = split[0];
            boolean taskDone = split[2].equals("1"); //if taskDone = 1, task was done
            String desc;

            switch (type) {
            case "T":
                StringBuilder todoBuilder = new StringBuilder();
                for (int i = 4; i < split.length; i++) {
                    if (i != 4) {
                        todoBuilder.append(" ");
                    }
                    todoBuilder.append(split[i]);
                }
                desc = todoBuilder.toString();

                list.add(new Todo(desc, taskDone));
                break;

            case "D":
                StringBuilder deadlineBuilder = new StringBuilder();
                StringBuilder byBuilder = new StringBuilder();
                String by;
                boolean byFound = false;

                for (int i = 4; i < split.length; i++) {
                    if (byFound) {
                        if (!byBuilder.toString().equals("")) {
                            byBuilder.append(" ");
                        }
                        byBuilder.append(split[i]);
                    } else {
                        if (i == 4) {
                            deadlineBuilder.append(split[i]);
                        } else if (split[i].equals("|")) {
                            byFound = true;
                        } else {
                            deadlineBuilder.append(" ");
                            deadlineBuilder.append(split[i]);
                        }
                    }
                }
                desc = deadlineBuilder.toString();
                by = byBuilder.toString();

                list.add(new Deadline(desc, by, taskDone));

                break;

            case "E":
                StringBuilder eventBuilder = new StringBuilder();
                StringBuilder atBuilder = new StringBuilder();
                String at;
                boolean atFound = false;

                for (int i = 4; i < split.length; i++) {
                    if (atFound) {
                        if (!atBuilder.toString().equals("")) {
                            atBuilder.append(" ");
                        }
                        atBuilder.append(split[i]);
                    } else {
                        if (i == 4) {
                            eventBuilder.append(split[i]);
                        } else if (split[i].equals("|")) {
                            atFound = true;
                        } else {
                            eventBuilder.append(" ");
                            eventBuilder.append(split[i]);
                        }
                    }
                }
                desc = eventBuilder.toString();
                at = atBuilder.toString();

                list.add(new Event(desc, at, taskDone));

            }
        }
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void add(Task task) {
        list.add(task);
    }
}
