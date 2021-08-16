public class Task {
    String descriptions;

    Task(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return this.descriptions;
    }
}
