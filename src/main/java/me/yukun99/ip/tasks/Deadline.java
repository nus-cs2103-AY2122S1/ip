package me.yukun99.ip.tasks;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;

public class Deadline extends Task {
    private DateTimePair pair;

    /**
     * Constructor for an Deadline instance.
     *
     * @param name Name of the Deadline.
     * @param date Date the deadline has to be completed by.
     */
    public Deadline(String name, String date) throws HelpBotDateTimeFormatException {
        super(name);
        setDate(date, true);
    }

    @Override
    public void updateDate(String date) throws HelpBotDateTimeFormatException {
        setDate(date, false);
    }

    private void setDate(String strDate, boolean doCreate) throws HelpBotDateTimeFormatException {
        if (doCreate) {
            pair = DateTimePair.parse(strDate);
        } else {
            pair.update(DateTimePair.parse(strDate));
        }
    }

    @Override
    public DateTimePair getDate() {
        return this.pair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return super.equals(o) && pair.equals(deadline.pair);
    }

    @Override
    public String saveString() {
        String save = "D:";
        if (isDone) {
            save += "T:";
        } else {
            save += "F:";
        }
        save += name + ":" + pair.saveString();
        return save + super.saveString();
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + pair.toString() + ")";
    }
}
