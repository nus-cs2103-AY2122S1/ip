package duke.tasks;

import java.time.LocalDate;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, "[T]", false);
    }

    public ToDo(String description, boolean status) {
        super(description, "[T]", status);
    }

    @Override
    public LocalDate getDueDate() {
        return LocalDate.MAX;
    }

    @Override
    public String getFormattedData() {
        String formattedStatus = super.isDone() ? "1|" : "0|";
        return "T|" + formattedStatus + super.getDescription();
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other instanceof ToDo) {
            return this.getDescription().equals(((ToDo) other).getDescription());
        } else {
            return false;
        }

    }
}
