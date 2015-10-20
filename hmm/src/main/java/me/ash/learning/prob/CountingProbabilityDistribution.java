package me.ash.learning.prob;

import java.util.Map;
import java.util.Set;

/**
 * Created by me.ash on 10/19/15.
 */
public class CountingProbabilityDistribution<T> implements ProbabilityDistribution<T>{

    private Map<T,Integer> countingTable;
    private double sum;

    public CountingProbabilityDistribution(Map<T, Integer> countingTable) {
        this.countingTable = countingTable;
        this.sum = 0.0;
        for(T t:countingTable.keySet()){
            this.sum += countingTable.get(t);
        }
    }

    public Set<T> knownEvents(){
        return this.countingTable.keySet();
    }

    public double p(T t){
        if(this.countingTable.containsKey(t)){
            return  ((double)this.countingTable.get(t)) / this.sum;
        }else{
            return 0.0;
        }
    }
}
