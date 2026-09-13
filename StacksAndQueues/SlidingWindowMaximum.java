package StacksAndQueues;

import java.util.Scanner;
import java.util.Stack;


/*
INTUITION :
    Brute force for sliding window maximum checks all k elements in every window,
    giving O(n*k) time — which becomes O(n^2) when k is large (e.g., k ~ n/2).
    The repeated work happens because we re-scan elements we've already compared
    in the previous window, even though most of them haven't changed.

    Key insight: within any window, the maximum element is the first element
    (starting from the window's left boundary) whose "next greater element" lies
    OUTSIDE the window. If an element's next greater element is still inside the
    window, that next greater element is automatically a better candidate for the
    max, so we can safely skip straight to it instead of comparing every element
    in between. 

APPROACH :
    1. Precompute the next greater element index for every position — for each
       index, store the index of the next strictly greater element to its right
       (or the end of the array if none exists).

    2. Maintain a pointer for the current window's max index. For each window
       starting at the left boundary:
        - If the pointer has fallen behind the window's left boundary (meaning
          the previous max just slid out of the window), reset the pointer to
          the window's left boundary.
        - While the pointer's next-greater-element index still falls within the
          current window, jump the pointer forward to that next-greater-element
          index — since a strictly greater element exists inside the window, it
          must be a better candidate for the max.
        - Once the pointer's next-greater-element index falls outside the
          window, the element at the pointer is the window's max, since nothing
          greater than it exists within the window bounds.

    3. Slide the window forward by one position and repeat, reusing the pointer
       from where it left off instead of restarting the chain from scratch each
       time.

COMPLEXITY :
    Building nge[] is O(n).
    For the window scan, 'j' only ever moves forward, never backward.
    Across the entire outer loop, total forward movement of 'j' is bounded by 'n'
    (not 'n' per each value of 'i')
    Hence, in worst case, in case of a strictly decreasing array, 'j' will move forward with 'i' 
    (making the total complexity as O(2N) which is still O(N) complexity).

    Therefore, even with the nested while loop inside, the overall time complexity of 
    this algo remains O(N).
*/
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Taking Input
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        int k = s.nextInt();

        int[] slidingWindowMaxArray = findSlidingWindowMax(arr, k);

        // Printing the resultant array
        for(int i = 0; i < slidingWindowMaxArray.length; i++) {
            System.out.print(slidingWindowMaxArray[i] + " ");
        }
    }

    public static int[] findSlidingWindowMax(int[] arr, int k) {
        int[] slidingWindowMax = new int[arr.length - k + 1];

        // Storing nextGreaterElement (NGE) to the right for every element
        int[] nge = new int[arr.length];
        fillNextGreaterElementOnTheRight(nge, arr);


        int j = 0;
        // The last window that needs evaluation would start from (arr.length - k) 
        // & hence, reaching there would mean that we don't need to slide further for the next window. 
        // Hence, loop goes till there only.
        for(int i = 0; i <= arr.length - k; i++) {   

            // Enter the loop to find the maximum of window starting from i
            
            if(j < i) { // 'j' only falls behind 'i' when the previous window's max was exactly at index 'i-1'. Since, that element would be now out of the window, we must restart the chain from the start of the new window boundary (i.e 'i')
                j = i;
            }

            while(nge[j] < i + k) {
                j = nge[j];
            }

            slidingWindowMax[i] = arr[j];
        }

        return slidingWindowMax;
    }

    public static void fillNextGreaterElementOnTheRight(int[] nge, int[] arr) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[i] > arr[st.peek()]) {
                int indexToBePopped = st.peek();
                nge[indexToBePopped] = i;
                st.pop();
            }

            st.push(i);
        }

        while(st.size() > 0) {
            int indexToBePopped = st.peek();
            nge[indexToBePopped] = arr.length;
            st.pop();
        }
    }


    
}
