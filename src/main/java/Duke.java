import java.util.Scanner;


public class Duke {

    public static int doneCheck(String response, int currentCapacity) {
        String strippedResponse = response.strip();
        if (strippedResponse.startsWith("done")) {
            if (strippedResponse.length() < 6) {
                return 0;
            } else {
                String cutString = strippedResponse.substring(5);
                char[] chars = cutString.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        return 0;
                    }
                }
                Integer intToCheck = Integer.parseInt(sb.toString());
                if (intToCheck <= currentCapacity) {
                    return intToCheck;
                } else {
                    return -1;
                }
            }
        } else {
            return 0;
        }
    }

    public static boolean eventCheck(String response) {
        if (response.startsWith("event ") && response.split("/at").length == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deadlineCheck(String response) {
        if (response.startsWith("deadline ") && response.split("/by").length == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static int undoCheck(String response, int currentCapacity) {
        String strippedResponse = response.strip();
        if (strippedResponse.startsWith("undo")) {
            if (strippedResponse.length() < 6) {
                return 0;
            } else {
                String cutString = strippedResponse.substring(5);
                char[] chars = cutString.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        return 0;
                    }
                }
                Integer intToCheck = Integer.parseInt(sb.toString());
                if (intToCheck <= currentCapacity) {
                    return intToCheck;
                } else {
                    return -1;
                }
            }
        } else {
            return 0;
        }
    }

    public static String responseToTodo(String response) {
        return response.substring(5);
    }
    public static String pluralOrNo(int cap) {
        return cap <= 1 ? "" : "s";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greetingMessage = "Henlo, this is Duke.\nHow may I help?";
        System.out.println(greetingMessage);
        String listCommand = "list";
        String byeString = "bye";
        String listFullMessage = "Your current list is full! Unable to add any more items!";
        String taskAddedMessage  = "Got it. I've added this task:\n  %s\nNow you have %d task%s in the list\n";
        Task[] userTaskList = new Task[100];
        int currentCapacity = 0;

        while (true) {
            String response = sc.nextLine();

            if (response.equals(byeString)) {
                System.out.println("Byebye!! Hehe..");
                break;
            } else if (response.startsWith("done ")) {
                if (doneCheck(response, currentCapacity) > 0) {
                    int positionToMarkDone = doneCheck(response, currentCapacity);
                    userTaskList[positionToMarkDone - 1].markDone();
                    String replyString = "Nice! I've marked this task as done:\n";
                    replyString += String.format("  %s", userTaskList[positionToMarkDone - 1].toString());
                    System.out.println(replyString);
                    continue;
                } else if (doneCheck(response, currentCapacity) == -1) {
                    System.out.println("No such task exists! Please try again!");
                    continue;
                }
            } else if (response.startsWith("undo ")) {
                if (undoCheck(response, currentCapacity) > 0) {
                    int positionToMarkUndone = undoCheck(response, currentCapacity);
                    userTaskList[positionToMarkUndone - 1].markUndone();
                    String replyString = "Oh no! I've marked this task as undone:\n";
                    replyString += String.format("  %s", userTaskList[positionToMarkUndone - 1].toString());
                    System.out.println(replyString);
                    continue;
                } else if (doneCheck(response, currentCapacity) == -1) {
                    System.out.println("No such task exists! Please try again!");
                    continue;
                }
            } else if (response.equals(listCommand)) {
                if (currentCapacity > 0) {
                    String toPrint = "Here are the tasks in your list:\n";
                    for (int i = 0; i < currentCapacity; i++) {
                        Task task = userTaskList[i];
                        toPrint += String.format("%d. %s\n", i + 1, task);
                    }
                    System.out.print(toPrint);
                } else {
                    System.out.println("You have no tasks!");
                }
            } else if (response.startsWith("todo ")) {
                if (currentCapacity < 100) {
                    userTaskList[currentCapacity] = new ToDo(responseToTodo(response));
                    System.out.printf(taskAddedMessage, userTaskList[currentCapacity], currentCapacity + 1, pluralOrNo(currentCapacity + 1));
                    currentCapacity += 1;
                } else {
                    System.out.println(listFullMessage);
                }
            } else if (eventCheck(response)) {
                if (currentCapacity < 100) {
                    String[] eventSplit = response.split("/at");
                    String description = eventSplit[0].strip().substring(6);
                    String date = eventSplit[1].strip();
                    userTaskList[currentCapacity] = new Event(description, date);
                    System.out.printf(taskAddedMessage, userTaskList[currentCapacity], currentCapacity + 1, pluralOrNo(currentCapacity + 1));
                    currentCapacity += 1;
                } else {
                    System.out.println(listFullMessage);
                }
            } else if (deadlineCheck(response)) {
                if (currentCapacity < 100) {
                    String[] deadlineSplit = response.split("/by");
                    String description = deadlineSplit[0].strip().substring(9);
                    String date = deadlineSplit[1].strip();
                    userTaskList[currentCapacity] = new Deadline(description, date);
                    System.out.printf(taskAddedMessage, userTaskList[currentCapacity], currentCapacity + 1, pluralOrNo(currentCapacity + 1));
                    currentCapacity += 1;
                } else {
                    System.out.println(listFullMessage);
                }
            } else {
                System.out.printf("I don't quite understand the command '%s'. Please try again!\n", response);
            }

        }
    }
}
