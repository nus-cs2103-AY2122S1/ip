package duke.task;

import java.time.format.DateTimeFormatter;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String reminderTime) {
        super(description, reminderTime);
    }

    @Override
    public String toString() {
        String result = "[T]" + super.toString();
        result += this.hasReminder()
                ? ", remind at" + this.getReminderTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                : "";

        return result;
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return toDo.toString().equals(this.toString());
        } else {
            return false;
        }
    }
}
