package algo_questions;


import java.util.Arrays;

/**
 * Solutions for the algo part of the exercise.
 */
public class Solutions {

    public Solutions() {
    }

    /**
     * Method computing the maximal amount of tasks out of n tasks that can be completed with m time slots.
     * A task can only be completed in a time slot if the length of the time slot is grater than the no.
     * of hours needed to complete the task.
     *
     * @param tasks     - array of integers of length n. tasks[i] is the time in hours required to complete task i.
     * @param timeSlots - array of integers of length m. timeSlots[i] is the length in hours of the slot i.
     * @return maximal amount of tasks that can be completed
     */
    public static int alotStudyTime(int[] tasks, int[] timeSlots) {
        int completedTasks = 0;
        Arrays.sort(timeSlots);
        Arrays.sort(tasks);
        int tasksLen = tasks.length;
        for (int i = timeSlots.length - 1; 0 <= i && completedTasks < tasks.length; i--) {
            for (int j = tasksLen - 1; 0 <= j; j--) {
                tasksLen--;
                if (tasks[j] <= timeSlots[i]) {
                    completedTasks++;
                    break;
                }
            }
        }
        return completedTasks;
    }

    /**
     * Method computing the nim amount of leaps a frog needs to jump across n waterlily leaves,
     * from leaf 1 to leaf n. The leaves vary in size and how stable they are, so some leaves allow larger
     * leaps than others. leapNum[i] is an integer telling you how many leaves ahead you can jump from leaf i.
     * If leapNum[3]=4, the frog can jump from leaf 3, and land on any of the leaves 4, 5, 6 or 7.
     *
     * @param leapNum - array of ints. leapNum[i] is how many leaves ahead you can jump from leaf i.
     * @return minimal no. of leaps to last leaf.
     */
    public static int minLeap(int[] leapNum) {
        int nextLeafIndex = 1;
        int leapCounter = 0;
        for (int i = 0; i < leapNum.length - 1; i = nextLeafIndex, leapCounter++) {
            int farthestLeafIndex = Integer.MIN_VALUE;
            if (i + leapNum[i] >= leapNum.length - 1) // if I can get to that last leaf from here:
                return ++leapCounter; // add one jump and return.
            for (int j = i + 1; j <= i + leapNum[i]; j++) {
                if (j + leapNum[j] > farthestLeafIndex) {
                    farthestLeafIndex = j + leapNum[j];
                    nextLeafIndex = j;
                }
            }
        }
        return leapCounter;
    }

    /**
     * Method computing the solution to the following problem: A boy is filling the water trough for his
     * father's cows in their village. The trough holds n liters of water. With every trip to the
     * village well, he can return using either the 2 bucket yoke, or simply with a single bucket.
     * A bucket holds 1 liter. In how many ways can he fill the water trough? n can be assumed
     * to be greater or equal to 0, less than or equal to 48.
     *
     * @param n - total liters in the trough.
     * @return valid output of algorithm.
     */
    public static int bucketWalk(int n) {
        if (n <= 1)
            return 1;
        int[] waysToFill = new int[n + 1];
        waysToFill[0] = 1;
        waysToFill[1] = 1;
        for (int i = 2; i < waysToFill.length; i++) {
            waysToFill[i] = waysToFill[i - 2] + waysToFill[i - 1];
        }
        return waysToFill[n];
    }

    /**
     * Method computing the solution to the following problem: Given an integer n, return the number of
     * structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
     * You can assume n is at least 1 and at most 19. (Definition: two trees S and T are structurally
     * distinct if one can not be obtained from the other by renaming of the nodes.) (credit: LeetCode)
     *
     * @param n given parameter.
     * @return valid output of algorithm.
     */
    public static int numTrees(int n) {
        long numOfBSTs = 1;
        for (int i = 2; i <= n; i++) {
            numOfBSTs *= (4L * i - 2);
            numOfBSTs /= (i + 1);
        }
        return (int) numOfBSTs;
    }
}