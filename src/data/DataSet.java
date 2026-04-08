package data;

import interfaces.ISmoothing;

import java.util.*;
import java.util.stream.Collectors;

public class DataSet {
	private final Set<Data> dataSet;
	private final ISmoothing smoothing;
	int dataDimension;
	// 	Xn/Y counts
	List<Map<String, Integer>> counts;
	// Xn|Y counts
	Map<String, List<Map<String, Integer>>> jointCounts;

	public DataSet(Set<Data> dataSet, ISmoothing smoothing) {
		this.dataSet = dataSet;
		this.dataDimension = dataSet.iterator().next().getDimension();
		this.smoothing = smoothing;

		for (var data : dataSet) {
			if (data.getDimension() != this.dataDimension) {
				throw new IllegalArgumentException("data dimensions do not match");
			}
		}

		recalculate();
	}

	public void addData(Data data) {
		this.dataSet.add(data);
		recalculate();
	}

	private void recalculate() {
		calculateCounts();
		calculateJointCounts();
	}

	public String[] getYSet() {
		return dataSet.stream().map(Data::getY).collect(Collectors.toSet()).toArray(String[]::new);
	}

	public double getPriori(String y, boolean forceSmoothing) {
		var numerator = counts.get(dataDimension - 1).getOrDefault(y, 0);
		var denominator = dataSet.size();

		return numerator == 0 || forceSmoothing ? smoothing.simpleSmoothing(numerator, denominator, denominator) : (double) numerator / denominator;
	}

	public double getPosteriori(String y, int i, String attr, boolean forceSmoothing) {
		var numerator = jointCounts.get(y).get(i).getOrDefault(attr, 0);
		var denominator = counts.get(dataDimension - 1).getOrDefault(y, 0);

		return numerator == 0 || forceSmoothing ? smoothing.simpleSmoothing(numerator, denominator, counts.get(i).size()) : (double) numerator / denominator;
	}

	private void calculateCounts() {
		this.counts = new ArrayList<>();
		for (int i = 0; i < dataDimension; i++) {
			this.counts.add(new HashMap<>());
		}
		for (Data d : dataSet) {
			for (int i = 0; i < dataDimension; i++) {
				var attrMap = this.counts.get(i);
				attrMap.put(d.getAttribute(i), 1 + attrMap.getOrDefault(d.getAttribute(i), 0));
			}
		}
	}

	private void calculateJointCounts() {
		this.jointCounts = new HashMap<>();
		for (String y : getYSet()) {
			var array = new ArrayList<Map<String, Integer>>();
			for (int i = 0; i < dataDimension - 1; i++) {
				array.add(new HashMap<>());
			}
			this.jointCounts.put(y, array);
		}

		for (String y : getYSet()) {
			for (int i = 0; i < dataDimension - 1; i++) {
				var attrMap = this.jointCounts.get(y).get(i);

				for (Data d : dataSet) {
					if (!d.getY().equals(y)) continue;
					attrMap.put(d.getAttribute(i), 1 + attrMap.getOrDefault(d.getAttribute(i), 0));
				}
			}
		}
	}
}
