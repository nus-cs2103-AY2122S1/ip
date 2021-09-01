package duke;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the arraylist of tasks
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
     * Adds task to arraylist
     *
     * @param task
     */
    public String list(Task task) {
        listOfTasks.add(task);
        int counter = listOfTasks.size();
        storage.save(listOfTasks);
        return ("Got it. I've added this task:\n" + "  " + task.toString() + "\nNow you have " + counter +
                " tasks in the list.");

    }

    /**
     * deletes task in arraylist at index-1
     *
     * @param index
     */
    public String deleteTask(int index) {
        Task item = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        storage.save(listOfTasks);
        return ("Noted. I've removed this task:\n  " + item + "\nNow you have " + listOfTasks.size() +
                " tasks left in the list");
    }

    /**
     * Prints the task in the arraylist
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
     * Mark as done at index number-1
     *
     * @param number
     */
    public String changeStatus(int number) {
        if (listOfTasks.size() >= number) {
            listOfTasks.get(number - 1).markAsDone();
            storage.save(listOfTasks);
            return ("Nice! I've marked this task as done:\n  " + listOfTasks.get(number - 1));
        } else {
            return "This index does not exist";
        }
    }

    /**
     * Find text on arraylist that contains user input
     *
     * @param toFind is the user input
     */
    public String find(String toFind) {
        int counter = 0;
        String stringToReturn = "Here are the matching tasks in your list:\n";
        for(int i = 0; i < listOfTasks.size(); i++) {
            if(listOfTasks.get(i).toString().contains(toFind)) {
                counter++;
                stringToReturn = stringToReturn + counter + "." + listOfTasks.get(i);
            }
        }
        if(counter == 0) {
            return "There are no matching tasks in your list";
        } else {
            return stringToReturn;
        }
    }

    public void load() {
        File existing = new File("src/main/data/duke.txt");
        try {
            if(existing.exists()) {
                Scanner reader= new Scanner(existing);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    if (data.length() > 0) {
                        if (data.substring(1,2).equals("T")) {
                            String[] split = data.substring(3).split("_");
                            Task tempTask = new ToDo(split[1]);
                            if(split[0].equals("true")) {
                                tempTask.markAsDone();
                            }
                            listOfTasks.add(tempTask);
                        } else if (data.substring(1,2).equals("E")) {
                            String[] split = data.substring(3).split("_");
                            String date = split[2];
                            LocalDate localDate = LocalDate.parse(date);
                            Task tempTask = new Event(split[1], localDate);
                            if(split[0].equals("true")) {
                                tempTask.markAsDone();
                            }
                            listOfTasks.add(tempTask);
                        } else {
                            String[] split = data.substring(3).split("_");
                            String date = split[2];
                            LocalDate localDate = LocalDate.parse(date);
                            Task tempTask = new Deadline(split[1], localDate, split[3]);
                            if(split[0].equals("true")) {
                                tempTask.markAsDone();
                            }
                            listOfTasks.add(tempTask);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing file");
        }

    }
}
