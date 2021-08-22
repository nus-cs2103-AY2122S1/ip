package util.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public abstract class DatedTask extends Task {
    protected LocalDate lDate;

    public DatedTask(String name, LocalDate date) {
        super(name);

        this.lDate = date;
    }

    public String localDate() {
        return lDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public void addTo(DateTaskTable table) {
        ArrayList<DatedTask> list = table.get(this.lDate);
        if (list == null) {
            list = new ArrayList<>();
            table.put(lDate, list);
        }
        list.add(this);

    }


}
