import smoothing.LaplaceSmoothing;

public class Main {
    public static void main(String[] args) {
        var classifier = new NaiveBayesClassifier(
                new LaplaceSmoothing()
        );
    }
}