/**
 * Description:
 * Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm.
 *
 * @author Leong Hong Fai
 */

public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    /**
     * Simple string representation of Deadline.
     *
     * @return A string consisting of the information of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }
}