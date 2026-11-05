```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LEETCODE TOP 150 STUDY PLAN
 * Problem 169: Majority Element (Easy)
 *
 * ============================================================================
 * SOLUTIONS COMPARISON TABLE:
 * ============================================================================
 * Approach       | Time Complexity | Space Complexity | Strategy
 * ----------------------------------------------------------------------------
 * 1. Brute Force | O(n^2)          | O(1)             | Count Every Element
 * 2. Better      | O(n)            | O(n)             | HashMap Frequency Count
 * 3. Optimal     | O(n)            | O(1)             | Boyer-Moore Voting Algorithm
 * ============================================================================
 */
class Top150_169_MajorityElement {

    /**
     * 1. BRUTE FORCE APPROACH
     *
     * Strategy:
     * Pick each element and count how many times it appears.
     * If its count is greater than n / 2, return it.
     *
     * Pros: Very easy to understand.
     * Cons: Repeatedly scans the array, resulting in O(n^2) time.
     */
    public int majorityElementBruteForce(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int count = 0;

            for (int j = 0; j < nums.length; j++) {

                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            if (count > nums.length / 2) {
                return nums[i];
            }
        }

        return -1;
    }


    /**
     * 2. BETTER APPROACH
     *
     * Strategy:
     * Use a HashMap to store the frequency of each element.
     * If any element's frequency becomes greater than n / 2,
     * return that element.
     *
     * Pros: Only one traversal of the array.
     * Cons: Requires extra space for the HashMap.
     */
    public int majorityElementBetter(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            int count = map.getOrDefault(num, 0) + 1;

            map.put(num, count);

            if (count > nums.length / 2) {
                return num;
            }
        }

        return -1;
    }


    /**
     * 3. OPTIMAL APPROACH (Interview Expected)
     *
     * Strategy:
     * Boyer-Moore Voting Algorithm.
     *
     * Maintain:
     * candidate = Potential majority element.
     * count     = Voting balance.
     *
     * If count becomes 0, select the current element as candidate.
     * If the current element matches candidate, increase count.
     * Otherwise, decrease count.
     *
     * Since a majority element is guaranteed to exist,
     * the final candidate will be the majority element.
     *
     * Pros: O(n) time and O(1) extra space.
     */
    public int majorityElementOptimal(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {

            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
```
