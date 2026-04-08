public class EvaluationMetrics<T> {
	private static <T> int[][] createConfusionMatrix(T[] realClasses, T[] predictedClasses, T positiveClass) {
		int[][] confusionMatrix = new int[2][2];
//		TP	FP
//		FN	TN
		for (int i = 0; i < realClasses.length; i++) {
			boolean isPositive = positiveClass.equals(predictedClasses[i]);
			boolean isFalse = !realClasses[i].equals(predictedClasses[i]);

			int y = isPositive ? 0 : 1;
			int x = y ^ (isFalse ? 1 : 0);

			confusionMatrix[y][x]++;
		}

		return confusionMatrix;
	}

	public static <T> double measurePrecision(T[] realClasses, T[] predictedClasses, T positiveClass) {
		var confusionMatrix = createConfusionMatrix(realClasses, predictedClasses, positiveClass);
		// TP / FP + TP
		return (double) confusionMatrix[0][0] / ((double) confusionMatrix[0][1] + (double) confusionMatrix[0][0]);
	}

	public static <T> double measureRecall(T[] realClasses, T[] predictedClasses, T positiveClass) {
		var confusionMatrix = createConfusionMatrix(realClasses, predictedClasses, positiveClass);
		// TP / FN + TP
		return (double) confusionMatrix[0][0] / ((double) confusionMatrix[1][0] + (double) confusionMatrix[0][0]);
	}

	public static <T> double measureFScore(T[] realClasses, T[] predictedClasses, T positiveClass) {
		// 2 * P * R / P + R
		var p = measurePrecision(realClasses, predictedClasses, positiveClass);
		var r = measureRecall(realClasses, predictedClasses, positiveClass);
		return 2 * p * r / (p + r);
	}

	public static <T> double measureAccuracy(T[] realClasses, T[] predictedClasses) {
		if (realClasses.length != predictedClasses.length) {
			throw new IllegalArgumentException("Number of realClasses and number of predictedClasses must be equal");
		}

		double matched = 0;

		for (int i = 0; i < realClasses.length; i++) {
			if (realClasses[i].equals(predictedClasses[i])) {
				matched++;
			}
		}

		return matched / realClasses.length;
	}
}
