import java.util.Scanner;

public class User {
    Scanner sc = new Scanner(System.in);
    
    public String command() {
        if (sc.hasNextLine()) {
            String str = sc.nextLine();
            return str;
        }
        return "";
    }
}