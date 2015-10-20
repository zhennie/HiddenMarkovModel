package me.ash.learning.prob;

import java.util.Set;

/**
 * Created by me.ash on 10/19/15.
 */
public interface ProbabilityDistribution<T> {
    public Set<T> knownEvents();
    public double p(T t);
}
