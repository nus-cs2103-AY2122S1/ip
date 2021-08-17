/**
 * Description:
 * Events: tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm.
 *
 * @author Leong Hong Fai
 */

public class Event extends Task {
    private String name;
    private String date;

    public Event(String name, String date) {
        super(name);
        this.name = name;
        this.date = date;
    }

    /**
     * Simple string representation of Event.
     *
     * @return A string consisting of the information of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }
}