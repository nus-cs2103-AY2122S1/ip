import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Session.output("I Understand. I Have Added:\n   " + task.toString() + "\nYou Have " + tasks.size() + " Tasks.");
    }

    public void listTasks() {
        String taskListString = "Here Are The Tasks In Your List:\n";
        for(int i = 0; i < tasks.size(); i++) {
            taskListString += (i+1) + "." + tasks.get(i).toString() + "\n";
        }
        taskListString += "I Wish You Luck With Your Tasks And Doings.";
        Session.output(taskListString);
    }

    public void markAsDone(int index) {
        String doneString = "Good Work. I Mark This Task As Done:\n";
        Task doneTask = tasks.get(index - 1);
        doneTask.markAsDone();
        doneString += "   " + doneTask.toString();
        Session.output(doneString);
    }

    public void removeTask(int index) {
        try {
            Task deadTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            Session.output("   I Have Removed This Task:\n   " + deadTask.toString());
        } catch (IndexOutOfBoundsException e) {
            Session.output("   You Cannot Delete A Task That Does Not Exist. Do Better.");
        }
    }

}
