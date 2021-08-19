import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class MyList {

    private final List<Task> items;
    HashSet<String> listedItems;

    MyList() {
        this.items = new ArrayList<Task>(100);
        this.listedItems = new HashSet<String>();
    }

    protected String addItem(String command) {
        String[] commandTokens = generateTokens(command, " ");
        String[] detailTokens;
        String taskName;
        Task task = null;
        switch (commandTokens[0]) {
            case ("todo"):
                taskName = command.substring(5).trim();
                if (this.listedItems.contains(taskName)) {
                    return "task already in list!";
                } else {
                    task = new ToDo(taskName);
                    this.listedItems.add(taskName);
                    this.items.add(task);
                    break;
                }
            case ("event"):
                detailTokens = generateTokens(command.substring(6), "/");
                taskName = detailTokens[0].trim();
                if (this.listedItems.contains(taskName)) {
                    return "task already in list!";
                } else {
                    task = new Event(taskName, detailTokens[1].trim());
                    this.listedItems.add(taskName);
                    this.items.add(task);
                    break;
                }
            case ("deadline"):
                detailTokens = generateTokens(command.substring(9), "/");
                taskName = detailTokens[0].trim();
                if (this.listedItems.contains(taskName)) {
                    return "task already in list!";
                } else {
                    task = new Deadline(taskName, detailTokens[1].trim());
                    this.listedItems.add(taskName);
                    this.items.add(task);
                    break;
                }
            default:
                return "invalid command!!";
        }
        return String.format("New task added to list:\n%s", task.toString()); //TODO
    }

    private String[] generateTokens(String taskDetails, String delimiter) {
        return taskDetails.split(delimiter);
    }

    protected String markAsCompleted(String taskName) {
        //find the task to delete
        int i = 0;
        boolean found = false;
        while (i < this.items.size()) {
            System.out.println(this.items.get(i).getTaskName());
            if (this.items.get(i).getTaskName().equals(taskName)) {
                Task completedTask = this.items.get(i);
                this.items.remove(i);
                this.items.add(i, completedTask.markAsCompleted());
                return "Task marked as completed: \n" + this.items.get(i).toString();
            } else {
                i++;
            }
        }
        return "task is not in list!!";
    }

    protected String getAllItems() {
        StringBuilder sb = new StringBuilder();
        if (this.items.size() == 0) {
            sb.append("There are no items in your list!");
            return sb.toString();
        }
        sb.append("Your list contains: \n");
        for (int i = 0; i < this.items.size(); i++) {
            String itemNum = Integer.toString(i + 1) + ". ";
            sb.append(itemNum);
            sb.append(this.items.get(i).toString());
            if (i < this.items.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
