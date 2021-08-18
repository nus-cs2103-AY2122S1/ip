import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> list;

    public TodoList() {
        this.list = new ArrayList<Task>();
    }

    public String getList() {
        ArrayList<Task> currentList = this.list;
        StringBuilder output = new StringBuilder("Wahseh, these are all the tasks you haven't do ley! \n      ");
        int i = 1;
        for (Task task : currentList) {
//            String checkBox = " [" + task.getStatus() + "] ";
//            output.append(i).append(". ").append(checkBox).append(task.name).append("\n      ");
            output.append(i).append(". ").append(task.toString()).append("\n      ");
            i += 1;
        }

        return output.toString();
    }

    public String insertTask(String input) {
        String first = input.split(" ",2)[0];
        String second = input.split(" ",2)[1];

        Task task;
        if (first.equalsIgnoreCase("deadline")) {
            if (second.contains("/by")) {
                String name = second.split("/by", 2)[0];
                String date = second.split("/by", 2)[1];
                task = new Deadline(name, date);
            } else {
                task = new Deadline(second, "");
            }
            task.type = 'D';

        } else if (first.equalsIgnoreCase("todo")) {
            task = new Todo(second);
            task.type = 'T';

        } else {
            if (second.contains("/at")) {
                String name = second.split("/at", 2)[0];
                String date = second.split("/at", 2)[1];
                task = new Event(name, date);
            } else {
                task = new Event(second, "");
            }
            task.type = 'E';
        }

        list.add(task);
        return "OK uncle added a task for you liao.\n" + "      " + task.toString()
                + "\n      You now have " + list.size() + " tasks remaining.";
    }

    public String completeTask(int index) {
        if (index < 0 || index > list.size()) {
            return "Aiyo, out of bounds leh!";
        } else {
            return list.get(index).completeTask();
        }
    }

}
