package me.yukun99.ip.tasks;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public void updateDate(String date) throws HelpBotInvalidTaskTypeException {
        throw new HelpBotInvalidTaskTypeException(Type.TODO);
    }

    @Override
    public DateTimePair getDate() throws HelpBotInvalidTaskTypeException {
        throw new HelpBotInvalidTaskTypeException(Type.TODO);
    }

    @Override
    public String saveString() {
        String save = "T:";
        if (isDone) {
            save += "T:";
        } else {
            save += "F:";
        }
        save += name;
        return save + super.saveString();
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
