package data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DataSet {
	// Y
	private Map<String, Double> priori;
	// Xn|Y
	private Map<String, Map<Integer, Map<String, Double>>> posteriori;

	private HashSet<Data> data;

	public DataSet() {
		this.priori = new HashMap<>();
		this.posteriori = new HashMap<>();
		this.data = new HashSet<>();
	}

	public void addData(Data data) {
		this.data.add(data);
		calculatePriori();
		calculatePosteriori();
	}

	private void calculatePosteriori() {
		this.posteriori.clear();

		for (Data d : data) {
			if (!this.posteriori.containsKey(d.getY())) {
				this.posteriori.put(d.getY(), new HashMap<>());
				for (int i = 0; i < d.getDimension() - 1; i++) {
					this.posteriori.get(d.getY())
							.computeIfAbsent(i, k -> new HashMap<>());
				}
			}
		}

		for (Data d : data) {
			for (int i = 0; i < d.getDimension() - 1; i++) {
				Map<String, Double> featureMap = this.posteriori.get(d.getY()).get(i);
				featureMap.put(d.getAttribute(i), featureMap.getOrDefault(d.getAttribute(i), 0d) + 1);
			}
		}

		for (String y : this.posteriori.keySet()) {
			double classCount = this.priori.get(y) * data.size();
			for (int i : this.posteriori.get(y).keySet()) {
				for (String value : this.posteriori.get(y).get(i).keySet()) {
					this.posteriori.get(y).get(i).put(value,
							this.posteriori.get(y).get(i).get(value) / classCount);
				}
			}
		}
	}

	private void calculatePriori() {
		this.priori.clear();

		Map<String, Integer> yCount = new HashMap<>();

		for (Data data : this.data) {
			yCount.put(data.getY(), yCount.getOrDefault(data.getY(), 0) + 1);
		}

		for (String y : yCount.keySet()) {
			this.priori.put(y, (double) yCount.get(y) / this.data.size());
		}
	}
}
