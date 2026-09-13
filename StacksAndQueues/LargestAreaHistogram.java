package StacksAndQueues;

import java.util.Scanner;
import java.util.Stack;

/*
INTUTION : 
    Think of each bar in the histogram as a building. 
    If a building wants to expand its roof sideways to make a big rectangle, 
    it can stretch left and right OVER other buildings, BUT only if those buildings are
    as tall or taller. The moment it hits a shorter building on either side, it gets blocked.
    So for every bar, we just need to find 2 things - nextSmallerBar on the left & 
    nextSmallerBar on the right.
    The space between these two blocking bars is the total width the current bar can use.
    Stack can be used for finding the blocking bars on either side in O(1).

APPROACH:
    1. Right Boundaries (Next Smaller Element on Right):
       - Traverse left to right while storing array indices in a stack.
       - Whenever the current element is strictly smaller than the element at the top 
         index of the stack, the current index becomes the right boundary for that 
         popped index.
       - Any indices remaining in the stack after the loop ends have no smaller 
         element to their right, so their right boundary defaults to the array length.

    2. Left Boundaries (Previous Smaller Element on Left):
       - Traverse left to right using a separate stack.
       - For the current element, pop all indices from the stack whose values are 
         greater than or equal to it. 
         Use of equality sign ensures duplicate heights are handled cleanly without 
         missing the maximum area.
       - After popping, if the stack is not empty, the top index in the stack is the 
         left boundary.
         If the stack becomes empty, the left boundary defaults to minus one.

    3. Calculating Max Area:
       - The width for each bar is calculated as: 
         (right boundary index) - (left boundary index) - 1.
       - The area is calculated as the height of the bar multiplied by the width.
         (cast to long to prevent overflow)
       - Track and update the overall maximum area across all bars.

    4. arr.length acts as default rightBoundary & -1 acts as default leftBoundary.
       We set these to act as virtual boundaries meaning that the width can pass the 
       very first and the very last valid bars.
*/
public class LargestAreaHistogram {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        // Taking Input
        int n = s.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        long largestArea = calculateLargestAreainHistogram(arr);
        System.out.println("Largest area that can be obtained is : " + largestArea);
    }

    public static long calculateLargestAreainHistogram(int[] arr) {

        int[] rightBoundary = new int[arr.length]; // nextSmallerElement index on the right
        findRightBoundariesForAllElements(rightBoundary, arr);

        int[] leftBoundary = new int[arr.length]; // nextSmallerElement index on the left
        findLeftBoundariesForAllElements(leftBoundary, arr);

        long maxArea = 0;
        for(int i = 0; i < arr.length; i++) {
            long width = rightBoundary[i] - leftBoundary[i] - 1;
            long area = arr[i] * width;
            if(area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }

    public static void findRightBoundariesForAllElements(int[] rightBoundary, int[] arr) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[i] < arr[st.peek()]) {
                int indexToBePopped = st.peek();
                rightBoundary[indexToBePopped] = i;
                st.pop();
            }

            st.push(i);
        }

        // Elements remaining in the stack do not have a defined rightBoundary. Their rightBoundary spans till the end.
        while(st.size() > 0) {
            int indexToBePopped = st.peek();
            rightBoundary[indexToBePopped] = arr.length;
            st.pop();
        }
    } 

    public static void findLeftBoundariesForAllElements(int[] leftBoundary, int[] arr) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[i] <= arr[st.peek()]) {
                st.pop();
            }

            if(st.size() == 0) { // leftBoundary spans till the very beginning
                leftBoundary[i] = -1;
            } else { // top index kept in the stack becomes the leftBoundary
                leftBoundary[i] = st.peek();
            }

            st.push(i);
        }
    }
}
