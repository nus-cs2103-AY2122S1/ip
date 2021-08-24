package mango;
import java.util.Scanner;

public class Parser {
    public static void parse(TaskList tasks) {
        Scanner sc = new Scanner(System.in);
        String str;

        while (!(str = sc.nextLine()).equals("bye")) {
            try {
                if (str.equals("list")) {
                    tasks.printList();
                } else if (str.contains("done")) {
                    tasks.complete(Character.getNumericValue(str.charAt(5)));
                } else if (str.contains("delete")) {
                    tasks.delete(Character.getNumericValue(str.charAt(7)));
                } else if (str.contains("find")){
                    tasks.find(str.split(" ", 2)[1]);
                } else {
                    tasks.add(str);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
