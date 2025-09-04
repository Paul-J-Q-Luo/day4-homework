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

            List<Input> inputList;
            Map<String, Long> map = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            List<Input> list = new ArrayList<>();
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().intValue());
                list.add(input);
            }
            inputList = list;

            inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (Input w : inputList) {
                String s = w.getValue() + " " + w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String[] splitInputString(String inputStr) {
        return inputStr.split(WHITESPACE_REGEX);
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
