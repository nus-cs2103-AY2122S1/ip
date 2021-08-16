public class Event extends Item {

    public Event(String[] strings) {
        String line = String.join(" ", strings);
        String substring = line.substring(line.indexOf("/at"), line.length());
        System.out.println(substring);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
