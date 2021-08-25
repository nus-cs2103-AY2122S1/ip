package duke;

import duke.task.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the list in Duke that stores all the tasks
 */
public class DukeTaskList {
    public List<Task> taskList;

    /**
     * Constructor for the DukeTaskList class.
     */
    public DukeTaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * Method to read data from the save file, and load them
     * into the list in a current run of Duke.
     *
     * @param type  type of task stored in save file.
     * @param state state of task stored in save file. 0 for not done and 1 for done.
     * @param body  includes the description, date(if any) and time(if any)
     *              stored in save file.
     */
    public void loadDataToList(String type, String state, String body) {
        Task currTask = null;
        String[] bodySplit = body.split("\\|", 4);

        switch (type) {
        case "T":
            currTask = new ToDos(body);
            break;
        case "D":
            currTask = new Deadlines(bodySplit[0],
                LocalDate.parse(bodySplit[1]),
                LocalTime.parse(bodySplit[2]));
            break;
        case "E":
            currTask = new Events(bodySplit[0],
                LocalDate.parse(bodySplit[1]),
                LocalTime.parse(bodySplit[2]),
                LocalTime.parse(bodySplit[3]));
            break;
        }

        assert currTask != null;
        if (state.equals("1")) {
            currTask.markAsDone();
        }
        taskList.add(currTask);
    }

    /**
     * Method to send tasks in the list to the save file.
     *
     * @return a string that represents all tasks in list for storing in the save file.
     */
    public String sendListToFile() {
        StringBuilder StrBuilder = new StringBuilder();
        for (Task task : taskList) {
            StrBuilder.append(task.toDataFileString()).append("\n");
        }
        return StrBuilder.toString();
    }

    /**
     * Method to send the entire list for printing.
     *
     * @return the list in DukeTaskList.
     */
    public List<Task> sendListForPrint() {
        return taskList;
    }

    /**
     * Method to mark a task as done in the list.
     *
     * @param doneTaskNo the task number to be marked as done.
     * @throws DukeException if task number entered out of range.
     */
    public void doneTask(int doneTaskNo) throws DukeException {
        if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }
        Task doneTask = taskList.get(doneTaskNo - 1);
        doneTask.markAsDone();
        Ui.printDoneTask(doneTask.toString());
    }

    /**
     * Method to delete a task from the list.
     *
     * @param deleteTaskNo the task number to be deleted.
     * @throws DukeException if task number entered out of range.
     */
    public void deleteTask(int deleteTaskNo) throws DukeException {
        if (deleteTaskNo < 1 || deleteTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }
        Task deleteTask = taskList.get(deleteTaskNo - 1);
        taskList.remove(deleteTaskNo - 1);
        Ui.printDeleteTask(deleteTask.toString(), taskList.size());
    }

    /**
     * Search for tasks in the list by a keyword.
     *
     * @param keyword keyword to be searched.
     */
    public void searchTask(String keyword) {
        List<Task> matchingTaskList = new ArrayList<>();
        for (Task task : taskList) {
            String[] taskDescriptionWords = task.getDescription().split(" ");
            for (String word : taskDescriptionWords) {
                if (word.equals(keyword)){
                    matchingTaskList.add(task);
                    break;
                }
            }
        }
        Ui.printFindTask(matchingTaskList);
    }

    /**
     * Method to add a todos task to the list.
     *
     * @param toDoText description of the todos task
     */
    public void addToDo(String toDoText) {
        Task currTask = new ToDos(toDoText); // description trimmed of trailing white space behind
        taskList.add(currTask);
        Ui.printAddTask(currTask.toString(), taskList.size());
    }

    /**
     * Method to add a deadline task to the list. Input format for deadline:
     * /by yyyy-mm-dd hh:mm (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME)
     *
     * @param DdlText description of the deadline task
     * @param DdlDate date when deadline is due.
     *                Must be in the format yyyy-mm-dd.
     * @param DdlTime time point when deadline is due.
     *                Must be in the format hh:mm in 24-hours time.
     */
    public void addDeadline(String DdlText, LocalDate DdlDate, LocalTime DdlTime) {
        Task currTask = new Deadlines(DdlText, DdlDate, DdlTime);
        taskList.add(currTask);
        Ui.printAddTask(currTask.toString(), taskList.size());
    }

    /**
     * Method to add a event task to the list. Input format for event:
     * /at yyyy-mm-dd hh:mm-hh:mm
     * (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME, dash, ISO_LOCAL_TIME)
     *
     * @param eventText      description of the event.
     * @param eventDate      date when the event happens.
     *                       Must be in the format yyyy-mm-dd.
     * @param eventStartTime time point when the event starts.
     *                       Must be in the format hh:mm in 24-hours time.
     * @param eventEndTime   time point when the event ends.
     *                       Must be in the format hh:mm in 24-hours time.
     */
    public void addEvent(String eventText, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
        Task currTask = new Events(eventText, eventDate, eventStartTime, eventEndTime);
        taskList.add(currTask);
        Ui.printAddTask(currTask.toString(), taskList.size());
    }
}
