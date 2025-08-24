import java.util.List;

public class GradeCalculator {

    public double calculateAverage(List<Integer> grades) {
        double average = grades.stream().mapToDouble(Integer::doubleValue).average().orElse(0);
        return average;
    }

    public int findHighest(List<Integer> grades) {
        int highestOne = grades.stream().mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
        return highestOne;
    }

    public int findLowest(List<Integer> grades) {
        int lowestOne = grades.stream().mapToInt(Integer::intValue).min().orElse(Integer.MIN_VALUE);
        return lowestOne;
    }
}
