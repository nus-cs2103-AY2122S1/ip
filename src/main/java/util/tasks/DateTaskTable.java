package util.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class DateTaskTable extends HashMap<LocalDate, ArrayList<DatedTask>> {

    public void add(DatedTask task) {
        task.addTo(this);

    }

    public ArrayList<DatedTask> get(LocalDate date) {
        ArrayList<DatedTask> res = super.get(date);
        if (res == null) {
            return new ArrayList<>();
        }
        return res;
    }

}
