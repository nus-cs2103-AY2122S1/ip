package duke;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Parses the inputs
 */
public class Parser {

    /** Input string by the user. */
    private String inp;

    /**
     * Constructor to initialise the input.
     * @param inp input
     */
    public Parser(String inp) {
        this.inp = inp;
    }

    /**
     * Parses the task array list and prints the list.
     * @param ob The TaskList object.
     */
    public void parseTaskList(TaskList ob) {
        for (int j = 0; j < ob.numberOfTasks(); j++) {
            if (!ob.getTaskFromList(j).getPreExisting()) {
                System.out.println("    " + (j + 1) + ". " + ob.getTaskFromList(j).toString());
            } else {
                System.out.println("    " + (j + 1) + ". " + ob.getTaskFromList(j).getDescription());
            }
        }
    }

    /**
     * Finds task with specific keywords from list.
     * @param ob TaskList object.
     * @param inputFind Keyword to find. 
     */
    public void findTaskFromTaskList(TaskList ob, String inputFind) {
        String trimmedFind = inputFind.split("\\s", 2)[1];
        boolean flag = true;
        int i=1;
        for (int j = 0; j < ob.numberOfTasks(); j++) {
            if(!ob.getTaskFromList(j).getPreExisting()) {
                if(ob.getTaskFromList(j).toString().contains(trimmedFind)){
                    flag = false;
                    System.out.println("    " + (i++) + ". " + ob.getTaskFromList(j).toString());
                }
            } else {
                if(ob.getTaskFromList(j).getDescription().contains(trimmedFind)) {
                    flag = false;
                    System.out.println("    " + (i++) + ". " + ob.getTaskFromList(j).getDescription());
                }
            }
        }
        if(flag) {
            System.out.println("oops! sorry you do not have any matching tasks");
        }
    }

    /**
     * Returns the tasks in the list in string format.
     * @param ob The TaskList object.
     * @return String containing the tasks in the list.
     */
    public String getTaskList(TaskList ob) {
        String ret="";
        for (int j = 0; j < ob.numberOfTasks(); j++) {
            if (!ob.getTaskFromList(j).getPreExisting()) {
                ret = ret + ob.getTaskFromList(j).toString() + System.lineSeparator();
            } else {
                ret = ret + ob.getTaskFromList(j).getDescription() + System.lineSeparator();
            }
        }
        return ret;
    }

    /**
     * Parses the input task to extract the task content.
     * @return The actual task content.
     */
    public String parseTask() {
        String desc = ((inp.split("\\s",2)[1]).split("/"))[0];
        return desc;
    }

    /**
     * Parses the input task to extract the date.
     * @return The date of the task.
     */
    public String parseTime() {
        String atByTime = ((inp.split("\\s", 2)[1]).split("/"))[1];
        String time = atByTime.split("\\s", 2)[1];
        LocalDate d1 = LocalDate.parse(time);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the input index of the task
     * in the done or delete commands.
     * @return index
     */
    public int getTaskIndex() {
        int ind = Integer.parseInt((inp.split("\\s"))[1]) - 1;
        return ind;
    }
}
