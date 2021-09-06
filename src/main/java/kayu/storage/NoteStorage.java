package kayu.storage;

import kayu.exception.StorageException;
import kayu.note.Note;

public class NoteStorage extends Storage<Note> {

    protected static final String ERROR_EMPTY_NOTE_DESC = "Note cannot have an empty description.";

    // Default task file directory.
    private static final String DEFAULT_FILE_PATH = "data/notes.txt";

    private NoteStorage(String directoryPath, String filePath) {
        super(directoryPath, filePath);
    }

    public static NoteStorage generate() {
        return NoteStorage.generate(DEFAULT_FILE_PATH);
    }

    public static NoteStorage generate(String filePath) {
        String directoryPath = Storage.extractDirectoryPath(filePath);
        return new NoteStorage(directoryPath, filePath);
    }

    @Override
    protected Note decode(String encoded) throws StorageException {
        if (encoded.isBlank()) {
            throw new StorageException(ERROR_EMPTY_NOTE_DESC);
        }
        return new Note(encoded);
    }

    @Override
    protected String encode(Note decoded) {
        return decoded.toString();
    }
}
