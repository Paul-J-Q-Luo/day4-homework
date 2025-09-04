import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITESPACE_REGEX = "\\s+";

    public String getResult(String inputStr) {
        try {
            String[] words = splitInputString(inputStr);
            if (words.length == 1) {
                return inputStr + " 1";
            }

            Map<String, Long> wordFrequencyMap = calculateWordFrequency(words);
            List<Input> sortedWordFrequencies = createSortedWordFrequencies(wordFrequencyMap);

            StringJoiner joiner = new StringJoiner("\n");
            for (Input w : sortedWordFrequencies) {
                String s = w.getValue() + " " + w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
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
