import java.util.Scanner;

public class Brain {

    public boolean decide(Speech s) {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        switch (in) {
            case "bye":
                s.goodbye();
                return false;
            default :
                s.speak(in);
                return true;
        }
    }




}
