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
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greetingMessage = "Henlo, this is Duke.\nHow may I help?";
        System.out.println(greetingMessage);
        String listCommand = "list";
        String byeString = "bye";

        Task[] userTaskList = new Task[100];
        int currentCapacity = 0;

        while (true) {
            String response = sc.nextLine();

            if (response.equals(byeString)) {
                System.out.println("Byebye!! Hehe..");
                break;
            } else if (response.startsWith("done")) {
                if (doneCheck(response, currentCapacity) > 0) {
                    int positionToMarkDone = doneCheck(response, currentCapacity);
                    userTaskList[positionToMarkDone - 1].markDone();
                    String replyString = "Nice! I've marked this task as done:\n";
                    replyString += String.format("  [%s] %s", userTaskList[positionToMarkDone - 1].getStatusIcon(), userTaskList[positionToMarkDone - 1].toString());
                    System.out.println(replyString);
                    continue;
                } else if (doneCheck(response, currentCapacity) == -1){
                    System.out.println("No such task exists! Please try again!");
                    continue;
                }
            } else if (response.startsWith("undo")) {
                if (undoCheck(response, currentCapacity) > 0) {
                    int positionToMarkUndone = undoCheck(response, currentCapacity);
                    userTaskList[positionToMarkUndone - 1].markUndone();
                    String replyString = "Oh no! I've marked this task as undone:\n";
                    replyString += String.format("  [%s] %s", userTaskList[positionToMarkUndone - 1].getStatusIcon(), userTaskList[positionToMarkUndone - 1].toString());
                    System.out.println(replyString);
                    continue;
                } else if (doneCheck(response, currentCapacity) == -1) {
                    System.out.println("No such task exists! Please try again!");
                    continue;
                }
            }

            if (!response.equals(listCommand)) {
                if (currentCapacity < 100) {
                    userTaskList[currentCapacity] = new Task(response);
                    currentCapacity += 1;
                    System.out.printf("added: %s\n", response);
                } else {
                    System.out.println("Your current list is full! Unable to add any more items!");
                }
            } else {
                if (currentCapacity > 0) {
                    String toPrint = "Here are the tasks in your list:\n";
                    for (int i = 0; i < currentCapacity; i++) {
                        Task task = userTaskList[i];
                        toPrint += String.format("%d.[%s] %s\n", i + 1, task.getStatusIcon(), task.toString());
                    }
                    System.out.print(toPrint);
                } else {
                    System.out.println("You have no tasks!");
                }

            }
        }

    }
}
