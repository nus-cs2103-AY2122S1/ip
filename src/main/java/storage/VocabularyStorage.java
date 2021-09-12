package storage;

import model.vocab.Vocab;
import model.vocab.VocabList;
import parser.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class VocabularyStorage extends Storage {

    public static final String VOCAB_PATH = "/vocab.txt";

    private VocabList vocabListRead;

    public VocabularyStorage() throws IOException {
        this.filePath = DIRECTORY_PATH + VOCAB_PATH;
        WRITER = new BufferedWriter(new FileWriter(filePath, true));
        READER = new BufferedReader(new FileReader(filePath));
    }



    /**
     * load taskList from where the reader and writer is currently at
     *
     * @return TaskList read from the save file
     */
    public VocabList loadVocabList() {

        vocabListRead = new VocabList();

        READER.lines().forEach((line) -> {
            vocabListRead.add(Vocab.of(line.substring(0, line.indexOf(" |")), line.substring(line.indexOf("| ") + 2)));
        });

        return vocabListRead;
    }

    /**
     * Store the taskList into the save file the reader and writer are currently at
     *
     * @param vocabList the taskList to be stored
     * @throws IOException if there is any error dealing with the system IO
     */
    public void save(VocabList vocabList) throws IOException {
        Path path = Paths.get(filePath);
        List<String> fileContent =
                vocabList.getVocabs().stream().filter(vocab -> !VocabList.DEFAULT_PHRASE.contains(vocab.getPhrase()))
                        .map(Parser::vocabToSaveFormat).collect(Collectors.toList());
        Files.write(path, fileContent, StandardCharsets.UTF_8);
        READER.close();
        WRITER.close();
    }

    public void close() throws IOException {
        READER.close();
        WRITER.close();
    }

}
