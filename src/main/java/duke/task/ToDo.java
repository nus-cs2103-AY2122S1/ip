package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, String reminderTime) {
        super(description, reminderTime);
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += this.hasReminder()
                ? ", reminds at " + this.getReminderTime().format(getOutputDateTimeFormatter())
                : "";

        return result;
    }

    @Override
    public String getIcon() {
        return "T";
    }

    @Override
    public String getTaskTime() {
        return "";
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
