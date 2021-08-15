public class FeatureMain {
    private String currentCommand;

    public FeatureMain(String command) {
        this.currentCommand = command;
    }

    // Level-1 specification
    public String echoCommand() {
        System.out.println(currentCommand + "\n");
        return currentCommand;
    }
}
