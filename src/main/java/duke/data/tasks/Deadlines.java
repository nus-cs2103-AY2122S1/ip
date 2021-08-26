package duke.data.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadlines extends Task {
    private final LocalDate date;

    public Deadlines(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Deadlines(boolean completed, String name, String deadline) {
        super(completed, name);
        this.date = LocalDate.parse(deadline);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getSaveData() {
        if (this.isCompleted()) {
            return String.format("D~1~%s~%s", this.getName(), this.date);
        } else {
            return String.format("D~0~%s~%s", this.getName(), this.date);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.date.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deadlines)) return false;

        Deadlines deadline = (Deadlines) o;

        return this.getName().equals(deadline.getName()) &&
                this.isCompleted() == deadline.isCompleted() &&
                this.date.equals(deadline.getDate());
    }
}
