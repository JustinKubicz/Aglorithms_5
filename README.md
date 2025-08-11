Justin Kubicz - PG5 Problem 6—Dungeons and Kine
The general algorithm breakdown is to find the minimum first role for a
given number of dice with a given number of sides and a given target, as read from
a file. Then, decrement the number of dice, and subtract the first roll's value from 
the target. Then recurse, passing the updated target and number of dice up the call stack. Return 1 when either the final dice is found to be able to achieve the remaining target, or when the remaining target == the remaining number of dice. This adds to the tally of solutions. Return 0 when either the final is not found to be able to achieve the remaining target, or when the target is more than the maximum value of the dice, or, the target value is less than the total number of dice, meaning you'd need to roll some negatives.
