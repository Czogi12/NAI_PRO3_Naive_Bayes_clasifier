import data.DataSet;
import interfaces.ISmoothing;

import java.util.Map;

public class NaiveBayesClassifier {
    private final DataSet dataSet;

    private boolean applySmoothingAll;
    private final ISmoothing smoothing;

    public NaiveBayesClassifier(
            DataSet dataSet,
            boolean applySmoothingAll,
            ISmoothing smoothing) {
        this.dataSet = dataSet;
        this.applySmoothingAll = applySmoothingAll;
        this.smoothing = smoothing;
    }

    public void setApplySmoothingAll(boolean applySmoothingAll) {
        this.applySmoothingAll = applySmoothingAll;
    }

    public void trainDataSet() {

    }
}
