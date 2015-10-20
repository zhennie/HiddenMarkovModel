package me.ash.learning;

import me.ash.data_structure.Pair;
import me.ash.learning.prob.ProbabilityDistribution;

import java.util.Set;

/**
 * Created by me.ash on 10/18/15.
 */
public class HMM<HIDDEN_STATE,OBSERVATION>{

    private ProbabilityDistribution<HIDDEN_STATE> initProbMap;
    private ProbabilityDistribution<Pair<HIDDEN_STATE,HIDDEN_STATE>> transProbMap;
    private ProbabilityDistribution<Pair<OBSERVATION,HIDDEN_STATE>> emitProbMap;
    private Set<HIDDEN_STATE> states;

    public HMM(ProbabilityDistribution<HIDDEN_STATE> initProbMap,
               ProbabilityDistribution<Pair<HIDDEN_STATE, HIDDEN_STATE>> transProbMap,
               ProbabilityDistribution<Pair<OBSERVATION, HIDDEN_STATE>> emitProbMap,
               Set<HIDDEN_STATE> states
               ) {
        this.initProbMap = initProbMap;
        this.transProbMap = transProbMap;
        this.emitProbMap = emitProbMap;
        this.states = states;
    }

    public Set<HIDDEN_STATE> getStates(){
        return this.states;
    }

    public double initProbability(HIDDEN_STATE given_state){
        return this.initProbMap.p(given_state);
    }
    public double emissionProbability(OBSERVATION output , HIDDEN_STATE given_state ){
        return this.emitProbMap.p(new Pair<>(output, given_state));
    }
    public double transitionProbability(HIDDEN_STATE state, HIDDEN_STATE previous){
        return this.transProbMap.p(new Pair<>(state, previous));
    }

}
