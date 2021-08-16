/**
 * Event class represents tasks that start at a specific time and ends at
 * a specific time e.g., team project meeting on 2/10/2019 2-4pm.
 *
 * @author Chng Zi Hao
 */
public class Event extends Task{
    private String eventTimeframe;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     * @param eventTimeframe The timeframe of the event.
     */
    public Event(String description, String eventTimeframe) {
        super(description);
        this.eventTimeframe= eventTimeframe;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.eventTimeframe + ")";
    }

}
