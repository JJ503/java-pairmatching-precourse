package pairmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PairMatching {
    private HashMap<List<String>, List<List<String>>> pairMatch;

    PairMatching() {
        pairMatch = new HashMap<>();
    }

    private List<List<String>> pairMatch(List<String> categories, CrewsByCourse crews) {
        List<List<String>> newPairMatch = generatePairs(crews);
        pairMatch.put(categories, newPairMatch);
        return newPairMatch;
    }
    private List<List<String>> generatePairs(CrewsByCourse crews) {
        return pairMatchToStringList(crews.shuffledCrews());
    }

    private List<List<String>> pairMatchToStringList(List<String> shuffledCrew) {
        List<List<String>> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrew.size() - 1; i += 2) {
            List<String> pair = new ArrayList<>();
            pair.add(shuffledCrew.get(i));
            pair.add(shuffledCrew.get(i+1));
            pairs.add(pair);
        }
        if (isOdd(shuffledCrew.size())) {
            pairs.get(pairs.size() - 1).add(shuffledCrew.get(shuffledCrew.size() - 1));
        }
        return pairs;
    }

    private boolean isOdd(int size) {
        return size % 2 != 0;
    }
}
