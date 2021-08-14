public class Command {
    private String instruction;

    public Command(String instruction) {
        this.instruction = instruction;
    };

    public String getInstruction() {
        return this.instruction;
    }

    public boolean isGoodBye() {
        return instruction.equals("bye");
    }
}
