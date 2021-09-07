package duke;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * Class that contains the list of Tasks.
 */

public class TaskList {
    private LinkedList<Task> myList;
    private LinkedList<Task> previousList;

    /**
     * Constructor for TaskList.
     * @param myList a LinkedList.
     */
    public TaskList(LinkedList<Task> myList) {
        this.myList = myList;
        this.previousList = myList;
    }

    /**
     * Method that is called to display the list.
     * @throws DukeException
     *
     */
    protected String list() throws DukeException {
        if (myList.isEmpty()) {
            throw new DukeException("Yo! Your list looks empty to me!");
        }
        String result = "Here are the tasks in your list:\n";
        int counter = 0;
        while (counter < myList.size()) {
            result = result + (counter + 1) + ". " + myList.get(counter).toString() + "\n";
            counter += 1;
        }
        return result;
    }

    /**
     * Method that is called to set a task to done.
     * @param nextLine the line of input
     * @throws DukeException
     */
    protected String setDone(String nextLine) throws DukeException {
        String[] splitWords = nextLine.split(" ");
        if (splitWords.length == 1 && splitWords[0].equals("done")) {
            throw new DukeException("Dude I don't think you told me which task you're talking about!");
        } else if (!splitWords[0].equals("done")) {
            throw new DukeException("Dude, I don't understand your instructions!");
        }
        try {
            int taskNumber = Integer.parseInt(splitWords[1]);
            if (taskNumber > myList.size()) {
                throw new DukeException("Dude I don't think you have a list THAT long!");
            } else if (myList.get(taskNumber - 1).getDone()) {
                throw new DukeException("This task is already done man!");
            } else {
                updatePreviousList();
                return myList.get(taskNumber - 1).setDone(true);

            }
        } catch (NumberFormatException ex) {
            throw new DukeException("Woah, enter the task number properly..");
        }
    }

    /**
     * Method to update the previous taskList before updating the current taskList.
     */
    private void updatePreviousList() {
        this.previousList = new LinkedList<Task>(myList);;
    }

    /**
     * Method that is called to delete a task from the list.
     * @param nextLine the next line of input
     * @throws DukeException
     */
    protected String deleteTask(String nextLine) throws DukeException {
        String[] splitWords = nextLine.split(" ");
        if (splitWords.length == 1 && splitWords[0].equals("delete")) {
            throw new DukeException("Dude I don't think you told me which task you're talking about!");
        } else if (!splitWords[0].equals("delete")) {
            throw new DukeException("Dude, I don't understand your instructions!");
        }
        try {
            int taskNumber = Integer.parseInt(splitWords[1]);
            if (taskNumber > myList.size()) {
                throw new DukeException("Dude I don't think you have a list THAT long!");
            } else {
                String infoOfTask = myList.get(taskNumber - 1).toString();
                updatePreviousList();
                myList.remove(taskNumber - 1);
                return "Noted. I've removed this task:\n" + infoOfTask;
            }
        } catch (NumberFormatException ex) {
            throw new DukeException("Woah, enter the task number properly..");
        }
    }

    /**
     * Method to create a new Todo.
     * @param nextLine the next line of input
     * @throws DukeException
     */
    protected String newTodo(String nextLine) throws DukeException {
        if (nextLine.replaceAll("\\s", "").length() == 4) {
            throw new DukeException("Seems like your todo task was incomplete!");
        } else {
            String[] splitLine = nextLine.split("todo");
            String title = splitLine[1];
            Todo nextTask = new Todo(title);
            updatePreviousList();
            myList.add(nextTask);
            return "Got it. I've added this task:\n" + nextTask.toString();
        }
    }

    /**
     * Method to create a new Deadline.
     * @param nextLine the next line of input
     * @throws DukeException
     */
    protected String newDeadline(String nextLine) throws DukeException {
        if (nextLine.replaceAll("\\s", "").length() == 8) {
            throw new DukeException("Seems like your deadline task was incomplete!");
        } else {
            try {
                String[] splitLine = nextLine.split("/by ");
                String date = splitLine[1];
                LocalDateTime parsedDate = dateParser(date);
                String title = splitLine[0].split("deadline")[1];
                Deadline nextTask = new Deadline(title, parsedDate);
                updatePreviousList();
                myList.add(nextTask);
                return "Got it. I've added this task:\n" + nextTask.toString();
            } catch (DateTimeException | ArrayIndexOutOfBoundsException err) {
                throw new DukeException("I think there's a problem with your input! "
                        + "Enter your task in this format! \"yyyy-MM-dd HHmm\"");
            }
        }
    }

    /**
     * Method to create a new Event.
     * @param nextLine the next line of input
     * @throws DukeException
     */
    protected String newEvent(String nextLine) throws DukeException {
        if (nextLine.replaceAll("\\s", "").length() == 5) {
            throw new DukeException("Seems like your event task was incomplete!");
        } else {
            try {
                String[] splitLine = nextLine.split("/at ");
                String date = splitLine[1];
                LocalDateTime parsedDate = dateParser(date);
                String title = splitLine[0].split("event")[1];
                Event nextTask = new Event(title, parsedDate);
                updatePreviousList();
                myList.add(nextTask);
                return "Got it. I've added this task:\n" + nextTask.toString();
            } catch (DateTimeException | ArrayIndexOutOfBoundsException err) {
                throw new DukeException("I think there's a problem with your input! "
                        + "Enter your task in this format! \"yyyy-MM-dd HHmm\"");
            }
        }
    }

    /**
     * Method to parse the date input from String to LocalDateTime
     * @param stringDate the input String date
     * @return the LocalDateTime
     */
    private LocalDateTime dateParser(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime parsedDate = LocalDateTime.parse(stringDate, formatter);
        return parsedDate;
    }



    /**
     * Method that returns the current size of list.
     */
    protected String getInfo() {
        return "Now you have " + myList.size() + " tasks in the list. You're welcome!";
    }

    /**
     * Method that gives you the list stored in the class variable
     * @return myList
     */
    protected LinkedList<Task> getList() {
        return myList;
    }


    protected String findTask(String nextLine) throws DukeException {
        String[] splitWords = nextLine.split(" ");
        if (splitWords.length == 1 && splitWords[0].equals("find")) {
            throw new DukeException("Dude at least enter a word you want me to look for!");
        } else {
            String keyword = splitWords[1];
            LinkedList<Task> copyList = new LinkedList<Task>(myList);
            copyList.removeIf(task -> {
                String taskTitle = task.getTitle();
                String[] taskWords = taskTitle.split(" ");
                for (String word : taskWords) {
                    if (word.equals(keyword)) {
                        return false;
                    } else { }
                }
                return true;
                // !task.getTitle().contains(keyword)
            });
            if (copyList.isEmpty()) {
                throw new DukeException("Yo! I can't find this keyword in your list!");
            }
            String result = "Here are the matching tasks in your list:\n";
            int counter = 0;
            while (counter < copyList.size()) {
                result = result + (counter + 1) + ". " + copyList.get(counter).toString() + "\n";
                counter += 1;
            }
            return result;
        }

    }

    public String undo() throws DukeException {

        if (!this.myList.equals(previousList)) {
            this.myList = previousList;
            return ("I've undone your last command!");
        } else {
            throw new DukeException("I can't go any further back in time!");
        }
    }
}
