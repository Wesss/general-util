package org.wesss.general_utils.math;

import java.util.*;

import static java.lang.Math.sqrt;

/**
 * The MathUtils class contains static methods for computing various functions
 */
public class MathUtils {
    /**
     * Returns the greatest common denominator between the given integers
     *
     * @param a
     * @param b
     * @return the largest positive integer that can divide both a and b into whole numbers
     */
    public static int gcd(int a, int b) {
        if (a < 0) {
            return gcd(-a, b);
        } else if (b < 0) {
            return gcd(a, -b);
        }

        if (a == 0)
            return b;

        return gcd(b % a, a);
    }

    /**
     * Returns the truncated length of the hypotenuse of a right triangle
     *
     * @param x the length of the triangle's base
     * @param y the length of the triangle's height
     * @return the truncated length of the triangle's hypotenuse
     */
    public static int hypotenuse(int x, int y) {
        return (int) sqrt((x * x) + (y * y));
    }

    /**
     * @param x
     * @param modulus
     * @return the value, y, in the range [0, |modulus|) such that (y mod modulus == x mod modulus)
     */
    public static int modPos(int x, int modulus) {
        if (modulus < 0) {
            return modPos(x, -modulus);
        }

        int result = x;

        while (result < 0) {
            result += modulus;
        }
        while (result >= modulus) {
            result -= modulus;
        }

        return result;
    }

    /**
     * Returns a bitSet such that every 'size' bits represents a single unique enumeration of bits of length 'size.
     * Every possible enumeration is returned, including the enumeration of all 0's.
     * enumerations are ordered such that every enumeration has at least the same amount of 1's as the enumeration
     * before it.
     * <p>
     * For instance, getEnumerations(2) will return the following:
     * [0, 0,
     *  1, 0,
     *  0, 1,
     *  1, 1]
     *  <p>
     *  getEnumerations(3) will return the following:
     * [0, 0, 0
     *  1, 0, 0
     *  0, 1, 0,
     *  0, 0, 1,
     *  1, 1, 0,
     *  1, 0, 1,
     *  0, 1, 1,
     *  1, 1, 1]
     */
    public static <E, V extends Collection<E>> Iterable<Set<E>> getEnumerations(V enumerate) {
        // TODO cleanup, rewrite javadoc, and extend getEnumerations
        ArrayList<E> enumerateList = new ArrayList<>();
        enumerateList.addAll(enumerate);
        BitSet bitSet = new BitSet();
        int size = enumerate.size();

        int numOfOnes = 0;
        int currentEnumerationNo = 0;
        BitSet currentBitEnumeration = new BitSet();
        currentBitEnumeration.set(0, size, false);
        while (numOfOnes < size + 1) {
            // add current enumeration to set
            for (int i = 0; i < size; i++) {
                bitSet.set(currentEnumerationNo * size + i, currentBitEnumeration.get(i));
            }
            currentEnumerationNo++;

            // generate next enumeration
            boolean isDoneEnumeratingCurNumOfOnes = true;
            for (int i = 0; i < numOfOnes; i++) {
                if (!currentBitEnumeration.get(size - 1 - i)) {
                    isDoneEnumeratingCurNumOfOnes = false;
                    break;
                }
            }

            if (isDoneEnumeratingCurNumOfOnes) {
                numOfOnes++;
                for (int i = 0; i < size; i++) {
                    currentBitEnumeration.set(i, i < numOfOnes);
                }
            } else {
                int numOnesEncountered = 0;
                for (int i = size - 1; i > 0; i--) {
                    boolean ithBit = currentBitEnumeration.get(i);
                    boolean iMinusOneBit = currentBitEnumeration.get(i - 1);
                    if (ithBit) {
                        numOnesEncountered++;
                    }
                    if (!ithBit && iMinusOneBit) {
                        currentBitEnumeration.set(i - 1, false);
                        currentBitEnumeration.set(i, i + numOnesEncountered + 1, true);
                        currentBitEnumeration.set(i + numOnesEncountered + 1, size, false);
                        break;
                    }
                }
            }
        }

        return () -> new Iterator<Set<E>>() {

            private int enumerationNo = 0;

            @Override
            public boolean hasNext() {
                return enumerationNo * size < bitSet.length();
            }

            @Override
            public Set<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Set<E> enumeration = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    if (bitSet.get((enumerationNo * size) + i)) {
                        enumeration.add(enumerateList.get(i));
                    }
                }

                enumerationNo++;

                return enumeration;
            }
        };
    }
}
