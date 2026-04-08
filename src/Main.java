import data.Data;
import data.DataSet;
import smoothing.LaplaceSmoothing;

import java.util.Set;

public class Main {
	static void main(String[] args) {
		{
			DataSet dataSet = new DataSet(Set.of(
					new Data(new String[]{"4", "Tak", "Nie", "Nie", "Nie"}), // żaba
					new Data(new String[]{"4", "Nie", "Nie", "Tak", "Tak"}), // lis
					new Data(new String[]{"4", "Tak", "Nie", "Tak", "Tak"}), // dziobak
					new Data(new String[]{"0", "Nie", "Nie", "Nie", "Tak"}), // delfin
					new Data(new String[]{"0", "Nie", "Tak", "Nie", "Nie"}), // rekin
					new Data(new String[]{"6", "Tak", "Nie", "Tak", "Nie"}), // pszczoła
					new Data(new String[]{"2", "Tak", "Nie", "Nie", "Nie"}), // sowa
					new Data(new String[]{"2", "Nie", "Nie", "Tak", "Tak"})  // nietoperz
			), new LaplaceSmoothing());

			var classifier = new NaiveBayesClassifier(dataSet, false);

//			System.out.printf("Ćma: %s\n", classifier.predict(new String[]{"6", "Tak", "Nie", "Tak"}));
//			System.out.printf("Wąż: %s\n", classifier.predict(new String[]{"0", "Tak", "Tak", "Nak"}));
//			System.out.printf("Pangolin: %s\n", classifier.predict(new String[]{"4", "Nie", "Tak", "Nie"}));
//			System.out.printf("Gąbka: %s\n", classifier.predict(new String[]{"0", "Nie", "Nie", "Nie"}));
		}

//		PROJECT
		{
			DataSet dataSet = new DataSet(Set.of(
					new Data(new String[]{"sunny", "hot", "high", "false", "no"}),
					new Data(new String[]{"sunny", "hot", "high", "true", "no"}),
					new Data(new String[]{"overcast", "hot", "high", "false", "yes"}),
					new Data(new String[]{"rainy", "mild", "high", "false", "yes"}),
					new Data(new String[]{"rainy", "cool", "normal", "false", "yes"}),
					new Data(new String[]{"rainy", "cool", "normal", "true", "no"}),
					new Data(new String[]{"overcast", "cool", "normal", "true", "yes"}),
					new Data(new String[]{"sunny", "mild", "high", "false", "no"}),
					new Data(new String[]{"sunny", "cool", "normal", "false", "yes"}),
					new Data(new String[]{"rainy", "mild", "normal", "false", "yes"}),
					new Data(new String[]{"sunny", "mild", "normal", "true", "yes"}),
					new Data(new String[]{"overcast", "mild", "high", "true", "yes"})
			), new LaplaceSmoothing());

//			new Data(new String[]{"overcast", 	"hot", 	"normal", 	"false", 	"yes"})
//			new Data(new String[]{"rainy", 		"mild", "high", 	"true", 	"no"})

			var classifier = new NaiveBayesClassifier(dataSet, true);

			var expectedClasses = new String[]{"yes", "no"};
			var predictedClasses = new String[]{
					classifier.predict(new String[]{"overcast", "hot", "normal", "false"}),
					classifier.predict(new String[]{"rainy", "mild", "high", "true"})
			};

			System.out.printf("Accuracy: %.4f\n", EvaluationMetrics.measureAccuracy(expectedClasses, predictedClasses));
			System.out.printf("Precision: %.4f\n", EvaluationMetrics.measurePrecision(expectedClasses, predictedClasses, "yes"));
			System.out.printf("Recall:    %.4f\n", EvaluationMetrics.measureRecall(expectedClasses, predictedClasses, "yes"));
			System.out.printf("F-Score:   %.4f\n", EvaluationMetrics.measureFScore(expectedClasses, predictedClasses, "yes"));
		}

	}
}