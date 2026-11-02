import java.util.Arrays;

/**
 * LEETCODE TOP 150 STUDY PLAN
 * Problem 27: Remove Element (Easy)
 * 
 * ============================================================================
 * SOLUTIONS COMPARISON TABLE:
 * ============================================================================
 * Approach      | Time Complexity   | Space Complexity | Strategy
 * ----------------------------------------------------------------------------
 * 1. Real Brute | O(N^2)            | O(1)             | Element Shifting Loop
 * 2. Better     | O(N)              | O(N)             | Extra Array + Two Pointers
 * 3. Optimal    | O(N)              | O(1)             | Read/Write Two Pointers
 * ============================================================================
 */
class Top150_027_RemoveElement {

    /**
     * 1. REAL BRUTE FORCE APPROACH
     * Strategy: Scan the array from front to back. Every single time we hit a 
     * match for 'val', we run a nested loop to manually shift all subsequent 
     * elements one index to the left. We then re-check the same index.
     * 
     * Pros: Done in-place without any extra arrays or memory shortcuts.
     * Cons: Highly inefficient O(N^2) runtime due to repeating element shifts.
     */
    public int removeElementRealBrute(int[] nums, int val) {
        int length = nums.length;
        int i = 0;
        
        while (i < length) {
            if (nums[i] == val) {
                // Shift every element after 'i' one position to the left
                for (int j = i; j < length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // Reduce the logical size of the array
                length--;
                // Do not increment 'i' because the new shifted element needs to be checked
            } else {
                i++;
            }
        }
        
        return length;
    }

    /**
     * 2. BETTER APPROACH
     * Strategy: Create a temporary array of the exact same size. Use a read 
     * pointer to scan the original array, copying only the valid elements 
     * (elements != val) into the temporary array, then transfer them back.
     * 
     * Pros: Simple, linear processing path.
     * Cons: Requires auxiliary memory allocation proportional to the input array size.
     */
    public int removeElementBetter(int[] nums, int val) {
        int[] temp = new int[nums.length];
        int k = 0;

        // Step 1: Extract valid numbers into the temporary array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                temp[k] = nums[i];
                k++;
            }
        }

        // Step 2: Push valid elements back into the original array
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }

        return k;
    }

    /**
     * 3. OPTIMAL APPROACH (Interview Expected)
     * Strategy: Two Pointers (Read/Write Trackers). The write pointer tracks 
     * where the next valid element belongs. The read pointer iterates through 
     * the entire sequence. Valid items are shifted forward in a single pass.
     * 
     * Pros: True O(N) runtime execution coupled with highly optimized O(1) zero-allocation memory footprint.
     */
    public int removeElementOptimal(int[] nums, int val) {
        int w = 0; // Write pointer tracker

        // Scan the elements using the implicit read index
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != val) {
                nums[w] = nums[r];
                w++;
            }
        }

        return w; // The write index automatically tracks the total count of valid items
    }
}
