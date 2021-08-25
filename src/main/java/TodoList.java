import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> list;

    public TodoList() {
        this.list = new ArrayList<Task>();
    }

    public String getList() {
        ArrayList<Task> currentList = this.list;
        StringBuilder output = new StringBuilder("Wahseh, these are all the tasks you haven't do ley!\n      ");
        int i = 1;
        for (Task task : currentList) {
            if (i != 1) {
                output.append("      ");
            }
            output.append(i).append(". ").append(task.toString()).append("\n");
            i += 1;
        }

        return output.toString();
    }

    public String insertTask(String input) throws NoDescriptionException, WrongInputException {
        String first = input.split(" ",2)[0];
        if (first.equals(input)) {
            String message = "Oi, description of your " + first + " cannot be empty lah!";
            throw new NoDescriptionException(message);
        }

        String second = input.split(" ",2)[1];
        Task task =  new Task("");
        if (first.equalsIgnoreCase("deadline")) {
            if (second.contains("/by")) {
                    String name = second.split("/by", 2)[0];
                    String date = second.split("/by", 2)[1];
                    task = Deadline.createDeadline(name, date);
                    task.type = 'D';
            } else {
                    task = Deadline.createDeadline(second, "");
                    task.type = 'D';
            }

        } else if (first.equalsIgnoreCase("todo")) {
            task = new Todo(second);
            task.type = 'T';

        } else {
            if (second.contains("/at")) {
                    String name = second.split("/at", 2)[0];
                    String date = second.split("/at", 2)[1];
                    task = Event.createEvent(name, date);
                    task.type = 'E';
            } else {
                    task = Event.createEvent(second, "");
                    task.type = 'E';
            }
        }

        list.add(task);
        return "OK uncle added a task for you liao.\n" + "      " + task.toString()
                + "\n      You now have " + list.size() + " tasks remaining.";
    }

    public String completeTask(int index) throws IndexNotInListException {
        if (index < 1 || index > list.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        } else {
            return list.get(index-1).completeTask();
        }
    }

    public String deleteTask(int index) throws IndexNotInListException {
        if (index < 1 || index > list.size()) {
            throw new IndexNotInListException("Haiyo, you sure there is a task " + index + " anot...");
        } else {
            Task task = list.remove(index-1);
            return "OK, uncle removed a task for you liao.\n" + "      " + task.toString()
                    + "\n      You now have " + list.size() + " tasks remaining.";
        }
    }

}
