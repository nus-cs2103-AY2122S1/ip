public class Echo extends Task {
    private static final String END_COMMAND = "bye";

    public Echo(String desc) {
        super(desc, true); 
    }

    boolean isLastTask() {
        return this.description.equals(END_COMMAND);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
