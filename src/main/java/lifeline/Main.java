package lifeline;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Lifeline lifeline = new Lifeline("save" + File.separator + "tasks.json");
        lifeline.start();
    }

}
