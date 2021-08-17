public class Task {
    private String name;
    private static String breakline = "____________________________________________________________";
    private boolean done;

    Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setStatus(boolean status) {
        this.done = status;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(breakline);
    }

    @Override
    public String toString() {
        return this.name;
    }


}
