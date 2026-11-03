import java.util.Arrays;

/**
 * LEETCODE TOP 150 STUDY PLAN
 * Problem 26: Remove Duplicates from Sorted Array (Easy)
 * 
 * ============================================================================
 * SOLUTIONS COMPARISON TABLE:
 * ============================================================================
 * Approach      | Time Complexity   | Space Complexity | Strategy
 * ----------------------------------------------------------------------------
 * 1. Real Brute | O(N^2)            | O(1)             | Element Shifting Loop
 * 2. Better     | O(N)              | O(N)             | Extra Array Tracking
 * 3. Optimal    | O(N)              | O(1)             | Two Pointers (Unique Write)
 * ============================================================================
 */
class Top150_026_RemoveDuplicatesFromSortedArray {

    /**
     * 1. REAL BRUTE FORCE APPROACH
     * Strategy: Scan the array from front to back. Whenever we encounter a duplicate
     * (nums[i] == nums[i-1]), we use a nested loop to shift all subsequent elements 
     * one index to the left to overwrite it. We then re-verify the same position.
     * 
     * Pros: Operates entirely in-place without scaling memory footprint.
     * Cons: Highly inefficient O(N^2) runtime due to repeating downstream element shifts.
     */
    public int removeDuplicatesRealBrute(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int length = nums.length;
        int i = 1;
        
        while (i < length) {
            // Check if current element is a duplicate of the previous one
            if (nums[i] == nums[i - 1]) {
                // Shift all subsequent elements one position left
                for (int j = i; j < length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // Reduce the logical boundaries of the array
                length--;
                // Do not advance 'i' because the newly shifted element must be analyzed
            } else {
                i++;
            }
        }
        
        return length;
    }

    /**
     * 2. BETTER APPROACH
     * Strategy: Utilize a temporary storage array of equal size. Iterate over the 
     * sorted collection and capture elements only when they change values relative to 
     * their predecessor. Finally, override the initial segments with these filtered values.
     * 
     * Pros: Simplistic, highly predictable linear element evaluation path.
     * Cons: Requires auxiliary memory allocation proportional to the input array size.
     */
    public int removeDuplicatesBetter(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        int k = 1;

        // Step 1: Collect unique values into the temporary buffer
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                temp[k] = nums[i];
                k++;
            }
        }

        // Step 2: Reinject the unique sequence back into the original container
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

        return k;
    }

    /**
     * 3. OPTIMAL APPROACH (Interview Expected)
     * Strategy: Fast/Slow Two Pointers. The slow write pointer tracks where the next
     * unique element belongs. The fast read pointer looks for value transitions. 
     * Because the input is sorted, duplicates are guaranteed to be contiguous.
     * 
     * Pros: True O(N) runtime execution coupled with highly optimized O(1) zero-allocation memory footprint.
     */
    public int removeDuplicatesOptimal(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int w = 1; // Slow write pointer (first element is always inherently unique)

        // Fast read pointer scans downstream transitions
        for (int r = 1; r < nums.length; r++) {
            // If the element changes, a new unique value has been discovered
            if (nums[r] != nums[r - 1]) {
                nums[w] = nums[r];
                w++;
            }
        }

        return w; // Returns the exact logical count of unique values preserved
    }
}
