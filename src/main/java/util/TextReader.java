package util;

import java.io.File;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class TextReader {

    public static List<String> readInput(String inputFile) {
        ClassLoader classLoader = TextReader.class.getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines.toList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
