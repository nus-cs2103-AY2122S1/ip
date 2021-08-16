import java.util.Scanner;

public class Duke {
    //To check if input is an integer, used as a helper function when user inputs "done 2"
    public static boolean isInteger(String userInput) {
        if (userInput == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //Statement to show the user upon exit
        String byeStatement = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";

        //Statement to show the user upon running Duke
        String greetingStatement = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________";
        //Array to store whatever text entered by the user
        Task[] userList = new Task[100];
        //Pointer for array
        int pointer = 0;

        Scanner userInput = new Scanner(System.in);
        System.out.println(greetingStatement);

        while (true) {
            String description = userInput.nextLine();
            String[] descriptionArray = description.split(" ");
            String keyword = descriptionArray[0];
            //Added a check to ensure only valid inputs are recorded
            if (description.equals("")) {
                System.out.println("    ____________________________________________________________\n" +
                    "     Please insert a valid input!\n" +
                    "    ____________________________________________________________");
            }
            //Stop duke if user types "bye"
            //Added a check to ensure there is only one keyword in the input to exit Duke
            else if (keyword.equals("bye") && descriptionArray.length == 1) {
                break;
            }
            //prints out all the stored tasks if user types "list"
            //Added a check to ensure there is only one keyword in the input to call list
            else if (keyword.equals("list") && descriptionArray.length == 1) {
                System.out.println("    ____________________________________________________________\n    " + "Here are the tasks in your list:");
                for (int i = 0; i < pointer; i++) {
                    System.out.println("    " + (i + 1) + ". " + "[" + userList[i].getStatusIcon() + "] " + userList[i].getDescription());
                }
                System.out.println("    ____________________________________________________________");
            }
            //marks task as done if user types "done" and a number
            else if (keyword.equals("done") && descriptionArray.length == 2) {
                //check if 2nd input is an integer
                if (isInteger(descriptionArray[1])) {
                    int taskNumber = Integer.parseInt(descriptionArray[1]);
                    //Checks for a valid task number
                    if (taskNumber > pointer || taskNumber <= 0) {
                        System.out.println("    ____________________________________________________________\n" +
                            "     Please insert a valid Task Number!\n" +
                            "    ____________________________________________________________");
                    }
                    //If task number is valid mark task as done
                    else {
                        userList[taskNumber - 1].markAsDone();
                        System.out.println("    ____________________________________________________________\n    " + "Nice! I've marked this task as done:");
                        System.out.println("[" + userList[taskNumber - 1].getStatusIcon() + "] " + userList[taskNumber - 1].getDescription());
                        System.out.println("    ____________________________________________________________");
                    }
                }
                //else add it as a task
                else {
                    userList[pointer] = new Task(description);
                    pointer++;
                    System.out.println("    ____________________________________________________________\n    " +
                        "added: " + description + "\n" +
                        "    ____________________________________________________________");
                }
            }
            //else add the task to array and print it out to user
            else {
                userList[pointer] = new Task(description);
                pointer++;
                System.out.println("    ____________________________________________________________\n    " +
                    "added: " + description + "\n" +
                    "    ____________________________________________________________");
            }

        }

        System.out.println(byeStatement);

    }
}
