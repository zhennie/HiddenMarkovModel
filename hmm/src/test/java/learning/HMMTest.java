package learning;
import me.ash.data_structure.Pair;
import me.ash.learning.HMM;
import me.ash.learning.prob.PredefinedProbabilityDistribution;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me.ash on 10/18/15.
 */
public class HMMTest {

    @Test
    public void testHMMOfGivenParameter() throws Exception {
        final String RANIY = "Rainy";
        final String SUNNY = "Sunny";

        final String WALK = "WALK";
        final String SHOP = "SHOP";
        final String CLEAN = "CLEAN";


        Map<String,Double> init = new HashMap<>();

        init.put(RANIY,0.6);
        init.put(SUNNY,0.4);

        Map<Pair<String,String>,Double> emit = new HashMap<>();

        emit.put(new Pair<>(WALK,RANIY),0.1);
        emit.put(new Pair<>(SHOP,RANIY),0.4);
        emit.put(new Pair<>(CLEAN,RANIY),0.3);

        emit.put(new Pair<>(WALK,SUNNY),0.6);
        emit.put(new Pair<>(SHOP,SUNNY),0.3);
        emit.put(new Pair<>(CLEAN,SUNNY),0.1);

        Map<Pair<String,String>,Double> trans = new HashMap<>();

        trans.put(new Pair<>(RANIY,RANIY),0.7);
        trans.put(new Pair<>(SUNNY,RANIY),0.3);


        trans.put(new Pair<>(RANIY,SUNNY),0.4);
        trans.put(new Pair<>(SUNNY,SUNNY),0.6);
        HMM<String, String> dummyHMM = new HMM<>(
                new PredefinedProbabilityDistribution<>(init),
                new PredefinedProbabilityDistribution<>(trans),
                new PredefinedProbabilityDistribution<>(emit),
                init.keySet()
        );

        org.junit.Assert.assertEquals(dummyHMM.initProbability(RANIY),0.6, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.initProbability(SUNNY),0.4, 0.001);

        org.junit.Assert.assertEquals(dummyHMM.emissionProbability(SHOP, RANIY),0.4, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.emissionProbability(CLEAN,RANIY),0.3, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.emissionProbability(WALK,RANIY),0.1, 0.001);

        org.junit.Assert.assertEquals(dummyHMM.emissionProbability(SHOP,SUNNY),0.3, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.emissionProbability(CLEAN,SUNNY),0.1, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.emissionProbability(WALK,SUNNY),0.6, 0.001);


        org.junit.Assert.assertEquals(dummyHMM.transitionProbability(RANIY, RANIY),0.7, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.transitionProbability(SUNNY, RANIY),0.3, 0.001);

        org.junit.Assert.assertEquals(dummyHMM.transitionProbability(RANIY, SUNNY),0.4, 0.001);
        org.junit.Assert.assertEquals(dummyHMM.transitionProbability(SUNNY, SUNNY),0.6, 0.001);



    }

}
