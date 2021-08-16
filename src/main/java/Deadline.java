public class Deadline extends Item {

    public Deadline(String[] strings) {
        super(strings);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
