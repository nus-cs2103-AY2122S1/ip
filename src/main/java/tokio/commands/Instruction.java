package tokio.commands;

/**
 * Stores the types of Instruction a command can have.
 */
public enum Instruction {
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DELETE("delete"),
    BYE("bye"),
    INVALID("invalid"),
    FIND("find");

    private final String s;
    Instruction(String s) {
        this.s = s;
    }

    /**
     * Compares Instruction with string.
     * @param str Input string.
     * @return Instruction if such an instruction exist, otherwise invalid
     */
    public static Instruction comparesTo(String str) {
        for (Instruction i : Instruction.values()) {
            if (i.s.equalsIgnoreCase(str)) {
                return i;
            }
        }
        return INVALID;
    }
}
