package util.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import util.parser.*;

public abstract class DatedTask extends Task {
    protected LocalDate lDate;

    public DatedTask(String name, String date) {
        super(name);
        this.lDate = Parser.dateParse(date);
    }

    public String localDate() {
        return lDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    //this function isn't working
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

    public void removeFromTable(DateTaskTable table) {
        ArrayList<DatedTask> ls = table.get(this.lDate);
        if (ls != null) ls.remove(this);

    }



    @Override
    public boolean isDated() {
        return true;
    }



}
