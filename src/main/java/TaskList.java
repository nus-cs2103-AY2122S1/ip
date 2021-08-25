import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task, boolean logOutput) {
        tasks.add(task);
        Session.output("I Understand. I Have Added:\n   " + task.toString() + "\nYou Have " + tasks.size() + " Tasks.");
    }

    public void listTasks(boolean logOutput) {
        String taskListString = "Here Are The Tasks In Your List:\n";
        for(int i = 0; i < tasks.size(); i++) {
            taskListString += (i+1) + "." + tasks.get(i).toString() + "\n";
        }
        taskListString += "I Wish You Luck With Your Tasks And Doings.";
        if (logOutput) Session.output(taskListString);
    }

    public void markAsDone(int index, boolean logOutput) {
        String doneString = "Good Work. I Mark This Task As Done:\n";
        Task doneTask = tasks.get(index - 1);
        doneTask.markAsDone();
        doneString += "   " + doneTask.toString();
        if (logOutput) Session.output(doneString);
    }

    public void removeTask(int index, boolean logOutput) {
        try {
            Task deadTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            if (logOutput) Session.output("   I Have Removed This Task:\n   " + deadTask.toString());
        } catch (IndexOutOfBoundsException e) {
            if (logOutput) Session.output("   You Cannot Delete A Task That Does Not Exist. Do Better.");
        }
    }

    public String saveTasks() {
        String outputString = "";
        for(int i = 0; i < tasks.size(); i++) {
            outputString += (i + 1) + " TTT " + tasks.get(i).toString() + "\n";
        }
        return outputString;
    }

    public void findTasks(String keyword) {
        String taskListString = "Let Me See If I Can Search What You Seek:\n";
        for(int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                taskListString += (i+1) + "." + tasks.get(i).toString() + "\n";
            }
        }
        taskListString += "What I Found, I Present Above.";
        Session.output(taskListString);
    }

}
