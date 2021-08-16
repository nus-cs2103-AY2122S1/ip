public class Task {
    private String name;
    private static String breakline = "____________________________________________________________";
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setStatus(boolean status) {
        this.done = status;
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("\t [X] %s\n", this.name);
        System.out.println(breakline);
    }

    @Override
    public String toString() {
        return this.name;
    }


}
