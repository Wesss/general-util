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
    private MarbleBag marbleBag;

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
        marbleBag = new MarbleBag(marbleTypeCount);

        Rational aprx = Rational.convertDouble(spreadStrength);
        modPlus = aprx.numer();
        modBase = aprx.denom();

        for (int type = 0; type < marbleTypeCount; type++) {
            // TODO marble bag remove multiple
            marbleBag.addMarbles(type, modBase);
        }
    }

    /**
     * @return the type of the marble picked
     */
    public int next() {
        //pick a marble
        int chosenType = marbleBag.pickMarble();

        //modify chances
        if (marbleBag.getMarbleCount(chosenType) > modBase) {
            marbleBag.removeMarbles(chosenType, modBase);
        } else {
            for (int type = 0; type < marbleTypeCount; type++) {
                if (type != chosenType) {
                    marbleBag.addMarbles(type, modBase);
                }
            }
        }

        return chosenType;
    }
}
