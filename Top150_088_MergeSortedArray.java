import java.util.Arrays;

/**
 * LEETCODE TOP 150 STUDY PLAN
 * Problem 88: Merge Sorted Array (Easy)
 * 
 * ============================================================================
 * SOLUTIONS COMPARISON TABLE:
 * ============================================================================
 * Approach      | Time Complexity   | Space Complexity | Strategy
 * ----------------------------------------------------------------------------
 * 1. Brute Force| O((m+n) log(m+n)) | O(1)             | Built-in Sort (Arrays.sort)
 * 2. Better     | O(m + n)          | O(m)             | Extra Array + Two Pointers
 * 3. Optimal    | O(m + n)          | O(1)             | Three Pointers (Back-to-Front)
 * ============================================================================
 */
class Top150_088_MergeSortedArray {

    /**
     * 1. BRUTE FORCE APPROACH
     * Strategy: Copy all elements of nums2 into the tail end of nums1, 
     * then use Java's built-in quicksort algorithm to sort the entire array.
     * 
     * Pros: Extremely simple to write (2 lines of code).
     * Cons: Inefficient for large inputs due to sorting overhead.
     */
    public void mergeBruteForce(int[] nums1, int m, int[] nums2, int n) {
        // Copy nums2 directly into the empty space at the back of nums1
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        // Sort the entire combined array
        Arrays.sort(nums1);
    }

    /**
     * 2. BETTER APPROACH
     * Strategy: Create a temporary copy of the valid elements in nums1. 
     * Use two pointers starting from index 0 to compare elements from the copy 
     * and nums2, writing the smaller element back into nums1 from front-to-back.
     * 
     * Pros: Linear time complexity O(m + n).
     * Cons: Requires extra auxiliary memory space to duplicate nums1 elements.
     */
    public void mergeBetter(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of the first 'm' elements of nums1
        int[] nums1Copy = new int[m];
        for (int i = 0; i < m; i++) {
            nums1Copy[i] = nums1[i];
        }

        int p1 = 0; // Pointer for nums1Copy
        int p2 = 0; // Pointer for nums2
        int w = 0;  // Write pointer for the original nums1 array

        // Move through both arrays and pick the smaller element
        while (p1 < m && p2 < n) {
            if (nums1Copy[p1] <= nums2[p2]) {
                nums1[w] = nums1Copy[p1];
                p1++;
            } else {
                nums1[w] = nums2[p2];
                p2++;
            }
            w++;
        }

        // If elements are remaining in nums1Copy, copy them over
        while (p1 < m) {
            nums1[w] = nums1Copy[p1];
            p1++;
            w++;
        }

        // If elements are remaining in nums2, copy them over
        while (p2 < n) {
            nums1[w] = nums2[p2];
            p2++;
            w++;
        }
    }

    /**
     * 3. OPTIMAL APPROACH (Interview Expected)
     * Strategy: Three Pointers (Back-to-Front). Start comparing elements from 
     * the largest numbers at the back, and fill nums1 from the rightmost empty 
     * slots working backward. 
     * 
     * Pros: O(m + n) speed and O(1) memory. No extra space or element shifting needed.
     */
    public void mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // Last valid element in nums1
        int p2 = n - 1; // Last element in nums2
        int w = m + n - 1; // Absolute end slot of nums1

        // Compare from the back and place the larger value at the write pointer
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[w] = nums1[p1];
                p1--;
            } else {
                nums1[w] = nums2[p2];
                p2--;
            }
            w--;
        }

        // Guardrail: If nums1 runs out first, copy the remaining values from nums2
        while (p2 >= 0) {
            nums1[w] = nums2[p2];
            p2--;
            w--;
        }
    }
}
