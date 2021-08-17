public class ToDo extends Item {

    public ToDo(String[] strings) {
        String line = String.join(" ", strings);
        this.setName(line.substring(5, line.length()));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
