import java.util.ArrayList;

public class Tasklist {
    private Task[] tasklist;
    private int currCount;
    private static String breakline = "____________________________________________________________";

    Tasklist() {
        this.tasklist = new Task[100];
        this.currCount = 0;
    }

    public void add(Task task) {
        this.tasklist[currCount] = task;
        this.currCount += 1;
        String addMsg = String.format("added: %s", task.toString());
        System.out.println(addMsg);
        System.out.println(breakline);
    }

    public void list() {
        for(int i=0;i < currCount; i++) {
            if(!tasklist[i].isDone()) {
                System.out.printf("%d.[ ] %s\n", i + 1, tasklist[i]);
            } else {
                System.out.printf("%d.[X] %s\n", i + 1, tasklist[i]);
            }
        }
        System.out.println(breakline);
    }

    public Task getTask(int idx) {
        return tasklist[idx];
    }


}
