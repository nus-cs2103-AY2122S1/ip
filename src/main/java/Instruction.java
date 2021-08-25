public enum Instruction {
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DELETE("delete"),
    INVALID("invalid")
    ;

    private final String s;
    Instruction(String s) {
        this.s = s;
    }

    public static Instruction comparesTo(String str) {
        for (Instruction i : Instruction.values()) {
            if (i.s.equalsIgnoreCase(str)) {
                return i;
            }
        }
        
        return INVALID;
    }
}
