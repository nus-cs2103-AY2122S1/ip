public class DukeIncorrectTaskDescription extends DukeIncorrectInputs {
    private static final String prefix = "To log a";
    private static final String suffix = ", you have to log the \n\tdescription with the date like this:\n";

    public DukeIncorrectTaskDescription(Deadline deadline, Throwable err) {
        super("\t" + DukeIncorrectTaskDescription.prefix + " deadline" +
                DukeIncorrectTaskDescription.suffix + "\t\t{deadline description} /by {date}", err);
    }

    public DukeIncorrectTaskDescription(Event event, Throwable err) {
        super("\t" + DukeIncorrectTaskDescription.prefix + "n event" +
                DukeIncorrectTaskDescription.suffix + "\t\t{event description} /at {date}", err);
    }
}
