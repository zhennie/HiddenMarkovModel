package me.ash.learning.prob;

import java.util.Map;
import java.util.Set;

/**
 * Created by me.ash on 10/19/15.
 */
public class PredefinedProbabilityDistribution<T> implements ProbabilityDistribution<T> {

    private Map<T, Double> pTable;

    public PredefinedProbabilityDistribution(Map<T, Double> pTable) {
        this.pTable = pTable;
    }

    public Set<T> knownEvents() {
        return this.pTable.keySet();
    }

    public double p(T t) {
        if (this.pTable.containsKey(t)) {
            return this.pTable.get(t);
        } else {
            return 0.0;
        }
    }
}
