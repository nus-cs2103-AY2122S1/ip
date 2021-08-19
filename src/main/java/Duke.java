import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! This is Jarvis.");
        System.out.println("What can I do for you sir?");
        System.out.println("---------------------------------");
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String taskDescription = sc.nextLine();
                if (taskDescription.equals("bye")) {
                    System.out.println("---------------------------------");
                    System.out.println("Goodbye Sir. Hope you have a pleasant day sir.");
                    System.out.println("---------------------------------");
                    break;
                } else if (taskDescription.equals("list")) {
                    System.out.println("---------------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.printf("%d. %s%n", i + 1, task);
                    }
                    System.out.println("---------------------------------");
                } else if (taskDescription.startsWith("done ")) {
                    int serialNum = Integer.parseInt(taskDescription.split(" ")[1]);
                    Task task = tasks.get(serialNum - 1);
                    task.markAsDone();
                    System.out.println("---------------------------------");
                    System.out.println("I have marked this task as done");
                    System.out.printf("%s%n", task);
                    System.out.println("---------------------------------");
                } else if (taskDescription.startsWith("deadline ")) {
                    String[] splitText = taskDescription.split(" ");
                    String description = "";
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < splitText.length; i++) {
                        if (splitText[i].equals("/by")) {
                            description = sb.toString().trim();
                            sb = new StringBuilder();
                        } else {
                            sb.append(splitText[i]).append(" ");
                        }
                    }
                    String date = sb.toString().trim();
                    Task task = new Deadline(description, date);
                    tasks.add(task);
                    System.out.println("---------------------------------");
                    System.out.println("I have added this task: ");
                    System.out.println(task.toString());
                    System.out.printf("Now you have %d tasks.%n", tasks.size());
                    System.out.println("---------------------------------");
                } else if (taskDescription.startsWith("event ")) {
                    String[] splitText = taskDescription.split(" ");
                    String description = "";
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < splitText.length; i++) {
                        if (splitText[i].equals("/at")) {
                            description = sb.toString().trim();
                            sb = new StringBuilder();
                        } else {
                            sb.append(splitText[i]).append(" ");
                        }
                    }
                    String date = sb.toString().trim();
                    Task task = new Event(description, date);
                    tasks.add(task);
                    System.out.println("---------------------------------");
                    System.out.println("I have added this task: ");
                    System.out.println(task.toString());
                    System.out.printf("Now you have %d tasks.%n", tasks.size());
                    System.out.println("---------------------------------");
                } else if (taskDescription.startsWith("todo ")) {
                    String[] splitText = taskDescription.split(" ");
                    if (splitText.length == 1) {
                        throw new DukeException("Sorry Sir, todo must be followed with a description");
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < splitText.length; i++) {
                        sb.append(splitText[i]).append(" ");
                    }
                    Task task = new ToDo(sb.toString().trim());
                    tasks.add(task);
                    System.out.println("---------------------------------");
                    System.out.println("I have added this task: ");
                    System.out.println(task.toString());
                    System.out.printf("Now you have %d tasks.%n", tasks.size());
                    System.out.println("---------------------------------");
                }  else if (taskDescription.startsWith("delete ")) {
                    int serialNum = Integer.parseInt(taskDescription.split(" ")[1]);
                    Task task = tasks.get(serialNum - 1);
                    tasks.remove(serialNum - 1);
                    System.out.println("---------------------------------");
                    System.out.println("I have removed this task");
                    System.out.printf("%s%n", task);
                    System.out.printf("Now you have %d tasks.%n", tasks.size());
                    System.out.println("---------------------------------");
                } else {
                    throw new DukeException("Sorry Sir, I cannot understand the command");
                }
            } catch (DukeException e) {
                System.out.println("---------------------------------");
                System.out.println(e.getMessage());
                System.out.println("---------------------------------");
            }
        }
        sc.close();
    }
}
