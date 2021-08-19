public class DukeMissingTaskDescription extends DukeIncorrectInputs {
    private static final String prefix = "⚠️ OOPS!! The description of a";
    private static final String suffix = " cannot be empty. ⚠️";
    public DukeMissingTaskDescription(ToDo todo, Throwable err) {
        super("\t" + DukeMissingTaskDescription.prefix + " todo" +
                DukeMissingTaskDescription.suffix, err);
    }

    public DukeMissingTaskDescription(Deadline deadline, Throwable err) {
        super("\t" + DukeMissingTaskDescription.prefix + " deadline" +
                DukeMissingTaskDescription.suffix, err);
    }

    public DukeMissingTaskDescription(Event event, Throwable err) {
        super("\t" + DukeMissingTaskDescription.prefix + "n event" +
                DukeMissingTaskDescription.suffix, err);
    }
}
