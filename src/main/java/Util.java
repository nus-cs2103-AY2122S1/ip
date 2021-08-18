import tasks.Task;

public class Util {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    //Print confirmation after the task is added
    public static void taskAddConfirmation(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else if (numTasks > 1) {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }
}
