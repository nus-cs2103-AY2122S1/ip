package duke;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the arraylist of tasks.
 */
public class TaskList {
    List<Task> listOfTasks;
    Storage storage = new Storage();

    TaskList() {
        this.listOfTasks = new ArrayList<Task>();

        try {
            storage.createFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds task to arraylist.
     *
     * @param task is one of the T/D/E tasks
     */
    public String list(Task task) {
        if (checkTaskDuplication(task)) {
            return "This task has already been added!";
        }
        listOfTasks.add(task);
        int counter = listOfTasks.size();
        storage.save(listOfTasks); //write data to duke.txt
        return "Got it. I've added this task:\n" + "  " + task.toString() + "\nNow you have " + counter +
                " tasks in the list.";

    }

    private boolean checkTaskDuplication(Task task) {
        String base = task.toString();
        for(int i = 0; i < listOfTasks.size(); i++) {
            String comparison = listOfTasks.get(i).toString();
            if(base.equals(comparison)) {
                return true;
            }
        }
        return false;
    }

    /**
     * deletes task in arraylist at index-1.
     *
     * @param index is the number the user input.
     */
    public String deleteTask(int index) {
        if (index <= listOfTasks.size() && index > 0) {
            Task item = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            storage.save(listOfTasks);
            return ("Noted. I've removed this task:\n  " + item + "\nNow you have " + listOfTasks.size() +
                    " tasks left in the list");
        } else {
            return "Index is invalid";
        }

    }

    /**
     * Prints the task in the arraylist.
     *
     * @return the tasks in the arraylist
     */
    public String printList() {
        if (listOfTasks.size() > 0) {
            String stringToReturn = "Here are the tasks in your list:\n";
            for (int i = 0; i < listOfTasks.size(); i++) {
                stringToReturn = stringToReturn + (i + 1 + "." + listOfTasks.get(i) + "\n");
            }
            return stringToReturn;
        } else {
            return "There are no tasks in your list";
        }
    }

    /**
     * Mark as done at index number-1.
     *
     * @param number
     * @return task marked as done or index invalid
     */
    public String changeStatus(int number) {
        if (listOfTasks.size() >= number && number > 0) {
            listOfTasks.get(number - 1).markAsDone();
            storage.save(listOfTasks);
            return ("Nice! I've marked this task as done:\n  " + listOfTasks.get(number - 1));
        } else {
            return "This index does not exist";
        }
    }

    /**
     * Find text on arraylist that contains user input.
     *
     * @param toFind is a string the user input.
     * @return matching tasks or the lack of it.
     */
    public String find(String toFind) {
        int counter = 0;
        String stringToReturn = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).toString().contains(toFind)) {
                counter++;
                stringToReturn = stringToReturn + counter + "." + listOfTasks.get(i) +"\n";
            }
        }

        if (counter == 0) {
            return "There are no matching tasks in your list";
        }

        return stringToReturn.substring(0,stringToReturn.length()-2);
    }

    private void readFromFile(String text) throws Exception {
        if (text.length() > 0) {
            String category = text.substring(1, 2); //category of task is T,D,E for todo,deadline, event
            String[] splitByUnderscore = text.substring(3).split("_"); //information stored is separated by underscore
            if (category.equals("T")) {
                Task tempTask = new ToDo(splitByUnderscore[1]);
                tempTask = markTaskAsDone(tempTask, splitByUnderscore[0]);
                listOfTasks.add(tempTask);
            } else if (category.equals("E")) {
                String date = splitByUnderscore[2];
                LocalDate localDate = LocalDate.parse(date);
                Task tempTask = new Event(splitByUnderscore[1], localDate);
                tempTask = markTaskAsDone(tempTask, splitByUnderscore[0]);
                listOfTasks.add(tempTask);
            } else if (category.equals("D")) {
                String date = splitByUnderscore[2];
                LocalDate localDate = LocalDate.parse(date);
                Task tempTask = new Deadline(splitByUnderscore[1], localDate, splitByUnderscore[3]);
                tempTask = markTaskAsDone(tempTask, splitByUnderscore[0]);
                listOfTasks.add(tempTask);
            } else {
                throw new Exception("Category not found");
            }
        }
    }

    private Task markTaskAsDone(Task task, String status) {
        if (status.equals("true")) {
            task.markAsDone();
        }
        return  task;
    }

    public void load() {
        File existingFile = new File("src/main/data/duke.txt");
        try {
            if (existingFile.exists()) {
                Scanner reader = new Scanner(existingFile);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    readFromFile(data);
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing file");
        }

    }
}
