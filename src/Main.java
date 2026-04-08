import data.Data;
import data.DataSet;
import smoothing.LaplaceSmoothing;

import java.util.Set;

public class Main {
	static void main(String[] args) {
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

		System.out.printf("Ćma: %s\n", classifier.predict(new String[]{"6", "Tak", "Nie", "Tak"}));
		System.out.printf("Wąż: %s\n", classifier.predict(new String[]{"0", "Tak", "Tak", "Nak"}));
		System.out.printf("Pangolin: %s\n", classifier.predict(new String[]{"4", "Nie", "Tak", "Nie"}));
		System.out.printf("Gąbka: %s\n", classifier.predict(new String[]{"0", "Nie", "Nie", "Nie"}));
	}
}