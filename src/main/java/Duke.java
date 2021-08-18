import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        Task currentTask = null;
        while (true) {
            String input = sc.nextLine();
            boolean isLastCharDigit =  Character.isDigit(input.charAt(input.length() - 1));
            if (input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
                }
            } else if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (input.contains("done") && isLastCharDigit) {
                boolean isNegative = input.charAt(input.length() - 1) == '-';
                char itemIndex = input.charAt(input.length() - 1);
                int i = Integer.parseInt(String.valueOf(itemIndex)) - 1;
                if (i < tasks.size() && !isNegative) {
                    tasks.get(i).markAsDone();
                    System.out.println("\tNice! I've marked this task as done:\n\t\t" +
                            tasks.get(i).toString());
                } else {
                    System.out.println("\tNo task found or invalid input!");
                }

            } else if (input.contains("done") && !isLastCharDigit) {
                System.out.println("\tInvalid input!");
            }
            else {
                if (input.contains("todo")) {
                    String test = input.replaceAll("\\s+","");
                    if (test.length() == 4) {
                        System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
                        continue;
                    } else {
                        int firstIndexAfterDeadline = 5;
                        currentTask = new Todo(input.substring(firstIndexAfterDeadline));
                        tasks.add(currentTask);
                    }
                } else if (input.contains("deadline")) {
                    String test = input.replaceAll("\\s+","");
                    if (test.length() == 8) {
                        System.out.println("\tOOPS!!! The description of a deadline cannot be empty.");
                        continue;
                    } else {
                        int firstIndexAfterDeadline = 9;
                        int i = input.indexOf('/');
                        if (i == -1) {
                            System.out.println("\tOOPS!!! The deadline for a deadline cannot be empty or it must be after a '/'");
                            continue;
                        } else {
                            String deadline = input.substring(i + 1, input.length() - 1+1);
                            currentTask = new Deadline(input.substring(firstIndexAfterDeadline, i), deadline);
                            tasks.add(currentTask);
                        }
                    }
                } else if (input.contains("event")) {
                    String test = input.replaceAll("\\s+","");
                    if (test.length() == 5) {
                        System.out.println("\tOOPS!!! The description of a deadline cannot be empty.");
                        continue;
                    } else {
                        int firstIndexAfterDeadline = 6;
                        int i = input.indexOf('/');
                        if (i == -1) {
                            System.out.println("\tOOPS!!! The duration for an event cannot be empty or it must be after a '/'");
                            continue;
                        } else {
                            String deadline = input.substring(i + 1, input.length() - 1+1);
                            currentTask = new Event(input.substring(firstIndexAfterDeadline, i), deadline);
                            tasks.add(currentTask);
                        }
                    }
                } else if (input.contains("delete")) {
                    char itemIndex = input.charAt(input.length() - 1);
                    int i = Integer.parseInt(String.valueOf(itemIndex)) - 1;
                    System.out.println("\tNoted. I've removed this task:");
                    System.out.println("\t\t" + tasks.remove(i).toString());
                    System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
                    continue;
                } else {
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }

                System.out.println("\tGot it. I've added this task:");
                if (currentTask != null) {
                    System.out.println("\t\t" + currentTask.toString());
                }
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
        }
    }
}
