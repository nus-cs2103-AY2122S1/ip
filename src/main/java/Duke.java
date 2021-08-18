import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        class Task {
            private final String description;
            private boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public String checkIsDone() {
                if (this.isDone) {
                    return "X";
                } else {
                    return " ";
                }
            }

            public String getDescription() {
                return this.description;
            }

            public void markAsDone() {
                this.isDone = true;
            }
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "      My favorite partner is back! How can I help?\n"
                + "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Task[] taskList = new Task[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (true) {
            String str = sc.nextLine();
            System.out.print("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            if (str.equals("bye")) {
                System.out.print("      Have a good day, friend!\n"
                        + "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;
            } else if (str.equals("list")) {
                    for (int j = 0; j < i; j++) {
                        String listItem = "      " + (j + 1) + ".[" + taskList[j].checkIsDone()
                                + "] " + taskList[j].getDescription() + "\n";
                        System.out.print(listItem);
                }
            } else if(str.contains("done ")) {
                Integer listIndex = Integer.parseInt(str.substring(5)) - 1;
                taskList[listIndex].markAsDone();
                System.out.print("      Well Done, I'll get it marked:\n"
                        + "        [" + taskList[listIndex].checkIsDone()
                        + "] " + taskList[listIndex].getDescription() + "\n");
            } else {
                    taskList[i++] = new Task(str);
                    System.out.print("      added: " + str + "\n");
            }
            System.out.print("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        }
    }


