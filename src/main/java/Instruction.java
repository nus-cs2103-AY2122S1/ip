public enum Instruction {
    LIST, TODO, DEADLINE, EVENT, DONE, DELETE, BYE, ERROR;

    public static Instruction toInstruction(String instruction) {
        for (Instruction enumInstruction : Instruction.values()) {
            if (enumInstruction.name().equals(instruction.toUpperCase())) {
                return enumInstruction;
            }
        }
        return Instruction.ERROR;
    }
}
