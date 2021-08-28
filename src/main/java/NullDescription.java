public class NullDescription extends DukeException {

    private String type;

    public NullDescription(String type) {
        this.type = type;
    }
    @Override
    public String getMessage() {
        return "\t☹ OOPS!!! The description of a " + type + " cannot be empty.";
    }
}
