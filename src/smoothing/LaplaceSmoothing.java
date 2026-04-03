package smoothing;

import interfaces.ISmoothing;

public class LaplaceSmoothing implements ISmoothing {
    @Override
    public double simpleSmoothing(int numerator, int denominator, int classes) {
        return (double) (numerator + 1) / (denominator + classes);
    }
}
