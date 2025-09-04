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

            Map<String, Long> wordFrequencyMap = calculateWordFrequency(words);
            List<Input> sortedWordFrequencies = createSortedWordFrequencies(wordFrequencyMap);
            return formatResult(sortedWordFrequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private static String formatSingleWordResult(String inputStr) {
        return inputStr + " 1";
    }

    private String formatResult(List<Input> wordFrequencies) {
        return wordFrequencies.stream()
                .map(input -> input.getValue() + " " + input.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private static List<Input> createSortedWordFrequencies(Map<String, Long> wordFrequencyMap) {
        return wordFrequencyMap.entrySet().stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().intValue()))
                .sorted((input1, input2) -> Integer.compare(input2.getWordCount(), input1.getWordCount()))
                .toList();
    }

    private static Map<String, Long> calculateWordFrequency(String[] words) {
        return Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private String[] splitInputString(String inputStr) {
        return inputStr.split(WHITESPACE_REGEX);
    }
}
