package ligma.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * This class represents a Deadline Task.
 */
public class Deadline extends Task {
    private LocalDate time;

    private Deadline(String details, LocalDate time, String meta) {
        super(details, meta);
        this.time = time;
    }

    private Deadline(String details, LocalDate time, boolean isDone, String meta) {
        super(details, isDone, meta);
        this.time = time;
    }

    /**
     * Factory method that creates a Deadline object.
     *
     * @param desc                      the description of the deadline
     * @return                          deadline created
     * @throws InputMismatchException   if format of deadline description is wrong
     * @throws DateTimeParseException   if format of time is wrong
     */
    public static Deadline createDeadline(String desc)
            throws InputMismatchException, DateTimeParseException {
        if (desc.contains("/by")) {
            String[] arr = desc.split("/by");
            LocalDate time = LocalDate.parse(arr[1].trim());
            return new Deadline(arr[0].trim(), time, desc);
        } else {
            throw new InputMismatchException("Time must be stipulated for deadlines using '/by'.");
        }
    }

    /**
     * Factory method that creates a Deadline object.
     *
     * @param desc                      the description of the deadline
     * @param isDone                    whether task has been completed
     * @return                          deadline created
     * @throws InputMismatchException   if format of deadline description is wrong
     * @throws DateTimeParseException   if format of time is wrong
     */
    public static Deadline createDeadline(String desc, boolean isDone) throws InputMismatchException {
        if (desc.contains("/by")) {
            String[] arr = desc.split("/by");
            LocalDate time = LocalDate.parse(arr[1].trim());
            return new Deadline(arr[0].trim(), time, isDone, desc);
        } else {
            throw new InputMismatchException("Time must be stipulated for deadlines using '/by'.");
        }
    }

    @Override
    public String getFullMeta() {
        return "D" + super.getFullMeta();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            return super.equals(obj) && this.time.equals(((Deadline) obj).time);
        }
        return false;
    }
}
