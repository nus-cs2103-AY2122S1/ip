import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String indentation = "       ";
        String Horizontal_line = "-----------------------------";
        String greeting = "Hello! I'm Duke \n" + indentation + "What can I do for you?\n";
        final String LIST = "list";
        final String BLAH = "blah";
        final String BYE = "bye";
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
            String keyword = scanner.nextLine();
            switch(keyword) {
                case LIST:
                    System.out.println(indentation + Horizontal_line);
                    System.out.println(indentation + "list");
                    System.out.println(indentation + Horizontal_line);
                    break;
                case BLAH:
                    System.out.println(indentation + Horizontal_line);
                    System.out.println("blah");
                    System.out.println(indentation + Horizontal_line);
                    break;
                case BYE:
                    System.out.println(indentation + Horizontal_line);
                    System.out.println(indentation + "Bye. Hope to see you again soon!");
                    System.out.println(indentation + Horizontal_line);
                    scanner.close();
                    isEnd = true;
                    break;
            }
        }


        //System.out.println(keyword);
    }
}
