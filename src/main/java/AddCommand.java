public abstract class AddCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    public String respond(Task task, int numOfTasks) {
        String response = "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + numOfTasks
                + " tasks in the list.";
        return response;
    }
}
