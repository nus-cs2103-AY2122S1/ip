package duke;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

/**
 * Encapsulates the list in Duke that stores all the tasks
 */
public class DukeTaskList {
    private List<Task> taskList;

    /**
     * Constructor for the DukeTaskList class.
     */
    public DukeTaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * Reads data from the save file, and load them
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
        default:
            assert false : "Data file contains a task type not yet implemented!";
        }

        if (state.equals("1")) {
            currTask.markAsDone();
        }
        taskList.add(currTask);
    }

    /**
     * Sends tasks in the list to the save file.
     *
     * @return a string that represents all tasks in list for storing in the save file.
     */
    public String sendListToFile() {
        StringBuilder strBuilder = new StringBuilder();
        for (Task task : taskList) {
            strBuilder.append(task.toDataFileString()).append("\n");
        }
        return strBuilder.toString();
    }

    /**
     * Returns task list in string.
     *
     * @return task list.
     */
    public String displayList() {
        StringBuilder strBuilder = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            strBuilder.append(i + 1).append(".").append(currTask.toString()).append("\n");
        }
        return strBuilder.toString();
    }

    /**
     * Marks a task as done in the list.
     *
     * @param doneTaskNo the task number to be marked as done.
     * @throws DukeException if task number entered out of range.
     * @return done task response.
     */
    public String doneTask(int doneTaskNo) throws DukeException {
        if (doneTaskNo < 1 || doneTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }
        Task doneTask = taskList.get(doneTaskNo - 1);
        doneTask.markAsDone();
        return "Nice! I've marked this task as done:\n" + doneTask.toString();
    }

    /**
     * Deletes a task from the list.
     *
     * @param deleteTaskNo the task number to be deleted.
     * @throws DukeException if task number entered out of range.
     * @return delete task response.
     */
    public String deleteTask(int deleteTaskNo) throws DukeException {
        if (deleteTaskNo < 1 || deleteTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }
        Task deleteTask = taskList.get(deleteTaskNo - 1);
        taskList.remove(deleteTaskNo - 1);
        return "Noted. I've removed this task:\n" + deleteTask.toString() + "\n"
                + String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Updates a task from the list. Update command format must be:
     * "update taskNo category content"
     * category can be description, date, time (for deadline), start_time and end_time (for events)
     *
     * @param updateCommand update command line.
     * @throws DukeException if command format incorrect, or task number entered out of range.
     * @throws NumberFormatException if task number entered cannot be parsed.
     * @throws DateTimeParseException if date/time entered cannot be parsed.
     * @return update task response.
     */
    public String updateTask(String updateCommand) throws DukeException,
            NumberFormatException, DateTimeParseException {
        String[] updateSplit = updateCommand.split(" ", 3);
        if (updateSplit.length < 3) {
            throw new DukeException("Update command format incorrect!\n");
        }
        // Throws NumberFormatException if string cannot be parsed into valid int
        int updateTaskNo = Integer.parseInt(updateSplit[0]);
        if (updateTaskNo < 1 || updateTaskNo > taskList.size()) {
            // Task No entered out of range
            throw new DukeException("Task number entered out of range!\n");
        }

        Task updateTask = taskList.get(updateTaskNo - 1);

        if (updateTask instanceof ToDos) {
            if (updateSplit[1].equals("description")) {
                updateTask.setDescription(updateSplit[2]);
            } else {
                throw new DukeException("You can only update description for Todo!");
            }
        } else if (updateTask instanceof Deadlines) {
            Deadlines ddl = (Deadlines) updateTask;
            if (updateSplit[1].equals("description")) {
                ddl.setDescription(updateSplit[2]);
            } else if (updateSplit[1].equals("date")) {
                // Throws DateTimeParseException if date cannot be parsed
                ddl.setDate(LocalDate.parse(updateSplit[2]));
            } else if (updateSplit[1].equals("time")) {
                // Throws DateTimeParseException if time cannot be parsed
                ddl.setTime(LocalTime.parse(updateSplit[2]));
            } else {
                throw new DukeException("You can only update description, date or time for deadline!");
            }
        } else if (updateTask instanceof Events) {
            Events event = (Events) updateTask;
            if (updateSplit[1].equals("description")) {
                event.setDescription(updateSplit[2]);
            } else if (updateSplit[1].equals("date")) {
                // Throws DateTimeParseException if date cannot be parsed
                event.setDate(LocalDate.parse(updateSplit[2]));
            } else if (updateSplit[1].equals("start_time")) {
                // Throws DateTimeParseException if time cannot be parsed
                event.setStartTime(LocalTime.parse(updateSplit[2]));
            } else if (updateSplit[1].equals("end_time")) {
                // Throws DateTimeParseException if time cannot be parsed
                event.setStartTime(LocalTime.parse(updateSplit[2]));
            } else {
                throw new DukeException("You can only update description, date, start and end time for events!");
            }
        } else {
            assert false : "Update command intakes a type of task not implemented yet!";
        }

        return "Nice! The task is updated as follows:\n" + updateTask.toString();
    }

    /**
     * Searches for tasks in the list by a keyword.
     *
     * @param keyword keyword to be searched.
     * @return matching tasks in string.
     */
    public String searchTask(String keyword) {
        List<Task> matchingTaskList = new ArrayList<>();
        for (Task task : taskList) {
            String[] taskDescriptionWords = task.getDescription().split(" ");
            for (String word : taskDescriptionWords) {
                if (word.equals(keyword)) {
                    matchingTaskList.add(task);
                    break;
                }
            }
        }

        StringBuilder strBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTaskList.size(); i++) {
            Task currTask = matchingTaskList.get(i);
            strBuilder.append(i + 1).append(".").append(currTask.toString()).append("\n");
        }

        return strBuilder.toString();
    }

    /**
     * Adds a todos task to the list.
     *
     * @param toDoText description of the todos task.
     * @return add Todos response.
     */
    public String addToDo(String toDoText) {
        Task currTask = new ToDos(toDoText); // description trimmed of trailing white space behind
        taskList.add(currTask);
        return "Got it. I've added this task:\n" + currTask.toString() + "\n" +
                String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Adds a deadline task to the list. Input format for deadline:
     * /by yyyy-mm-dd hh:mm (ISO_LOCAL_DATE, space, ISO_LOCAL_TIME)
     *
     * @param ddlText description of the deadline task
     * @param ddlDate date when deadline is due.
     *                Must be in the format yyyy-mm-dd.
     * @param ddlTime time point when deadline is due.
     *
     * @return add deadline response.
     */
    public String addDeadline(String ddlText, LocalDate ddlDate, LocalTime ddlTime) {
        Task currTask = new Deadlines(ddlText, ddlDate, ddlTime);
        taskList.add(currTask);
        return "Got it. I've added this task:\n" + currTask.toString() + "\n" +
                String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Adds an event task to the list. Input format for event:
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
     * @return add event response.
     */
    public String addEvent(String eventText, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
        Task currTask = new Events(eventText, eventDate, eventStartTime, eventEndTime);
        taskList.add(currTask);
        return "Got it. I've added this task:\n" + currTask.toString() + "\n" +
                String.format("Now you have %d tasks in the list.\n", taskList.size());
    }
}
