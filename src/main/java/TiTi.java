import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.*;

public class TiTi {

    private static String STARTER_NORMAL =   "   (=^ ･ｪ･^=) < ";
    private static String STARTER_BUFFER =   "                ";
    private static String STARTER_SLEEPY =   "   (=^ ‐ｪ‐^=) < ";
    private static String STARTER_CONFUSED = "   (=^ ･x･^=)? < ";
    private static String STARTER_HAPPY =    "   (=^ ･ω･^=)❀ < ";


    public static void main(String[] args) {
        SavedHistory savedHistory = new SavedHistory();

        ArrayList<Task> list = savedHistory.readHistory();
        Scanner sc = new Scanner(System.in);


        // Welcome text
        System.out.println(STARTER_NORMAL + "Hello! I'm TiTi~ ");
        System.out.println(STARTER_BUFFER + "What would you like to do nya? ");

        String input = sc.nextLine();
        while (true) {
            String cue = input.split(" ", 2)[0];

            // exit cue
            if (cue.equals("bye")) {
                System.out.println(STARTER_SLEEPY + "Already done? Come back again soon nya~");
                savedHistory.saveHistory(list);
                break;
            }

            // display list
            if (cue.equals("list")) {
                int numberOfTasks = list.size();
                // no tasks
                if (numberOfTasks == 0) {
                    System.out.println(STARTER_NORMAL + "Looks like you have no tasks nya~");
                    input = sc.nextLine();
                    continue;
                }
                // print task list
                System.out.println(STARTER_NORMAL + "Have you competed these tasks nya?");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(STARTER_BUFFER + (i + 1) + ". " + list.get(i));
                }
                input = sc.nextLine();
                continue;
            }

            // mark complete task
            if (cue.equals("done")) {
                int taskNumber = Integer.parseInt("" + input.charAt(5));
                int numberOfTasks = list.size();
                if (taskNumber > numberOfTasks) {
                    System.out.println(STARTER_CONFUSED + "Nya?... I can't find the task...");
                } else {
                    Task task = list.get(taskNumber - 1);
                    task.complete();
                    System.out.println(STARTER_HAPPY + "Nya! You've worked hard haven't you!");
                    System.out.println(STARTER_BUFFER + "I'll mark this task as done:");
                    System.out.println(STARTER_BUFFER + "  " + task.toString());
                }
                input = sc.nextLine();
                continue;
            }

            // delete task
            if (cue.equals("delete")) {
                int taskNumber = Integer.parseInt("" + input.charAt(7));
                int numberOfTasks = list.size();
                if (taskNumber > numberOfTasks) {
                    System.out.println(STARTER_CONFUSED + "Nya?... I can't find the task...");
                } else {
                    Task task = list.get(taskNumber - 1);
                    System.out.println(STARTER_NORMAL + "Nya! This task shall be removed:");
                    System.out.println(STARTER_BUFFER + "  " + task.toString());
                    list.remove(taskNumber - 1);

                    System.out.println(STARTER_BUFFER + countTasks(list));
                }
                input = sc.nextLine();
                continue;
            }


            // handle missing description
            if ((cue.equals("todo") || cue.equals("deadline") || cue.equals("event"))
                    && input.split(" ", 2).length == 1) {
                System.out.println(STARTER_CONFUSED + "Nya? Give me a description of the task.");
                input = sc.nextLine();
                continue;
            }

            // add todo to list
            if (cue.equals("todo")) {
                String task = input.split(" ", 2)[1];
                ToDo todo = new ToDo(task);
                list.add(todo);
                System.out.println(STARTER_NORMAL + "A new task? I'll add this to the list:");
                System.out.println(STARTER_BUFFER + "  " + todo.toString());
                System.out.println(STARTER_BUFFER + countTasks(list));
                System.out.println(STARTER_BUFFER + "Don't forget to complete it nya~");
                input = sc.nextLine();
                continue;
            }

            // add deadline to list
            if (cue.equals("deadline")) {
                String[] arr = input.split(" ", 2)[1].split(" /by ", 2);
                String task = arr[0];
                String time = arr[1];

                Deadline deadline = new Deadline(task, time);
                list.add(deadline);
                System.out.println(STARTER_NORMAL + "A new deadline? Sounds tough nya...");
                System.out.println(STARTER_BUFFER + "  " + deadline.toString());
                System.out.println(STARTER_BUFFER + countTasks(list));
                System.out.println(STARTER_BUFFER + "Gambatte nya~");
                input = sc.nextLine();
                continue;
            }

            // add event to list
            if (cue.equals("event")) {
                String[] arr = input.split(" ", 2)[1].split(" /at ", 2);
                String task = arr[0];
                String time = arr[1];

                Event event = new Event(task, time);
                list.add(event);
                System.out.println(STARTER_NORMAL + "A new event? Let me record it down:");
                System.out.println(STARTER_BUFFER + "  " + event.toString());
                System.out.println(STARTER_BUFFER + countTasks(list));
                System.out.println(STARTER_BUFFER + "I'll be waiting nya~");
                input = sc.nextLine();
                continue;
            }

            System.out.println(STARTER_CONFUSED + "Nya?... I can't find what you are looking for...");
            input = sc.nextLine();
        }
    }

    static String countTasks(ArrayList<Task> list) {
        if (list.size() == 1) {
            return "We now have " + list.size() + " task on our list.";
        } else {
            return "We now have " + list.size() + " tasks on our list.";
        }
    }



}
