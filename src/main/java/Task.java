public class Task {
    private String name;
    private static String breakline = "____________________________________________________________";

    Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
