package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.DukeException;
import duke.exception.IndexOutOfRangeException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.MissingArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Encapsulates the task List which contains a list of tasks
 *
 * @author Keith Tan
 */
public class Tasklist {

    private static final DateTimeFormatter[] DATE_FORMATTERS = {
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("mm-DD-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-DD-mm HH:mm"),
    };

    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Getter that returns the current length of the list
     *
     * @return int returns current length of list
     */
    public int getSize() {

        return this.tasks.size();

    }

    /**
     * Checks whether user inputted the time for the deadline/event task
     *
     * @param strArr String array containing the inputted description and time
     * @param event String stating the type of task to be added
     * @return String Returns the time of the task
     * @throws MissingArgumentException throws a MissingArgumentException if no time found
     * @throws InvalidArgumentException throws a InvalidArgumentException if error occurs when parsing
     *                                  inputted command line for dates
     */
    private DukeDate checkTime(String[] strArr, String event) throws MissingArgumentException,
        InvalidArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("time", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("time", event);
        } else {

            assert strArr[1].trim().length() > 2 : "Invalid Time inputted";
            if (event.equals("deadline")) {
                return new DukeDate(parseDateTime(strArr[1], event));
            } else {
                //if task is an event
                String[] eventDuration = strArr[1].split(" to ", 2);
                return new DukeDate(parseDateTime(eventDuration[0], event),
                        parseDateTime(eventDuration[1], event));

            }
        }
    }

    /**
     * Parses the LocalDateTime from the inputted date and time string in the command
     *
     * @param dateTime String array containing the inputted date and time
     * @param event String stating the type of task to be added
     * @return LocalDateTime Returns the time of the task
     * @throws InvalidArgumentException throws an InvalidArgumentException if error occurs when parsing
     *                                  inputted command line for dates
     */
    public LocalDateTime parseDateTime(String dateTime, String event) throws InvalidArgumentException {

        LocalDateTime result = null;
        for (DateTimeFormatter df : DATE_FORMATTERS) {
            try {
                result = LocalDateTime.parse(dateTime, df);
                break;
            } catch (DateTimeParseException dtpe) {
                // Ignore; try next formatter
            }
        }

        if (result == null) {
            throw new InvalidArgumentException("times", event);
        } else {
            return result;
        }

    }

    /**
     * Adds tasks read from store in hard drive
     *
     * @param storeTask Task that was saved in hard drive to be loaded into  current task list
     */
    public void addTask(Task storeTask) {
        tasks.add(storeTask);
    }

    /**
     * Adds the task to the list
     *
     * @param description String containing the description and time of the task
     * @param task Duke.util.Duke.command.Command stating the type of task to be added
     * @return String Returns the success message of added the task to the list
     * @throws DukeException throws a duke exception depending on the error found
     */
    public String addTask(String description, String task) throws DukeException {

        Task newTask;
        switch(task) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            String[] deadlineDetails = description.split(" /by ", 2);
            DukeDate deadlineTime = checkTime(deadlineDetails, "deadline");
            newTask = new Deadline(deadlineDetails[0], deadlineTime);
            break;
        case "event":
            String[] eventDetails = description.split(" /at ", 2);
            DukeDate eventTime = checkTime(eventDetails, "event");
            newTask = new Event(eventDetails[0], eventTime);
            break;
        default:
            //unexpected error occurs
            throw new InvalidCommandException();
        }

        tasks.add(newTask);
        String successMessage = "Got it. I've added this task:\n"
                + "  " + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return successMessage;
    }

    /**
     * Marks the designated tasks as complete
     *
     * @param taskNumber task to be marked as complete
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String markTask(int taskNumber) throws IndexOutOfRangeException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {

            if (tasks.get(taskNumber - 1).completeTask()) {

                String markTaskMessage = "Nice! I've marked this task as done:\n"
                        + tasks.get(taskNumber - 1).toString();
                return markTaskMessage;

            } else {

                String markTaskMessage = tasks.get(taskNumber - 1).toString() + " already marked!";
                return markTaskMessage;

            }

        }

    }

    /**
     * Update the designated tasks description
     *
     * @param taskNumber task whose description is to be updated
     * @param newDescription new description string for the task
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String updateDescription(int taskNumber, String newDescription) throws IndexOutOfRangeException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {
            tasks.get(taskNumber - 1).changeDescription(newDescription);
            String updateDescriptionMessage = "Nice! I've changed the description of the task:\n"
                    + "  " + tasks.get(taskNumber - 1).toString();
            return updateDescriptionMessage;
        }
    }

    /**
     * Update the designated tasks start date time
     *
     * @param taskNumber task whose description is to be updated
     * @param newDateTime new date time string for the start time of task
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     * @throws InvalidArgumentException throws the exception if user tries to change
     *                                  the start time of a non-event task
     */
    public String updateStartDateTime(int taskNumber, String newDateTime) throws IndexOutOfRangeException,
        InvalidArgumentException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {

            Task currentTask = tasks.get(taskNumber - 1);
            boolean isEvent = currentTask instanceof Event;
            if (isEvent) {
                Event currentEvent = (Event) currentTask;
                LocalDateTime officialNewStartDateTime = parseDateTime(newDateTime, "update");
                currentEvent.changeStartDateTime(officialNewStartDateTime);
                String updateStartDateTimeMessage = "Nice! I've changed the start time of the task:\n"
                        + "  " + currentTask.toString();
                return updateStartDateTimeMessage;
            } else {

                String taskName = currentTask instanceof ToDo ? "todo" : "deadline";
                throw new InvalidArgumentException("times", taskName);

            }


        }
    }

    /**
     * Update the designated tasks end date time
     *
     * @param taskNumber task whose description is to be updated
     * @param newDateTime new date time string for the start time of task
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     * @throws InvalidArgumentException throws the exception if user tries to change
     *                                  the start time of a non-event task
     */
    public String updateEndDateTime(int taskNumber, String newDateTime) throws IndexOutOfRangeException,
        InvalidArgumentException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {

            Task currentTask = tasks.get(taskNumber - 1);
            boolean isToDo = currentTask instanceof ToDo;
            if (!isToDo) {
                if (currentTask instanceof Event) {

                    Event currentEvent = (Event) currentTask;
                    LocalDateTime officialNewEndDateTime = parseDateTime(newDateTime, "update");
                    currentEvent.changeEndDateTime(officialNewEndDateTime);
                    String updateEndDateTimeMessage = "Nice! I've changed the end time of the task:\n"
                            + "  " + currentTask.toString();
                    return updateEndDateTimeMessage;

                } else {
                    Deadline currentEvent = (Deadline) currentTask;
                    LocalDateTime officialNewEndDateTime = parseDateTime(newDateTime, "update");
                    currentEvent.changeEndDateTime(officialNewEndDateTime);
                    String updateEndDateTimeMessage = "Nice! I've changed the start time of the task:\n"
                            + currentTask.toString();
                    return updateEndDateTimeMessage;
                }

            } else {
                throw new InvalidArgumentException("set time", "update todo");
            }


        }
    }

    /**
     * Delete the designated tasks from the task list
     *
     * @param taskNumber task to be deleted
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String deleteTask(int taskNumber) throws IndexOutOfRangeException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {

            Task taskToRemove = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            String deletedTaskMessage = "Noted. I've removed this task:\n"
                    + "  " + taskToRemove.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";;
            return deletedTaskMessage;

        }

    }

    /**
     * Filter the task list to find tasks whose description the fit the search term
     *
     * @param searchTerm search term to filter the task list by
     * @return String message to be printed depending on if any tasks are found
     */
    public String filterTask(String searchTerm) {

        Tasklist tempList = new Tasklist();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.checkTerm(searchTerm)) {
                tempList.addTask(currentTask);
            }
        }
        return tempList.getSize() == 0 ? "No tasks fit search term"
                : "Here are the matching tasks in your list:\n" + tempList.toString();

    }

    @Override
    public String toString() {

        String listString = IntStream.range(0, tasks.size())
                .mapToObj(index -> String.format("%d.%s\n", index + 1 , tasks.get(index).toString()))
                .collect(Collectors.joining());

        return listString;

    }

}
