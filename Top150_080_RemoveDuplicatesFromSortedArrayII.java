import java.util.Arrays;

/**
 * LEETCODE TOP 150 STUDY PLAN
 * Problem 80: Remove Duplicates from Sorted Array II (Medium)
 *
 * ============================================================================
 * SOLUTIONS COMPARISON TABLE:
 * ============================================================================
 * Approach      | Time Complexity | Space Complexity | Strategy
 * ----------------------------------------------------------------------------
 * 1. Real Brute  | O(N^2)          | O(1)             | Element Shifting
 * 2. Better      | O(N)            | O(N)             | Temporary Array
 * 3. Optimal     | O(N)            | O(1)             | Two Pointers
 * ============================================================================
 */
class Top150_080_RemoveDuplicatesFromSortedArrayII {

    /**
     * 1. REAL BRUTE FORCE APPROACH
     *
     * Strategy:
     * If an element appears more than twice consecutively,
     * remove the extra occurrence by shifting elements left.
     *
     * Pros: In-place modification.
     * Cons: Repeated shifting makes it O(N^2).
     */
    public int removeDuplicatesRealBrute(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        int length = nums.length;
        int i = 2;

        while (i < length) {

            // If current element equals the element two positions before,
            // it means this value has already appeared twice.
            if (nums[i] == nums[i - 2]) {

                // Shift remaining elements to the left
                for (int j = i; j < length - 1; j++) {
                    nums[j] = nums[j + 1];
                }

                length--;

                // Check the same index again
            } else {
                i++;
            }
        }

        return length;
    }


    /**
     * 2. BETTER APPROACH
     *
     * Strategy:
     * Use a temporary array to store elements.
     * Since the input is sorted, count consecutive occurrences.
     * Store only the first two occurrences of each value.
     *
     * Pros: Easy to understand.
     * Cons: Requires O(N) extra memory.
     */
    public int removeDuplicatesBetter(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        int[] temp = new int[nums.length];

        int k = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            // Check whether this is a new number
            if (i == 0 || nums[i] != nums[i - 1]) {
                count = 1;
            } else {
                count++;
            }

            // Store only the first two occurrences
            if (count <= 2) {
                temp[k] = nums[i];
                k++;
            }
        }

        // Copy valid elements back into original array
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

        return k;
    }


    /**
     * 3. OPTIMAL APPROACH (INTERVIEW EXPECTED)
     *
     * Strategy:
     * Use two pointers.
     *
     * 'w' = Write pointer
     * 'r' = Read pointer
     *
     * Since the array is sorted, compare the current element
     * with the element two positions behind the write pointer.
     *
     * If they are different, we can safely keep the element.
     *
     * Pros: O(N) time and O(1) extra space.
     */
    public int removeDuplicatesOptimal(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        int w = 2;

        for (int r = 2; r < nums.length; r++) {

            // Allow the current number if it differs from
            // the number two positions behind the write pointer
            if (nums[r] != nums[w - 2]) {

                nums[w] = nums[r];
                w++;
            }
        }

        return w;
    }
}
