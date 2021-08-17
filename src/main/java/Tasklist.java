public class Tasklist {
    private Task[] tasklist;
    private static int currCount;
    private static String breakline = "____________________________________________________________";

    Tasklist() {
        this.tasklist = new Task[100];
        this.currCount = 0;
    }

    public void add(Task task) {
        this.tasklist[currCount] = task;
        this.currCount += 1;
        System.out.println("Got it. I've added this task:");
        String addMsg = String.format("%s", task.toString());
        String counterMsg = String.format("Now you have %d tasks in the list.", currCount);
        System.out.println(addMsg);
        System.out.println(counterMsg);
        System.out.println(breakline);
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i < currCount; i++) {
            if(!tasklist[i].isDone()) {
                System.out.printf("%d. %s\n", i + 1, tasklist[i].toString());
            } else {
                System.out.printf("%d. %s\n", i + 1, tasklist[i].toString());
            }
        }
        System.out.println(breakline);
    }

    public Task getTask(int idx) {
        return tasklist[idx];
    }


}
