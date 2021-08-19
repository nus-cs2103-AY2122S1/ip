public class ToDo extends Task {

    public ToDo(String description) {
        super(getDescription(description));
    }

    private static String getDescription(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your todo needs a description.\n");
        }
        return description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
