import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class PG5 {
    /*
     * Justin Kubicz - PG5 Problem 6—Dungeons and Kine
     * The general algorithm breakdown is to find the minimum first role for a
     * given
     * number of dice with a given number of sides and a given target, as read from
     * a file.
     * Then, decrement the number of dice, and subtract the first roll's value from
     * the target.
     * Then recurse, passing the updated target and number of dice up the call
     * stack.
     * Return 1 when either the final dice is found to able to achieve the remaining
     * target,
     * or, when the remaining target == the remaining number of dice. This adds to
     * the tally
     * of solutions. Return 0 when either the final is not found to be able to
     * achieve the remaining
     * target, or, when the target is more than the maximum value of the dice, or,
     * the target value
     * is less than the total number of dice, meaning you'd need to role some
     * negatives.
     */
    private static HashMap<String, BigInteger> mcHashMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        try {

            Scanner mcScanny = new Scanner(new File("./PG5.in")).useDelimiter("\r\n");
            List<String> lines = new ArrayList<String>();
            while (mcScanny.hasNext()) {
                lines.add(mcScanny.next());
            }
            mcScanny.close();
            if (lines.getLast().equals("0")) {
                start(lines);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void start(List<String> lines) throws IOException {
        FileWriter outy = new FileWriter("./PG5.out");
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(" ");
            if (parts.length == 3) {
                outy.append("Case " + i + ": " + "There are "
                        + howMany(Integer.parseInt(parts[2]), Integer.parseInt(parts[0]), Integer.parseInt(parts[1]))
                        + " ways to roll a " + parts[2] + "\r\n");
            }
        }
        outy.append("completed on: " + LocalDateTime.now());
        outy.close();
    }

    public static BigInteger howMany(int target, int numDice, int numSides) {
        String key = target + ":" + numDice + ":" + numSides;
        if (mcHashMap.get(key) != null) {// check hash
            return mcHashMap.get(key);
        }
        BigInteger ans = BigInteger.ZERO;
        if ((numDice == 1 && target <= numSides || target == numDice) && target > 0) {
            // if one dice left, and target is possible, we've found a combination that
            // works
            return BigInteger.ONE;
        }
        if ((numDice == 1 && target > numSides) || target > numDice * numSides || target < numDice) {
            // if one dice left, and target is not possible, or, target is greater than the
            // maximum value of the dice, there is not a combination that works
            return BigInteger.ZERO;
        }
        int firstRole = 1;
        while (target - (firstRole + (numDice - 1) * numSides) > 0) {
            // Grab minimum first Role
            firstRole++;
        }
        while (firstRole <= numSides) {
            // increment first roll until max possible value, adding found combinations
            // to the tally
            ans = ans.add(howMany(target - firstRole, numDice - 1, numSides));
            firstRole++;
        }
        mcHashMap.put(key, ans);// memoize
        return ans;

    }
}
