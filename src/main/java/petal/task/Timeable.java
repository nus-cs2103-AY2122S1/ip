package petal.task;

import java.time.LocalDate;

/**
 * The Timeable interface represents Tasks that have a date/time components,
 * mainly the Deadline/Event tasks
 */
public interface Timeable {

    /**
     * Returns the date of that Timeable object
     *
     * @return LocalDate object corresponding to the date of that object
     */
    public LocalDate getDate();

}
