package duke.Tasks;

import duke.PrintType;
import duke.RegexType;
import duke.exceptions.*;

import java.util.ArrayList;

public class TaskManager {
    public ArrayList<Task> tasks = new ArrayList<>();

    public String getAcknowledgement(String line, int index) {
        return line + "\n " + tasks.get(index).showTask();
    }

    public String getNumOfTask() {
        return "Now you have " + tasks.size() + " tasks in the list";
    }
    // Print list of task
    public String getList() throws EmptyListException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }
        StringBuilder fullList = new StringBuilder(PrintType.LIST_INTRO_LINE.getPrintType());

        // Add each item to listLines
        for (int i = 1; i <= tasks.size(); ++i) {
            fullList.append("\n").append(i).append(". ").append(tasks.get(i - 1).showTask());
//            fullList = fullList + "\n" +  i + ". " + tasks.get(i - 1).showTask();

        }

        return fullList.toString();
    }

    // Adding a duke.tasks.Todo to list of tasks
    public String addTodo(String userInput) throws TodoException {

        if (userInput.equals("todo") || userInput.equals("")){
            throw new TodoException();
        }
        // Create new duke.tasks.Todo instance an add it to end taskList
        tasks.add(new Todo(userInput));

        return getAcknowledgement(PrintType.TASK_DELETED_LINE.getPrintType(), tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    // Adding a duke.tasks.Deadline to list of tasks
    public String addDeadline(String userInput) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!userInput.matches(RegexType.DEADLINE_REGEX.getRegexType())){
            throw new DeadlineException();
        }

        // get description and by from line
        String description = userInput.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String by = userInput.replaceAll(RegexType.GET_BY_REGEX.getRegexType(), "");

        // Create new duke.tasks.Deadline instance an add it to end taskList
        tasks.add(new Deadline(description, by));

        return getAcknowledgement(RegexType.TASK_ADDED_LINE.getRegexType(), tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    // Adding an duke.tasks.Event to list of tasks
    public String addEvent(String userInput) throws EventException {
        // Check if userInput follows the format "<description> /at <time/date>"
        if (!userInput.matches(RegexType.EVENT_REGEX.getRegexType())){
            throw new EventException();
        }

        // get description and by from userInput
        String description = userInput.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String at = userInput.replaceAll(RegexType.GET_AT_REGEX.getRegexType(), "");


        // Create new duke.tasks.Event instance an add it to end taskList
        tasks.add(new Event(description, at));

        return getAcknowledgement(PrintType.TASK_ADDED_LINE.getPrintType(), tasks.size()-1)
                + "\n" + getNumOfTask();
    }

    // Delete task
    public String deleteTask(String userInput)
            throws DeleteFormatException, DeleteRangeException {

        if (!userInput.matches(RegexType.DIGITS_REGEX.getRegexType())){
            throw new DeleteFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;

        if (index >= tasks.size()) {
            throw new DeleteRangeException();
        }


        String acknowledgement = getAcknowledgement(PrintType.TASK_DELETED_LINE.getPrintType(), index);

        tasks.remove(index);

        return acknowledgement + "\n" + getNumOfTask();
    }



    // Mark the task at the given index as done
    public String markAsDone(String userInput)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException {

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(RegexType.DIGITS_REGEX.getRegexType())){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= tasks.size()){
            throw new DoneRangeException();
        }
        // Check if task is already done
        if (tasks.get(index).isDone()){
            throw new DoneAlreadyException();
        }

        // Mark the index as done
        tasks.get(index).markAsDone();

        // Acknowledge task is done
        return getAcknowledgement(PrintType.TASK_DONE_LINE.getPrintType(), index);

    }

    public void clearList(){
        tasks.clear();
    }






}
