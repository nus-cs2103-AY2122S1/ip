import exceptions.AuguryException;

public class App {
    public static void main(String args[]) throws AuguryException {
        Augury a = new Augury("data/tasks.txt");
        a.init();
        a.greet();
        a.loop();
    }
}
