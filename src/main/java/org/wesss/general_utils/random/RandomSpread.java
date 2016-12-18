package org.wesss.general_utils.random;/* stats for later modification

  plus refers to ModulationPlus,
  dif refers to aprx. max difference between picking of 2 values over 1000 picks
  plus = dif - 4     1 <= p <= 3
  plus = 2dif - 11    3 <= p <= 10
  plus = 10^(dif/11)  10 <= p <= 1000

*/


import org.wesss.general_utils.math.Rational;

import java.util.Random;

/**
 * A RandomSpread object generates a stream of random numbers from 0 to some
 * maximum count with bias towards numbers picked less often.
 */
public class RandomSpread {
    private static final boolean DEBUG = false;

    private static final Random random = new Random();

    /*
     * A RandomSpread object is thought of as a bag of marbles of different types,
     * where each type represents an integer. Picking a random number is analogous
     * to picking a random marble from this bag.
     *
     * Represented by:
     * marbleTypeCount: the number of different marbles types in our scope.
     * for (int i = 0; i < marbleTypeCount; i++)
     * marbles[i]: the number of marbles in the bag of type i
     * modBase: the minimum amount of (marbles per type) in the bag at any time
     * modPlus: the number of (marbles per type) added or removed from the bag
     * after each pick
     * <p>
     * Representation Invariant:
     * marbleType count >= 1;
     * for (int i = 0; i < marbleTypeCount; i++)
     * marble[i] >= modBase;
     * (marble[i] - modBase) % modPlus == 0;
     */


    private int marbleTypeCount;
    private int[] marbles;
    private int modPlus;
    private int modBase;

    /**
     * Creates a new RandomSpread object to return a stream of random integers from 0 to count,
     * in a controlled spread (Default mod of 1, see RandomSpread(int count, double mod))
     *
     * @param count the upper non-exclusive bound of random integer generation
     *              <p> count must be at least 1
     */
    public RandomSpread(int count) {
        this(count, 1);
    }

    /**
     * Creates a new RandomSpread object to return a stream of random integers from 0 to count,
     * in a controlled spread based on spreadStrength.
     * <p>
     * spreadStrength --> 0        == no enforced spread
     * spreadStrength --> inifity     == the total counts of each int returned will never surpass each other by 1
     *
     * @param count the upper exclusive bound of random integer generation
     * @param spreadStrength   a double that determine of the ratio of "control" over the randomness
     *              <p> count must be at least 1, spreadStrength must be greater than 0
     */
    public RandomSpread(int count, double spreadStrength) {
        if (spreadStrength <= 0)
            throw new IllegalArgumentException("RandomSpread initialized with modularity " + spreadStrength);
        if (count < 1)
            throw new IllegalArgumentException("RandomSpread initialized to count " + count
                    + "arguments");

        //spreadStrength is interpreted as (modPlus/modBase)
        marbleTypeCount = count;
        Rational aprx = Rational.convertDouble(spreadStrength);
        modPlus = aprx.numer();
        modBase = aprx.denom();

        marbles = new int[marbleTypeCount];
        for (int i = 0; i < marbleTypeCount; i++) {
            marbles[i] = modBase;
        }
    }

    /**
     * @return a random number from 0 to (initialized count), with bias
     * towards numbers less picked since initialization.
     */
    public int next() {
        int[] maxPerType = new int[marbleTypeCount];
        int[] minPerType = new int[marbleTypeCount];

        //setup marble bag
        int marbleCount = 0;
        for (int i = 0; i < marbleTypeCount; i++) {
            minPerType[i] = marbleCount;
            marbleCount = marbleCount + marbles[i];
            maxPerType[i] = marbleCount - 1;
        }

        //pick a marble
        int chosenMarble = random.nextInt(marbleCount);

        int chosenValue = -1; //does not exist
        for (int i = 0; chosenValue < 0; i++) {
            if (chosenMarble >= minPerType[i] && chosenMarble <= maxPerType[i]) {
                chosenValue = i;
            }
        }

        //modify chances
        if (marbles[chosenValue] > modBase) {
            marbles[chosenValue] = marbles[chosenValue] - modPlus;
        } else {
            for (int i = 0; i < marbleTypeCount; i++) {
                if (i != chosenValue) {
                    marbles[i] = marbles[i] + modPlus;
                }
            }
        }

        return chosenValue;
    }
}
