package me.ash.learning.inference;

import me.ash.learning.HMM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by me.ash on 10/18/15.
 */
public class Viterbi {
    public static <H,O> List<H> inference(HMM<H,O> hmm,List<O> observations){

        Set<H> stateSet =  hmm.getStates();

        double[][] logProbabilityMatrix = new double[observations.size()][stateSet.size()];
        Object[][] backTrackingMatrix = new Object[observations.size()][stateSet.size()];
        int endingRow = -1;
        HashMap<H, Integer> stateRowCoordinate = new HashMap<>();

        // Fill in DP matrix.
        for (int i=0; i<observations.size(); i++){

            if (i==0) {
                int j = 0;
                for (H givenState : stateSet) {
                    double logProbability = Math.log(hmm.initProbability(givenState)) +
                            Math.log(hmm.emissionProbability(observations.get(i), givenState));

                    logProbabilityMatrix[i][j] = logProbability;

                    // Line below initializes state value of each cell in state matrix column zero as
                    // a steady state coordinates which can be retrieved for reference, and
                    // should never be counted towards backtracking.
                    backTrackingMatrix[i][j] = givenState;
                    stateRowCoordinate.put(givenState, j);
                    j++;
                }
            }
            else {

                for (int j=0; j<stateSet.size(); j++){
                    H bestPreviousState = null;
                    double maxProbability = Double.NEGATIVE_INFINITY;

                    // Refer to state matrix column zero as state coordinate for currently checking state.
                    H currentState = (H) backTrackingMatrix[0][j];

                    for (int jPre=0; jPre<stateSet.size(); jPre++){
                        H previousState = (H) backTrackingMatrix[i-1][jPre];

                        double logProbability = logProbabilityMatrix[i-1][jPre] +
                                Math.log(hmm.transitionProbability(currentState, previousState)) +
                                Math.log(hmm.emissionProbability(observations.get(i), currentState));

                        if (logProbability > maxProbability){
                            maxProbability = logProbability;
                            bestPreviousState = previousState;
                            endingRow = j;
                        }
                    }

                    logProbabilityMatrix[i][j] = maxProbability;
                    backTrackingMatrix[i][j] = bestPreviousState;
                }
            }

        }

        // Start backtracking to unpack the best path.
        ArrayList<H> bestSequence = new ArrayList<>(observations.size());
        for (int i = 0; i < observations.size(); i++) {
            bestSequence.add(null);
        }

        bestSequence.set(observations.size() - 1, (H) backTrackingMatrix[0][endingRow]);

        int bestRow = endingRow;
        for (int i=observations.size()-1; i>0; i--){
            bestSequence.set(i - 1, (H) backTrackingMatrix[i][bestRow]);
            bestRow = stateRowCoordinate.get((H) backTrackingMatrix[i][bestRow]);
        }

        return bestSequence;
    }
}
