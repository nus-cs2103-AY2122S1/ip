package bob;

import bob.task.Task;

public class Ui {
    public Ui() {}

    public void showStart() {
        System.out.println("I'm waking up... please wait...\n");
    }

    public void showDirectoryLoadingError() {
        System.out.println("You don't have the data directory and Bob text file yet." +
                " I'll make them for you now, you're welcome! :D\n");
    }

    public void showFileLoadingError() {
        System.out.println("You don't have the Bob.Bob text file yet. I'll make one for you now, you're welcome! :D\n");
    }

    public void showGreeting() {
        System.out.println("OKKAYYY finally awake!");
        System.out.println("Howwwwwwdy! I'm Bob :D");
        System.out.println("What do you want?\n");
    }

    public void showList(TaskList tasks) {
        String result = "Here's your tasks! Wow I'm so helpful!\n";
        result = result + tasks.getList();
        System.out.println(result);
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        tasks.addTask(task);
        System.out.println("Okay okay I've added the task:\n" + task.printTask() + "\n"
                + "Yay " + tasks.getNoOfTasks() + " tasks!\n");
    }

    public void showIndexCompleted(int index, TaskList tasks) {
        System.out.println("Wow you finally did something productive!\n" + tasks.markIndexCompleted(index) + "\n");
    }

    public void showIndexDeleted(int index, TaskList tasks) {
        System.out.println("Okay task yeeted away :D\n" + tasks.deleteIndex(index) + "\n"
                + "Yay " + tasks.getNoOfTasks() + " tasks!\n");
    }

    public void showInvalidInputException() {
        System.out.println("That doesn't make any sense! >:(\n");
    }

    public void showNoTaskException() {
        System.out.println("You didn't specify your task! >:(\n");
    }

    public void showNoDeadlineException() {
        System.out.println("When is the deadline? >:(\n");
    }

    public void showNoEventTimingException() {
        System.out.println("When is the event? >:(\n");
    }

    public void showOutOfBoundsException() {
        System.out.println("Huh what task is that :/\n");
    }

    public void showGoodbye() {
        System.out.println("Bye! Shoo!");
    }
}
