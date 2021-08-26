/**
 * @author Hang Zelin
 * @description Stores all the tasks for the Duke. Duke can refer to this tasklist to see a specific task
 * or make use of the methods in it to execute an operation.
 */
package duke.task;

import duke.command.Parser;
import duke.excpetions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TaskList {

    ArrayList<Task> tasks;

    /**
     * @author Hang Zelin
     * @description Constructor to store all the tasks in a Generic ArrayList.
     */
    public TaskList(ArrayList<Task> tasks) {

        this.tasks = tasks;
    }

    /**
     * @author Hang Zelin
     * @description Another Constructor to initialize an empty TaskList if there is no save data.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * @param time
     * @return void
     * @author Hang Zelin
     * @description get all the tasks that match the time users take in.
     */
    public void getSpecificDateEvent(String time) {
        Parser p = new Parser("");
        int count = 0;//count the number of the events happen on the time.

        for (int i = 0; i < tasks.size(); i++) {
            String Message = tasks.get(i).getTaskInfo();
            String UnParsedInfo = tasks.get(i).getTime();
            String timeInFormat = (p.ParseTime(time) != null) ?
                    p.ParseTime(time).format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH))
                    : "Nope";

            if ((UnParsedInfo != null && (UnParsedInfo.contains(time) || UnParsedInfo.contains(timeInFormat)))
                    || Message.contains(time) || Message.contains(timeInFormat)) {
                count++;
                System.out.println(count + "." + Message);
            }
        }
        if (count == 0) {
            System.out.println("Sorry. There is no tasks occurred on the time you give me!! :(");
        }
    }

    /**
     * @param keyword
     * @return void
     * @author Hang Zelin
     * @description find all the tasks that match the key word users take in.
     */
    public void FindTask(String keyword) {
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            String Message = tasks.get(i).getTaskInfo();
            if (Message.contains(keyword)) {
                count++;
                System.out.println(count + "." + Message);
            }
        }

        if (count == 0) {
            System.out.println("Sorry. There is no tasks matching the keyword you give me!! :(");
        }
    }

    /**
     * @param index
     * @return void
     * @author Hang Zelin
     * @description Mark a specific task as done.
     */
    public void MarkDone(int index) {
        this.tasks.get(index).MarkDone();
    }

    /**
     * @param index
     * @return void
     * @author Hang Zelin
     * @description delete a specific task.
     */
    public void Delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * @param taskType
     * @param task
     * @param time
     * @return void
     * @throws DukeException
     * @author Hang Zelin
     * @description Add a task to the TaskLists. This method will automatically decide which type of the
     * task is added to the list.
     */
    public void add(String taskType, String task, String time) throws DukeException {
        Parser p = new Parser("");

        LocalDateTime parsedTime = p.ParseTime(time);
        OperationType[] taskTypes = OperationType.values();
        for (OperationType t : taskTypes) {
            if (t.toString().equals(taskType)) {
                Task newTask = t.AssignTaskType(t, task, parsedTime);
                tasks.add(newTask);
                break;
            }
        }

    }

    /**
     * @param index
     * @return Task
     * @author Hang Zelin
     * @description Return a specific task users are referring to.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }


    /**
     * @param
     * @return int
     * @author Hang Zelin
     * @description Return the size of the TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * @param index
     * @return void
     * @throws DukeException
     * @author Hang Zelin
     * @description detect if the index taking in is invalid or not.
     */
    public void detectIndex(int index) throws DukeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but the index is invalid :-(");
        }
    }

    /**
     * @author Hang Zelin
     * @description The enum of all types of operations that is able to execute.
     * It also contains a method AssignTask Type to find the specific type of task to create.
     */
    public enum OperationType {
        bye, done, delete, tell, find, list, todo, deadline, event;

        public Task AssignTaskType(OperationType t, String task, LocalDateTime time) {
            switch (t) {
                case todo:
                    return new ToDos(false, task);
                case deadline:
                    return new Deadlines(false, task, time);
                case event:
                    return new Events(false, task, time);
                default:
                    return null;
            }
        }
    }
}
