import java.util.ArrayList;

public class Tasklist {
    private ArrayList<Task> tasklist;
    // private Task[] tasklist;
    private static int currCount;
    private static String breakline = "____________________________________________________________";

    Tasklist() {
        this.tasklist = new ArrayList<>();
        this.currCount = 0;
    }

    public void add(Task task) {

        try {
            if(task == null) {
                throw new DukeException("Task has not been added successfully.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(breakline);
            return;
        }

        this.tasklist.add(task);
        this.currCount += 1;
        System.out.println("Got it. I've added this task:");
        String addMsg = String.format("%s", task.toString());
        String counterMsg = String.format("Now you have %d tasks in the list.", currCount);
        System.out.println(addMsg);
        System.out.println(counterMsg);
        System.out.println(breakline);
    }

    public void list() {

        try {
            if(this.tasklist.size() == 0) {
                throw new DukeException("List is empty :(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(breakline);
            return;
        }

        System.out.println("Here are the tasks in your list:");
        for(int i=0;i < currCount; i++) {
            if(!tasklist.get(i).isDone()) {
                System.out.printf("%d. %s\n", i + 1, tasklist.get(i).toString());
            } else {
                System.out.printf("%d. %s\n", i + 1, tasklist.get(i).toString());
            }
        }
        System.out.println(breakline);
    }

    public void delete(int idx) {
        Task removedTask;
        try {
            removedTask = this.tasklist.remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Item %d cannot be found.", idx);
            System.out.println(breakline);
            return;
        }

        String removeMsg = String.format("Noted. I've removed this task:\n%s",removedTask.toString());
        System.out.println(removeMsg);
        currCount = currCount - 1;
        System.out.printf("Now you have %d task(s) in the list.\n", currCount);
        System.out.println(breakline);
    }

    public Task getTask(int idx) {
        return tasklist.get(idx);
    }


}
