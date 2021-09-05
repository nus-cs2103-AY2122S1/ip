package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.place.Place;

/**
 * Task class which contains description of task and whether task is done.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected ArrayList<Place> places;

    /**
     * Constructor for the Task class.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.places = new ArrayList<>();
        this.description = description;
        isDone = false;
    }

    /**
     * Adds a place to the task object.
     *
     * @param place the place to be associated with the task.
     */
    public void addPlace(Place place) {
        assert places != null : "Places is not initialised";
        places.add(place);
    }

    /**
     * Gets the icon representation of duke.task.Task depending if duke.task.Task is done.
     *
     * @return X if done, empty otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to be done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return string representation with status icon and description of task with the places.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getStatusIcon()).append("] ").append(description);
        if (places.size() > 0) {
            sb.append(", Places: ").append(getPlacesRepresentation());
        }
        return sb.toString();
    }

    /**
     * Gets the string representation of the list of places.
     *
     * @return string representation of list of places.
     */
    protected String getPlacesRepresentation() {
        return places.stream().map(Place::toString).collect(Collectors.joining(", "));
    }

    /**
     * Returns the string representation which is used to store the tasks.
     *
     * @return string representation used for storing task.
     */
    public String toDataFormat() {
        return String.format("  | %s | %s | %s", isDone ? "1" : "0", description, getPlacesRepresentation());
    }

    /**
     * Implements the compareTo function as required to be comparable.
     *
     * @param o the other task
     * @return lexicographical ordering of the description.
     */
    @Override
    public int compareTo(Task o) {
        return this.description.compareTo(o.description);
    }
}
