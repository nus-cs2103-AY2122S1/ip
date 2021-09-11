package me.yukun99.ip.tasks;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;

public class Event extends Task {
    private DateTimePair pair;

    /**
     * Constructor for an Event instance.
     *
     * @param name Name of the Event.
     * @param date Date the event occurs at.
     */
    public Event(String name, String date) throws HelpBotDateTimeFormatException {
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
        return pair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return super.equals(o) && pair.equals(event.pair);
    }

    @Override
    public String saveString() {
        String save = "E:";
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
        return " [E]" + super.toString() + " (at: " + pair.toString() + ")";
    }
}
