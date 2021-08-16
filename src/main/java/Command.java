public class Command {
    protected Instruction instruction;
    protected String parameter_1;
    protected String parameter_2;

    public Command(String input) {
        String[] inputValues = input.split(" ", 2);
        if (inputValues.length > 1) {
            String[] inputParameters = inputValues[1].split("\\s/((at)|(by))\\s");
            if (inputParameters.length > 1) {
                this.parameter_1 = inputParameters[0];
                this.parameter_2 = inputParameters[1];
            } else {
                this.parameter_1 = inputParameters[0];
            }
        }

//        this.instruction = Instruction.valueOf(inputValues[0].toUpperCase());
        this.instruction = Instruction.toInstruction(inputValues[0]);
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    public String getParameter_1() {
        return this.parameter_1;
    }

    public String getParameter_2() {
        return this.parameter_2;
    }
}
