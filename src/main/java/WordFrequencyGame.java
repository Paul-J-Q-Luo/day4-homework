import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITESPACE_REGEX = "\\s+";

    public String getResult(String inputStr) {
        try {
            String[] words = splitInputString(inputStr);
            if (words.length == 1) {
                return formatSingleWordResult(inputStr);
            }

            Map<String, Long> wordFrequencyMap = countFrequencies(words);
            List<WordFrequency> sortedWordFrequencies = createSortedWordFrequencies(wordFrequencyMap);
            return composeOutput(sortedWordFrequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private static String formatSingleWordResult(String inputStr) {
        return inputStr + " 1";
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

    private String[] splitInputString(String inputStr) {
        return inputStr.split(WHITESPACE_REGEX);
    }

    private record WordFrequency(String word, int count) {
    }
}
