package learning.inference;

import me.ash.data_structure.Pair;
import me.ash.learning.HMM;
import me.ash.learning.prob.PredefinedProbabilityDistribution;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.ash.learning.inference.Viterbi.inference;

/**
 * Created by me.ash on 10/18/15.
 */
public class ViterbiTest {


    @Test
    public void testViterbiInference() throws Exception {

        final String HEALTHY = "HEALTHY";
        final String FEVER = "FEVER";

        final String NORMAL = "NORMAL";
        final String COLD = "COLD";
        final String DIZZY = "DIZZY";


        Map<String, Double> init = new HashMap<>();

        init.put(HEALTHY, 0.6);
        init.put(FEVER, 0.4);

        Map<Pair<String, String>, Double> emit = new HashMap<>();

        emit.put(new Pair<>(NORMAL, HEALTHY), 0.5);
        emit.put(new Pair<>(COLD, HEALTHY), 0.4);
        emit.put(new Pair<>(DIZZY, HEALTHY), 0.1);

        emit.put(new Pair<>(NORMAL, FEVER), 0.1);
        emit.put(new Pair<>(COLD, FEVER), 0.3);
        emit.put(new Pair<>(DIZZY, FEVER), 0.6);

        Map<Pair<String, String>, Double> trans = new HashMap<>();

        trans.put(new Pair<>(HEALTHY, HEALTHY), 0.7);
        trans.put(new Pair<>(FEVER, HEALTHY), 0.3);


        trans.put(new Pair<>(HEALTHY, FEVER), 0.6);
        trans.put(new Pair<>(FEVER, FEVER), 0.4);

        HMM<String, String> dummyHMM = new HMM<>(
                new PredefinedProbabilityDistribution<>(init),
                new PredefinedProbabilityDistribution<>(trans),
                new PredefinedProbabilityDistribution<>(emit),
                init.keySet()
        );
        List<String> observations = new ArrayList<>();
        observations.add(NORMAL);
        observations.add(COLD);
        observations.add(DIZZY);


        List<String> answers = inference(dummyHMM, observations);
        String[] my_answer = new String[answers.size()];
        answers.toArray(my_answer);


        String[] gold_answer = {HEALTHY, HEALTHY, FEVER};


        org.junit.Assert.assertArrayEquals(gold_answer, my_answer);


    }
}
