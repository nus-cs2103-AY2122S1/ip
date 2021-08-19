import java.util.ArrayList;

public class TaskDialog extends Dialog {
    // This class is a child of dialog class which allow the user to interact with the task
    private final ArrayList<Task> tasks;

    public void addTask(Task task) {
        tasks.add(task);
        Dialog addDialog = Dialog.generate("add" + task);
        addDialog.add("Got it. I've added this task:");
        addDialog.add("  " + task);
        addDialog.add("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(addDialog);
    }

    public Task getTaskByIndex(int index) throws IndexOutOfBoundsException{
        return tasks.get(index);
    }

    private TaskDialog(ArrayList<String> sentences, ArrayList<Task> tasks) {
        super(sentences);
        this.tasks = tasks;
    }

    public static Dialog generate(String id) {
        if (archive.containsKey(id)) {
            return archive.get(id);
        } else {
            final Dialog newDialog = new TaskDialog(new ArrayList<>(), new ArrayList<>());
            archive.put(id, newDialog);
            return newDialog;
        }
    }

    public TaskDialog asTaskDialog() {
        return this;
    }

    public void markTaskAsDone(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > this.tasks.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Task task = getTaskByIndex(index);
            task.markAsDone();
            Dialog markAsDoneDialog = Dialog.generate("markAsDone" + task);
            markAsDoneDialog.add("Nice! I've marked this task as done:");
            markAsDoneDialog.add("  " + task);
            System.out.println(markAsDoneDialog);
        }
    }



    @Override
    public String toString() {
        String dialogs = this.sentences.stream().reduce("    ", (s1, s2) -> s1 + s2 + "\n    ");
        StringBuilder tasksDialog = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tasksDialog.append("    ").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return "    ____________________________________________________________\n" +
                dialogs +
                "Here are the tasks in your list:\n"+
                tasksDialog +
                "    ____________________________________________________________";
    }
}
