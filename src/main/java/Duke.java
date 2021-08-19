import java.util.Objects;
import java.util.Scanner;

/**
 * This program is a chatbot that creates a task list for you and is able to mark tasks as done.
 */
public class Duke {
    public static void main(String[] args) {
        String inData = "yo";
        String intro = "____________________________________________________________ \n"
                + "Hello! I'm Joker \n"
                + "What can I do for you? \n"
                + "____________________________________________________________";
        String[] jokerList = new String[100];
        Task[] taskList = new Task[100];
        int i = 0;

        System.out.println(intro);

        Scanner scan = new Scanner(System.in);
        while (!Objects.equals(inData, "bye")) {
            inData = scan.nextLine();
            if (Objects.equals(inData, "list")) {
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list: ");
                for (int j = 0; j < i; j++) {
                    if (taskList[j] instanceof ToDo) {
                        System.out.println(j + 1 + ". [" + taskList[j].getTask() + "]" +
                                "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription());
                    } else {
                        System.out.println(j + 1 + ". [" + taskList[j].getTask() + "]" +
                                "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription()
                                + taskList[j].getDate());
                    }
                }
                System.out.println("____________________________________________________________");
            } else {
                int inDataLength = inData.length();
                if (inDataLength < 6) {
                    try {
                        if (!Objects.equals(inData, "bye") && Objects.equals(inData.substring(0, 4), "todo")) {
                            inData.substring(0, 6);
                        } else {
                            try {
                                if (!Objects.equals(inData, "bye") && !Objects.equals(inData.substring(0, 4), "done")) {
                                    System.out.println("____________________________________________________________\n" +
                                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                            "____________________________________________________________");
                                }
                            } catch (Exception e) {
                                System.out.println("____________________________________________________________\n" +
                                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                        "____________________________________________________________");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                } else {
                    if (!inData.contains("deadline") && !inData.contains("todo") && !inData.contains("event")
                            && !inData.contains("done") && !inData.contains("list") && !inData.contains("bye")) {
                        System.out.println("____________________________________________________________\n" +
                                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "____________________________________________________________");
                    } else {
                        if (!Objects.equals(inData, "bye") && Objects.equals(inData.substring(0, 5), "todo ")) {
                            jokerList[i] = inData.substring(5, inDataLength);
                            taskList[i] = new ToDo(inData.substring(5, inDataLength));
                            System.out.println(
                                    "____________________________________________________________ \n"
                                            + "Got it. I've added this task: \n"
                                            + "[" + taskList[i].getTask() + "]"
                                            + "[" + taskList[i].getStatusIcon() + "] "
                                            + taskList[i].getDescription()
                                            + "\n"
                                            + "Now you have " + (i + 1) + " tasks in the list.\n"
                                            + "____________________________________________________________");
                            i++;

                        }
                        if (inData.contains("deadline") || inData.contains("event")) {
                            if (Objects.equals(inData.substring(0, 9), "deadline ")) {
                                String[] segments = inData.split("/by ");
                                String date = segments[segments.length - 1];

                                int segmentedLength = segments[0].length();
                                String description = segments[0].substring(9, segmentedLength);
                                jokerList[i] = description;
                                taskList[i] = new Deadline(description, date);

                                System.out.println(
                                        "____________________________________________________________ \n"
                                                + "Got it. I've added this task: \n"
                                                + "[" + taskList[i].getTask() + "]"
                                                + "[" + taskList[i].getStatusIcon() + "] "
                                                + taskList[i].getDescription()
                                                + "\n"
                                                + "Now you have " + (i + 1) + " tasks in the list.\n"
                                                + "____________________________________________________________");
                                i++;

                            } else if (Objects.equals(inData.substring(0, 6), "event ")) {
                                String[] segments = inData.split("/at ");
                                String date = segments[segments.length - 1];

                                int segmentedLength = segments[0].length();
                                String description = segments[0].substring(6, segmentedLength);
                                jokerList[i] = description;
                                taskList[i] = new Event(description, date);

                                System.out.println(
                                        "____________________________________________________________ \n"
                                                + "Got it. I've added this task: \n"
                                                + "[" + taskList[i].getTask() + "]"
                                                + "[" + taskList[i].getStatusIcon() + "] "
                                                + taskList[i].getDescription()
                                                + "\n"
                                                + "Now you have " + (i + 1) + " tasks in the list.\n"
                                                + "____________________________________________________________");
                                i++;
                            }
                        } else {
                            if (Objects.equals(inData.substring(0, 5), "done ")) {
                                if (isNumeric(inData.substring(5, inDataLength))) {
                                    int taskNo = Integer.parseInt(inData.substring(5, inDataLength));
                                    if (taskNo <= 100 && taskNo <= i) {
                                        taskList[taskNo - 1].markAsDone();
                                        System.out.println(
                                                "____________________________________________________________\n"
                                                        + "Nice! I've marked this task as done: \n"
                                                        + " [" + taskList[taskNo - 1].getStatusIcon() + "] "
                                                        + taskList[taskNo - 1].getDescription()
                                                        + "\n____________________________________________________________");
                                    } else {
                                        System.out.println(
                                                "____________________________________________________________ \n"
                                                        + "invalid number!"
                                                        + "\n____________________________________________________________");
                                    }
                                }
                            }
//                        } else {
//                            jokerList[i] = inData;
//                            taskList[i] = new Task(inData);
//                            System.out.println(
//                                    "____________________________________________________________ \n"
//                                            + "added: "
//                                            + inData
//                                            + "\n"
//                                            + "____________________________________________________________");
//                            i++;
//                        }
                        }
                    }
                }
            }
            System.out.println(
                    "____________________________________________________________ \n"
                            + "Bye. Hope to see you again soon! \n"
                            + "____________________________________________________________");
        }
    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
