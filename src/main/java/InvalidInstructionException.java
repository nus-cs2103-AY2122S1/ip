public class InvalidInstructionException extends DukeException {

    private String instruction;

    public InvalidInstructionException(String str) {
        super(str);
        this.instruction = str;
    }

    @Override
    public String toString() {
        return "Invalid instruction: " + instruction + " is not a valid instruction.";
    }
}
