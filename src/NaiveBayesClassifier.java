import data.DataSet;

public class NaiveBayesClassifier {
	private final DataSet trainDataSet;

	private boolean applySmoothingAll;

	public NaiveBayesClassifier(DataSet trainDataSet, boolean applySmoothingAll) {
		this.trainDataSet = trainDataSet;
		this.applySmoothingAll = applySmoothingAll;
	}

	public void setApplySmoothingAll(boolean applySmoothingAll) {
		this.applySmoothingAll = applySmoothingAll;
	}

	public String predict(String[] input) {
		String bestPrediction = null;
		double bestPredictionScore = Double.NEGATIVE_INFINITY;

		for (String y : trainDataSet.getYSet()) {
			double score = trainDataSet.getPriori(y, applySmoothingAll);
			for (int i = 0; i < input.length; i++) {
				score *= trainDataSet.getPosteriori(y, i, input[i], applySmoothingAll);
			}
			if (score > bestPredictionScore) {
				bestPrediction = y;
				bestPredictionScore = score;
			}
		}
		return bestPrediction;
	}
}
