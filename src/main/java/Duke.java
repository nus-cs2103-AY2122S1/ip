import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Task[] task = new Task[100];
        int taskNum = 0;
        String indentation = "       ";
        String Horizontal_line = "-----------------------------";
        String greeting = "Hello! I'm Duke \n" + indentation + "What can I do for you?\n";
        final String LIST = "list";
        final String BLAH = "blah";
        final String BYE = "bye";
        final String DONE = "done";
        boolean isEnd = false;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(indentation + Horizontal_line);
        System.out.println(indentation + greeting);
        System.out.println(indentation + Horizontal_line);
        Scanner scanner = new Scanner(System.in);


        while (!isEnd) {
            String keyword = scanner.next();
            switch(keyword) {
                case LIST:
                    System.out.println(indentation + Horizontal_line);
                    for (int i = 0; i < taskNum; i++) {
                        if (task[i].isDone() == false) {
                            System.out.println(indentation + (task[i].getIndex() + 1) + "." + " [ ] " + task[i].getName());
                        } else {
                            System.out.println(indentation + (task[i].getIndex() + 1) + "." + " [X] " + task[i].getName());
                        }
                    }
                    System.out.println(indentation + Horizontal_line);
                    break;
                case BLAH:
                    System.out.println(indentation + Horizontal_line);
                    System.out.println("blah");
                    System.out.println(indentation + Horizontal_line);
                    break;
                case DONE:
                    int num = scanner.nextInt();
                    try {
                        task[num - 1].setDone(true);
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Nice! I've marked this task as done: ");
                        System.out.println(indentation + "  [X] " + task[num - 1].getName());
                        System.out.println(indentation + Horizontal_line);
                    } catch (NullPointerException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, you do not have task " + num);
                        System.out.println(indentation + Horizontal_line);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(indentation + Horizontal_line);
                        System.out.println(indentation + "Sorry, please enter a task number bigger than 0");
                        System.out.println(indentation + Horizontal_line);
                    }

                    break;
                case BYE:
                    System.out.println(indentation + Horizontal_line);
                    System.out.println(indentation + "Bye. Hope to see you again soon!");
                    System.out.println(indentation + Horizontal_line);
                    scanner.close();
                    isEnd = true;
                    break;
                default:
                    task[taskNum] = new Task(keyword, false, taskNum);
                    taskNum++;
                    System.out.println(indentation + Horizontal_line);
                    System.out.println(indentation + "Added: "+ keyword);
                    System.out.println(indentation + Horizontal_line);
            }
        }


    }
}
