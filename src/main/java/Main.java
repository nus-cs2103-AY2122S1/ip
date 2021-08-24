import Duke.Duke;

public class Main {
    public static void main(String[] args) {
        try {
            Duke duke = new Duke();
            duke.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
