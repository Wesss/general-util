package org.wesss.general_utils.random;/* stats for later modification

  plus refers to ModulationPlus,
  dif refers to aprx. max difference between picking of 2 values over 1000 picks
  plus = dif - 4     1 <= p <= 3
  plus = 2dif - 11    3 <= p <= 10
  plus = 10^(dif/11)  10 <= p <= 1000

*/


import org.wesss.general_utils.math.Rational;

/**
 * A RandomSpread object generates a stream of random numbers from 0 to some
 * maximum count with bias towards numbers picked less often.
 */
public class RandomSpread {

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
     * TODO fix RandomSpread documentation formatting
     *
     * Creates a new RandomSpread object to return a stream of random integers from 0 to count,
     * in a controlled spread based on spreadStrength.
     * <p>
     * spreadStrength --> 0        == no enforced spread
     * spreadStrength --> inifity     == the total counts of each int returned will never surpass each other by 1
     *
     * @param count the upper exclusive bound of random integer generation
     * @param spreadStrength the strength of the spread "control" over the randomness
     *              <p> count must be at least 1, spreadStrength must be greater than 0
     */
    public RandomSpread(int count, double spreadStrength) {
        this(count, Rational.convertDouble(spreadStrength));
    }

    /**
     * @see org.wesss.general_utils.random.RandomSpread#RandomSpread(int, double)
     */
    public RandomSpread(int count, Rational spreadStrength) {
        if (spreadStrength.isNegative())
            throw new IllegalArgumentException("RandomSpread initialized with negative spreadStrength "
                    + spreadStrength);
        if (count < 1)
            throw new IllegalArgumentException("RandomSpread initialized to non-positive count "
                    + count + "arguments");

        marbleTypeCount = count;
        marbleBag = new MarbleBag(marbleTypeCount);

        //spreadStrength is interpreted as (modPlus/modBase)
        modPlus = spreadStrength.numer();
        modBase = spreadStrength.denom();

        for (int type = 0; type < marbleTypeCount; type++) {
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
            marbleBag.removeMarbles(chosenType, modPlus);
        } else {
            for (int type = 0; type < marbleTypeCount; type++) {
                if (type != chosenType) {
                    marbleBag.addMarbles(type, modPlus);
                }
            }
        }

        return chosenType;
    }
}
