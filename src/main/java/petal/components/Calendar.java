package petal.components;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import petal.exception.InvalidInputException;
import petal.task.Task;
import petal.task.Timeable;

/**
 * The Calendar class encapsulates a calender, where tasks
 * have dates, and can be retrieved and added by date
 */
public class Calendar {

    private HashMap<LocalDate, ArrayList<Timeable>> dateAndTimeable;

    /**
     * Constructor for the Calender class
     */
    public Calendar() {
        dateAndTimeable = new HashMap<>();
    }

    /**
     * Adds a Timeable object to the calendar
     *
     * @param timeable The Timeable to be added
     */
    public void addToCalendar(Timeable timeable) {
        LocalDate date = timeable.getDate();
        Optional<ArrayList<Timeable>> current = Optional.ofNullable(dateAndTimeable.get(date));
        if (current.isPresent()) {
            current.get().add(timeable);
        } else {
            ArrayList<Timeable> firstList = new ArrayList<>();
            firstList.add(timeable);
            dateAndTimeable.put(date, firstList);
        }
    }

    /**
     * Returns the String representation of all the Timeables on that date
     *
     * @param date date to be used
     */
    public String showTasksOnDate(LocalDate date) throws InvalidInputException {
        int count = 1;
        Optional<ArrayList<Timeable>> current = Optional.ofNullable(dateAndTimeable.get(date));
        if (current.isPresent()) {
            StringBuilder result = new StringBuilder("Here are the tasks on this date: ");
            for (Timeable d: current.get()) {
                result.append("\n").append(count++).append(". ").append(d);
            }
            return result.toString();
        } else {
            return "No tasks on this date!";
        }
    }

    /**
     * Updates the calendar
     *
     * @param newList The new arraylist of Tasks, which will be used to update the calendar
     */
    public void updateCalendar(List<Task> newList) {
        dateAndTimeable = new HashMap<>();
        for (Task t: newList) {
            if (t.isTimeable()) {
                addToCalendar((Timeable) t);
            }
        }
    }
}
