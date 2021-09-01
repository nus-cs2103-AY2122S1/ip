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

    public void action(String input) {
        if (input.equals("list")) {
            listTasks();
        } else if (input.startsWith("done")) {
            markAsDone(input);
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        } else if (input.startsWith("find")) {
            findTask(input);
        } else {
            addTask(input);
        }
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

    private void listTasks() {
        System.out.println("Here is your to-do list!");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(i + 1 + ". " + myList.get(i).toString());
        }
    }

    private void addTask(String input) {
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
                System.out.println("Invalid task. Please specify the type of task.");
                return;
            }
            System.out.println("I've added this task:");
            System.out.println(myList.get(myList.size() - 1));
            System.out.println("Now you have " + myList.size() + " tasks.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid format. Please follow this format. <type of task> <description> /<date if necessary>");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please type something after specifying the type of task.");
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format. Please provide your date in this format: yyyy-mm-dd");
        }
    }

    private void deleteTask(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList.get(index - 1);
            myList.remove(index - 1);
            System.out.println("I've deleted this task:\n[X] " + myTask.description);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after the keyword: delete");
        } catch (IndexOutOfBoundsException | NullPointerException e ) {
            System.out.println("Please input a valid task index");
        }
    }

    private void findTask(String input) {
        String[] segments = input.split(" ");
        try {
            System.out.println("Here are the matching tasks:");
            int count = 1;
            for (int i = 0; i < myList.size(); i++) {
                String task = myList.get(i).toString();
                if (task.contains(segments[1])) {
                    System.out.println(count + ". " + task);
                    count++;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input a word after the keyword: find");
        }
    }


    private void markAsDone(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList.get(index - 1);
            myTask.markAsDone();
            System.out.println("I've marked this task as done:\n[X] " + myTask.description);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after the keyword: done");
        } catch (IndexOutOfBoundsException | NullPointerException e ) {
            System.out.println("Please input a valid task index");
        }
    }
}
