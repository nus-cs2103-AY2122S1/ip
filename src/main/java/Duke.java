import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Task[] taskArray = new Task[100];
        int listIndex = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "\nWhat can I do for you today?");

        while(true) {
            String input = scan.nextLine();
            String[] inputArray = input.split("\\s");
            String firstString = inputArray[0];

            //case if nothing is entered
            if (firstString.equals("")) {
                continue;
            }

            //case if user wants to exit the program
            if (firstString.equals("bye")) {
                break;
            }

            //cases for specified keywords
            if (firstString.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (taskArray[i] == null) {
                        break;
                    }
                    int listNumber = i + 1;
                    System.out.println(listNumber + ". " + taskArray[i].toString());
                }
                continue;
            }

            if (firstString.equals("done") ) {
                if (inputArray.length < 2) {
                    //case if no number is entered
                    System.out.println("Please enter the index of the task to complete after the keyword done!");
                    continue;
                }
                try {
                    int index = Integer.parseInt(inputArray[1]);
                    int arrayIndex = index - 1;
                    if (index > listIndex || index < 1) {
                        System.out.println("Sorry, that task does not exist!");
                        continue;
                    }
                    taskArray[arrayIndex].setCompleted();
                    System.out.println("Ok, very nice. I have set the following task as completed.\n" + taskArray[arrayIndex].toString());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                } finally {
                    continue;
                }
            }

            if (firstString.equals("todo")) {
                //initialise new StringBuilder instance to construct the remaining string
                StringBuilder str = new StringBuilder();
                for (int i = 1; i < inputArray.length; i++) {
                    str.append(inputArray[i]).append(" ");
                }

                Todos tempTask = new Todos(str.toString());
                taskArray[listIndex] = tempTask;
                listIndex += 1;

                System.out.println("Ok can, sure. I have added this task as you wanted.");
                System.out.println(tempTask.toString());
                System.out.println("Now you have only " + listIndex + " tasks in the list. Try being more hardworking!");
                continue;
            }

            if (firstString.equals("deadline")) {
                //initialise new StringBuilder instance to construct the remaining string
                StringBuilder str = new StringBuilder();
                String time = "";
                boolean stringEnded = false;

                for (int i = 1; i < inputArray.length; i++) {
                    String currentArrayElement = inputArray[i];
                    if (stringEnded) {
                        time = currentArrayElement;
                        break;
                    }
                    if (currentArrayElement.equals("/by")) {
                        stringEnded = true;
                        continue;
                    }
                    str.append(currentArrayElement).append(" ");
                }

                Deadlines tempTask = new Deadlines(str.toString(), time);
                taskArray[listIndex] = tempTask;
                listIndex += 1;

                System.out.println("Ok can, sure. I have added this task as you wanted.");
                System.out.println(tempTask.toString());
                System.out.println("Now you have only " + listIndex + " tasks in the list. Try being more hardworking!");
                continue;
            }

            if (firstString.equals("event")) {
                //initialise new StringBuilder instance to construct the remaining string
                StringBuilder str = new StringBuilder();
                String time = "";
                boolean stringEnded = false;

                for (int i = 1; i < inputArray.length; i++) {
                    String currentArrayElement = inputArray[i];
                    if (stringEnded) {
                        time = currentArrayElement;
                        break;
                    }
                    if (currentArrayElement.equals("/at")) {
                        stringEnded = true;
                        continue;
                    }
                    str.append(currentArrayElement).append(" ");
                }

                Events tempTask = new Events(str.toString(), time);
                taskArray[listIndex] = tempTask;
                listIndex += 1;

                System.out.println("Ok can, sure. I have added this task as you wanted.");
                System.out.println(tempTask.toString());
                System.out.println("Now you have only " + listIndex + " tasks in the list. Try being more hardworking!");
                continue;
            }

            System.out.println("Whoops, I could not understand your input. Please try again!");
        }

        scan.close();
        System.out.println("See you again next time!");
    }
}
