package sariel.util.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A class for containing DatedTasks assigned to their corresponding
 * LocalDate.
 *
 *
 */
public class DateTaskTable extends HashMap<LocalDate, ArrayList<DatedTask>> {

    /**
     * Adding the DatedTask to the Table.
     * @param task The DatedTask to add.
     */
    public void add(DatedTask task) {
        assert task != null : "Task added is null";
        task.addTo(this);

    }

    /**
     * The method to get the list of dated tasks
     * that have the same date as the input.
     * @param date The date of the tasks in the result.
     * @return The Dated Tasks that possess the same date as the param date.
     */
    public ArrayList<DatedTask> get(LocalDate date) {
        ArrayList<DatedTask> res = super.get(date);
        if (res == null) {
            return new ArrayList<>();
        }
        return res;
    }



}
