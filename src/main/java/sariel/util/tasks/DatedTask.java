package sariel.util.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import sariel.util.parser.Parser;


/**
 * An abstract class representing a Task that has a date.
 *
 */
public abstract class DatedTask extends Task {
    protected LocalDate localDate;

    /**
     * The constructor for a datedtask.
     *
     * @param name The name of the task
     * @param date The date of the task.
     */
    public DatedTask(String name, String date) {
        super(name);
        this.localDate = Parser.dateParse(date);
    }



    /**
     * The localdate in the format to print out.
     *
     * @return The local date in the format MMM dd yyyy
     */
    public String localDate() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Adds the dated task to the input table.
     *
     *
     * @param table The hashmap to put the dated task in.
     */
    public void addTo(HashMap<LocalDate, ArrayList<DatedTask>> table) {
        ArrayList<DatedTask> list = table.get(this.localDate);
        if (list == null) {
            list = new ArrayList<>();
            table.put(this.localDate, list);
            table.get(this.localDate).add(this);

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
        ArrayList<DatedTask> ls = table.get(this.localDate);
        if (ls != null) {
            ls.remove(this);
        }

    }


    /**
     * Ensures that the arguments are inline with
     * description {delimiter} date.
     *
     * @param ss
     * @throws DukeException
     */
    protected static void enforceArguments(String[] ss, String errorMessage) throws DukeException {
        if (ss.length == 0) {
            throw new DukeException(errorMessage);
        }
    }



    @Override
    public boolean isDated() {
        return true;
    }

    @Override
    public LocalDate getDate() {
        return this.localDate;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof DatedTask) {
            DatedTask dt = (DatedTask) o;
            return dt.localDate.equals(this.localDate) && dt.name.equals(this.name);
        }
        return false;
    }
}
