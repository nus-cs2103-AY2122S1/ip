package duke;

import duke.Deadline;
import duke.Event;
import duke.Task;

import java.time.LocalDate;
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
     * @param input
     */

    public String action(String input) {
        String output;
        if (input.equals("list")) {
            output = listTasks();
        } else if (input.startsWith("done")) {
            output = markAsDone(input);
        } else if (input.startsWith("delete")) {
            output = deleteTask(input);
        } else if (input.startsWith("find")) {
            output = findTask(input);
        } else {
            output = addTask(input);
        }
        return output;
    }

    /**
     * forms a String of all the tasks, for saving into file
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

    private String addTask(String input) {
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
                output = "Invalid task. Please specify the type of task.";
                return output;
            }
            output = "I've added this task:\n";
            output = output + myList.get(myList.size() - 1) + "\n";
            output = output + "Now you have " + myList.size() + " tasks.\n";
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "Invalid format. Please follow this format. <type of task> <description> /<date if necessary>";
        } catch (StringIndexOutOfBoundsException e) {
            output = "Invalid input. Please type something after specifying the type of task.";
        } catch (DateTimeParseException e) {
            output = "Wrong date format. Please provide your date in this format: yyyy-mm-dd";
        }
        return output;
    }

    private String deleteTask(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList.get(index - 1);
            myList.remove(index - 1);
            return "I've deleted this task:\n[X] " + myTask.description + "\n";
        } catch (NumberFormatException e) {
            return "Please input a number after the keyword: delete";
        } catch (IndexOutOfBoundsException | NullPointerException e ) {
            return "Please input a valid task index";
        }
    }

    private String findTask(String input) {
        String[] segments = input.split(" ");
        try {
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
        } catch (NumberFormatException e) {
            return "Please input a word after the keyword: find";
        }
    }


    private String markAsDone(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList.get(index - 1);
            myTask.markAsDone();
            return "I've marked this task as done:\n[X] " + myTask.description + "\n";
        } catch (NumberFormatException e) {
            return "Please input a number after the keyword: done";
        } catch (IndexOutOfBoundsException | NullPointerException e ) {
            return "Please input a valid task index";
        }
    }
}
