package model.vocab;

import java.util.Objects;

public class Vocab {

    private final String phrase;
    private final String feedback;

    private Vocab(String phrase, String feedback) {
        this.phrase = phrase;
        this.feedback = feedback;
    }

    public static Vocab of(String phrase, String feedback) {
        return new Vocab(phrase, feedback);
    }

    public String getPhrase() {
        return phrase;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vocab)) {
            return false;
        }
        Vocab otherVocab = (Vocab) other;
        return otherVocab.phrase.equals(phrase) && otherVocab.feedback.equals(feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phrase, feedback);
    }
}
