import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITESPACE_REGEX = "\\s+";

    public String getResult(String inputStr) {
        try {
            validateInput(inputStr);

            String[] words = inputStr.split(WHITESPACE_REGEX);
            if (words.length == 1) {
                return inputStr + " 1";
            }

            Map<String, Long> wordFrequencyMap = countFrequencies(words);
            List<WordFrequency> sortedWordFrequencies = createSortedWordFrequencies(wordFrequencyMap);
            return composeOutput(sortedWordFrequencies);
        } catch (Exception e) {
            return "Invalid input format：" + e.getMessage();
        }
    }

    private void validateInput(String inputStr) {
        if (inputStr == null || inputStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
    }

    private String composeOutput(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.word() + " " + wordFrequency.count())
                .collect(Collectors.joining("\n"));
    }

    private static List<WordFrequency> createSortedWordFrequencies(Map<String, Long> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                .sorted((freq1, freq2) -> Integer.compare(freq2.count(), freq1.count()))
                .toList();
    }

    private static Map<String, Long> countFrequencies(String[] words) {
        return Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private record WordFrequency(String word, int count) {
    }
}
