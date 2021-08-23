package petal.task;

import java.time.LocalDate;

/**
 * The Timeable interface represents Tasks that have a date/time components,
 * mainly the Deadline/Event tasks
 */
public interface Timeable {

    /**
     * Method that returns the date of that object
     *
     * @return LocalDate object correpsonding to the date of that object
     */
    public LocalDate getDate();
}
