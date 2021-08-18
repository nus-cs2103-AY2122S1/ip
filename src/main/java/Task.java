public class Task {
    private String description;
    private boolean isDone;

    /**
     * @param description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        System.out.println(Pib.DIVIDER + "Added: " + description + "\n" + Pib.DIVIDER);
    }

    public String displayTask() {
        return ".[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        if (this.isDone) {
            System.out.println(Pib.DIVIDER + "Item is already marked as done!\n" + Pib.DIVIDER);
            return;
        }
        this.isDone = true;
        System.out.println(Pib.DIVIDER
                + "Nice! I've marked this task as done:\n"
                + "[X] " + description + "\n"
                + Pib.DIVIDER);
    }

    /**
     * @return
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}

