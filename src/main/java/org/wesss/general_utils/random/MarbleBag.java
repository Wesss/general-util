package org.wesss.general_utils.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MarbleBag {

    // for i = 0 to marbleTypes - 1
    //      typeToCount.get(i) must exist and be non-negative
    private Map<Integer, Integer> typeToCount;

    private Random random;

    /**
     *
     * @param marbleTypes the number of different marbles that can be added to the bag
     */
    public MarbleBag(int marbleTypes) {
        if (marbleTypes <= 0) {
            throw new IllegalArgumentException("Marble bag must have at least 1 type of marble");
        }

        typeToCount = new HashMap<>();
        for (int i = 0; i < marbleTypes; i++) {
            typeToCount.put(i, 0);
        }

        random = new Random();
    }

    /**
     * @return the total amount of marbles in the bag
     */
    public int size() {
        int sum = 0;
        for (int i : typeToCount.values()) {
            sum += i;
        }
        return sum;
    }

    /**
     * Adds a marble of the given type to the bag
     * @param type the type of marble to add
     */
    public void addMarble(int type) {
        addMarbles(type, 1);
    }

    /**
     * Adds marbles of the given type to the bag
     * @param type the type of marble to add
     * @param count the amount of marbles to add
     */
    public void addMarbles(int type, int count) {
        validateTypeExists(type);

        typeToCount.put(type, typeToCount.get(type) + count);
    }

    /**
     * @param type the type of marbles to count
     * @return the amount of marbles of the given type currently in the bag
     */
    public int getMarbleCount(int type) {
        validateTypeExists(type);

        return typeToCount.get(type);
    }

    /**
     * Removes a marble of the given type from the bag.
     * If no marbles of given type is present, nothing is changed
     * @param type the marble type to remove
     */
    public void removeMarble(int type) {
        removeMarbles(type, 1);
    }

    /**
     * Removes marbles of the given type from the bag.
     * If more marbles are removed than is present, all remaining marbles are removed
     * @param type the marble type to remove
     */
    public void removeMarbles(int type, int count) {
        validateTypeExists(type);

        int typeCount = typeToCount.get(type) - count;
        if (typeCount < 0) {
            typeToCount.put(type, 0);
        } else {
            typeToCount.put(type, typeCount);
        }
    }

    /**
     * Picks a marble from the bag randomly. The picked marble is not removed from the bag
     * @return the type of the picked marble
     */
    public int pickMarble() {
        int pickedMarble = random.nextInt(size());

        //we cycle through each type of marble until we land in the range of the picked marble type
        int curType = -1;
        while (pickedMarble > 0) {
            curType++;
            pickedMarble -= typeToCount.get(curType);
        }

        return curType;
    }

    private void validateTypeExists(int type) {
        if (!typeToCount.containsKey(type)) {
            throw new IllegalArgumentException("given marble type does not exist in this bag");
        }
    }
}
