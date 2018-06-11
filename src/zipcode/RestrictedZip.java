package zipcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class RestrictedZip implements Comparable<RestrictedZip> {

    private int lower;
    private int upper;

    public RestrictedZip(final int lower, final int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public RestrictedZip() {
    }

    @Override
    public int compareTo(final RestrictedZip zipCode) {

        if (lower > zipCode.lower) {
            return 1;
        }
        else if (lower < zipCode.lower) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "[" + lower + "," + upper + "]";
    }

    private boolean isOverLap(final RestrictedZip previous, final RestrictedZip current) {

        return (current.lower - 1 <= previous.upper);

    }

    private RestrictedZip mergeOverLap(final RestrictedZip previous, final RestrictedZip current) {

        return new RestrictedZip(previous.lower, current.upper);

    }

    private RestrictedZip[] getSortedRestrictedZipRanges(final int[][] restrictedZipRanges) {

        final RestrictedZip[] restrictedZips = new RestrictedZip[restrictedZipRanges.length];

        for (int i = 0; i < restrictedZips.length; i++) {
            restrictedZips[i] = new RestrictedZip(restrictedZipRanges[i][0], restrictedZipRanges[i][1]);
        }

        Arrays.sort(restrictedZips);

        return restrictedZips;
    }

    public Deque<RestrictedZip> getMergedRestrictedZipRanges(final int[][] restrictedZipRanges) {

        final Deque<RestrictedZip> restrictedZipStack = new ArrayDeque<>();
        final RestrictedZip[] restrictedZips = getSortedRestrictedZipRanges(restrictedZipRanges);

        if (restrictedZips.length < 1) {
            return restrictedZipStack;
        }

        restrictedZipStack.push(restrictedZips[0]);

        for (int i = 1; i < restrictedZips.length; i++) {
            if (isOverLap(restrictedZipStack.peek(), restrictedZips[i])) {

                restrictedZipStack.push(mergeOverLap(restrictedZipStack.pop(), restrictedZips[i]));

            }
            else {
                restrictedZipStack.push(restrictedZips[i]);
            }

        }

        return restrictedZipStack;
    }

}