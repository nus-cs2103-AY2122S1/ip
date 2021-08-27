package util.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import util.parser.Parser;



public abstract class DatedTask extends Task {
    protected LocalDate lDate;

    /**
     * The constructor for a datedtask.
     *
     * @param name The name of the task
     * @param date The date of the task.
     */
    public DatedTask(String name, String date) {
        super(name);
        this.lDate = Parser.dateParse(date);
    }


    /**
     * The localdate in the format to print out.
     *
     * @return The local date in the format MMM dd yyyy
     */
    public String localDate() {
        return lDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Adds the dated task to the input table.
     *
     *
     * @param table The hashmap to put the dated task in.
     */
    public void addTo(HashMap<LocalDate, ArrayList<DatedTask>> table) {
        ArrayList<DatedTask> list = table.get(this.lDate);
        if (list == null) {
            list = new ArrayList<>();
            table.put(this.lDate, list);
            table.get(this.lDate).add(this);

        } else if (TaskList.isAdded(this, list)) {
            //it has been added before

        } else {

            list.add(this);

        }


    }

    /**
     * Removes the DatedTask from the input DateTaskTable.
     *
     *
     * @param table
     */
    public void removeFromTable(DateTaskTable table) {
        ArrayList<DatedTask> ls = table.get(this.lDate);
        if (ls != null) {
            ls.remove(this);
        }

    }



    @Override
    public boolean isDated() {
        return true;
    }



}
