
public class Duke {

    private Speech speech;
    private Brain brain;
    private Storage storage;

    public Duke() {
        speech = new Speech();
        brain = new Brain();
        storage = new Storage();
    }

    public void run(){
        speech.welcome();

        while (true) {
            System.out.print("Say something to Duke: ");
            boolean cont = brain.decide(speech, storage);
            if (!cont) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        new Duke().run();
    }
}
