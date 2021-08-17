public class Deadline extends Item {
    private String time;

    public Deadline(String[] strings) {
        String line = String.join(" ", strings);
        int idx = line.indexOf("/by");
        this.setName(line.substring(9, idx));
        this.time = line.substring(idx + 4, line.length());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
