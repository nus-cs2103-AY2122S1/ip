package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> myList;

    /**
     * initialises the TaskList (supposed to have loading functionality, but will add on later)
     */

    public TaskList(ArrayList<Task> parsedList) {
        myList = parsedList;
    }

    /**
     * calls an appropriate method based on the input param
     *
     * @param input
     */

    public String action(String input) throws DukeException {
        try {
            String output;
            if (input.equals("list")) {
                output = listTasks();
            } else if (input.startsWith("done")) {
                output = markAsDone(input);
            } else if (input.startsWith("delete")) {
                output = deleteTask(input);
            } else if (input.startsWith("find")) {
                output = findTask(input);
            } else if (input.startsWith("view")) {
                output = viewTask(input);
            } else {
                output = addTask(input);
            }
            assert output != null;
            return output;
        } catch (DukeException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * forms a String of all the tasks, for saving into file
     *
     * @return String
     */

    public String output() {
        String output = "";
        for (int i = 0; i < myList.size(); i++) {
            output = output + myList.get(i) + "\n";
        }
        return output;
    }

    private String listTasks() {
        String output = "Here is your to-do list!\n";
        for (int i = 0; i < myList.size(); i++) {
            int index = i + 1;
            output = output + index + ". " + myList.get(i).toString() + "\n";
        }
        return output;
    }

    private String addTask(String input) throws DukeException {
        String output = "";
        try {
            if (input.startsWith("todo")) {
                String remaining = input.substring(5);
                myList.add(new ToDo(remaining));
            } else if (input.startsWith("deadline")) {
                String remaining = input.substring(9);
                String[] segments = remaining.split(" /");
                LocalDate myDate = LocalDate.parse(segments[1]);
                myList.add(new Deadline(segments[0], myDate));
            } else if (input.startsWith("event")) {
                String remaining = input.substring(6);
                String[] segments = remaining.split(" /");
                LocalDate myDate = LocalDate.parse(segments[1]);
                myList.add(new Event(segments[0], myDate));
            } else {
                throw new DukeException("Invalid task. Please specify the type of task.");
            }
            output = "I've added this task:\n";
            output = output + myList.get(myList.size() - 1) + "\n";
            output = output + "Now you have " + myList.size() + " tasks.\n";
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid format. Please follow this format. <type of task> <description> /<date if necessary>");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input. Please type something after specifying the type of task.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format. Please provide your date in this format: yyyy-mm-dd");
        }
        return output;
    }

    private String deleteTask(String input) throws DukeException {
        String[] segments = input.split(" ");
        if (segments.length <= 1) {
            throw new DukeException("Please input a number after the keyword: delete");
        }

        int index;

        try {
            index = Integer.parseInt(segments[segments.length - 1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number after the keyword: delete");
        }

        if (index <= 0 || index > myList.size()) {
            throw new DukeException("Please input a valid task index");
        }
        Task myTask = myList.get(index - 1);
        myList.remove(index - 1);
        return "I've deleted this task:\n[X] " + myTask.description + "\n";
    }

    private String findTask(String input) throws DukeException {
        String[] segments = input.split(" ");
        if (segments[1] == null) {
            throw new DukeException("Please input a word after the keyword: find");
        }
        String output = "Here are the matching tasks:\n";
        int count = 1;
        for (int i = 0; i < myList.size(); i++) {
            String task = myList.get(i).toString();
            if (task.contains(segments[1])) {
                output = output + count + ". " + task + "\n";
                count++;
            }
        }
        return output;
    }

    private String viewTask(String input) throws DukeException {
        String[] segments = input.split(" ");
        if (segments[1] == null) {
            throw new DukeException("Please input a date after the keyword: view");
        }
        LocalDate myDate;
        try {
            myDate = LocalDate.parse(segments[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format. Please provide your date in this format: yyyy-mm-dd");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String output = "Here are your tasks for that day:\n";
        int count = 1;
        for (int i = 0; i < myList.size(); i++) {
            LocalDate taskDate = myList.get(i).getDate();
            if (myDate.equals(taskDate)) {
                output = output + count + ". " + myList.get(i).toString() + "\n";
                count++;
            }
        }
        return output;
    }


    private String markAsDone(String input) throws DukeException {
        String[] segments = input.split(" ");
        if (segments[1] == null) {
            throw new DukeException("Please input a number after the keyword: done");
        }

        int index;

        try {
            index = Integer.parseInt(segments[segments.length - 1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number after the keyword: done");
        }
        if (index <= 0 || index > myList.size()) {
            throw new DukeException("Please input a valid task index");
        }
        Task myTask = myList.get(index - 1);
        myTask.markAsDone();
        return "I've marked this task as done:\n[X] " + myTask.description + "\n";
    }
}
