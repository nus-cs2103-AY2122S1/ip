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
                System.out.println("____________________________________________________________");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + ". [" + taskList[j].getStatusIcon() + "] " + jokerList[j]);
                }
                System.out.println("____________________________________________________________");
            } else {
                int inDataLength = inData.length();
                if (inDataLength < 6) {
                    jokerList[i] = inData;
                    taskList[i] = new Task(inData);
                    if (!Objects.equals(inData, "bye")) {
                        System.out.println(
                                "____________________________________________________________ \n"
                                + "added: "
                                + inData
                                + "\n"
                                + "____________________________________________________________");
                    }
                    i++;
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
                    } else {
                        jokerList[i] = inData;
                        taskList[i] = new Task(inData);
                        System.out.println(
                                "____________________________________________________________ \n"
                                + "added: "
                                + inData
                                + "\n"
                                + "____________________________________________________________");
                        i++;
                    }
                }
            }
        }
        System.out.println(
                "____________________________________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "____________________________________________________________");
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
