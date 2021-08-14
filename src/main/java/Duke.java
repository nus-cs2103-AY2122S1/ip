
public class Duke {

    private Speech speech;
    private Brain brain;

    public Duke() {
        speech = new Speech();
        brain = new Brain();
    }

    public void run(){
        speech.welcome();

        String user_input = "";

        while (true) {
            System.out.print("Say something to Duke: ");
            boolean cont = brain.decide(speech);
            if (!cont) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
}
