package duke.storage;

import duke.DukeExceptions;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

    private static final String DIVIDER = "%%";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list! \n");
        int index = 1;
        for (Task task : this) {
            result.append(index)
                    .append(". ")
                    .append(task.toString())
                    .append('\n');
            index++;
        }
        return (result.toString().stripTrailing());
    }

    public void importFromList(List<String> inputString) {
        this.clear();
        for (String line : inputString) {
            String[] splitString = line.split(DIVIDER, 0);
            Task task;
            if (splitString[0].equals("T")) {
                task = ToDo.create(splitString[2]);

            } else if (splitString[0].equals("E")) {
                task = Event.create(splitString[2], LocalDateTime.parse(splitString[3],
                        formatter));
            } else {
                task = Deadline.create(splitString[2], LocalDateTime.parse(splitString[3],
                        formatter));
            }
            task.markFinished(splitString[1].equals("X"));
            this.add(task);
        }
    }

    public String exportToText() {
        StringBuilder result = new StringBuilder();
        for (Task currTask : this) {
            result.append(currTask.getTaskType())
                    .append(DIVIDER)
                    .append(currTask.getStatusIcon())
                    .append(DIVIDER)
                    .append(currTask.getDescription());
            if (!(currTask.getTaskType().equals("T"))) {
                result.append(DIVIDER)
                        .append(currTask.getTime());
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void markAsFinished(int index) throws DukeExceptions {
        Task task = this.get(index);
        if (task.getStatusIcon().equals("X")) {
            throw new DukeExceptions("That task was already marked as done");
        } else{
            task.markFinished(true);
        }
    }

}
