/**
 * Represents the event that has time. .
 *
 * @author QIN GUORUI
 */
public class Event extends Task{
        /** The time to do the event. */
        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
}
