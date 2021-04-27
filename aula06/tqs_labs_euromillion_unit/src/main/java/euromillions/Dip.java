package euromillions;

import java.security.SecureRandom;
import java.util.Objects;

import sets.SetOfNaturals;

import java.util.Random;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {

    // consts
    private static final int NUMBER_OF_NUMBERS = 5;
    private static final int NUMBER_OF_STARS = 2;
    private static final int MAX_NUMBER = 50;
    private static final int MAX_STAR = 10;

    private SetOfNaturals numbers;
    private SetOfNaturals starts;

    private static Random generator = new SecureRandom();

    public Dip() {
        numbers = new SetOfNaturals();
        starts = new SetOfNaturals();
    }

    // use of consts instead of "magic numbers"
    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();
        if (NUMBER_OF_NUMBERS == arrayOfNumbers.length && NUMBER_OF_STARS == arrayOfStarts.length) {
            numbers.add(arrayOfNumbers);
            starts.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }
    }

    public static Random getGenerator() {
        return generator;
    }

    public SetOfNaturals getNumbersColl() {
        return numbers;
    }

    public SetOfNaturals getStarsColl() {
        return starts;
    }

    // use of consts instead of "magic numbers"
    public static Dip generateRandomDip() {
        Random generator = getGenerator();
        Dip randomDip = new Dip();
        for (int i = 0; i < NUMBER_OF_NUMBERS; i++) {
            int candidate = generator.nextInt(MAX_NUMBER - 1) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
            }
        }
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            int candidate = generator.nextInt(MAX_STAR - 1) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.starts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        return Objects.equals(this.starts, other.starts);
    }


    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */

    // changed method
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            sb.append(String.format("%3d", star));
        }
        sb.append("]");
        return sb.toString();
    }
}
