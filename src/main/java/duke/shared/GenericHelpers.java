package duke.shared;

public class GenericHelpers {
    /**
     * Reference
     * https://stackoverflow.com/questions/9648305/creating-a-hashcode-method-java
     *
     * Generates a composite hashcode given multiple hashcodes.
     *
     * @param codes Hashcodes to be combined.
     */
    public static int combineHashCodes(int... codes) {
        int generatedCode = 1;
        for (int i = 0; i < codes.length; i++) {
            generatedCode = generatedCode * 31 + codes[i];
        }
        return generatedCode;
    }
}
