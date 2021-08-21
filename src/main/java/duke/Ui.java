package duke;

import java.util.Scanner;

public class Ui {
    static Scanner sc = new Scanner(System.in);

    static String nextLine() {
        return sc.nextLine();
    }

    static void show(String... lines) {
        System.out.println("\t____________________________________");
        for (String line : lines)
            System.out.println("\t " + line);
        System.out.println("\t____________________________________");
    }
}
