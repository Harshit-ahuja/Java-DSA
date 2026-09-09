package StacksAndQueues;

import java.util.Scanner;
import java.util.Stack;

/*
    INTUITION:
    For every element, we need the first greater element on its right, so instead of repeatedly searching to the right,
    use a stack to efficiently eliminate elements that can no longer be answers.


    APPROACH 1:
    Traverse from right to left.
    For each element, remove all stack elements that are smaller than or equal to the current element; the top is 
    then the next greater element.
    If the stack becomes empty, no greater element exists. Mark -1 as the answer for that element.
    Then push the current element for elements on its left to use. 


    APPROACH 2:
    Traverse from left to right.
    Keep indices of elements in the stack instead of the actual element.
    When the current element is greater than the element at the stack's top index, it becomes the answer 
    for the element at the stack's top index. Keep popping till it happens.
    Push the current index for future elements. Any indices left at the end have no greater element, 
    so their answer is -1.

    COMPLEXITY:
    For both approaches, time complexity will be O(n) because every element is pushed into the stack once 
    and popped at most once.
*/
public class NextGreaterElementToTheRight {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Taking input array
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        int[] nge_approach1 = nextGreaterElementToTheRight_Approach1(arr);
        int[] nge_approach2 = nextGreaterElementToTheRight_Approach2(arr);

        // Printing Next Greater Element To The Right Using Approach 1
        System.out.println("NGE to the right using approach 1 : \n");
        for(int i = 0; i < n; i++) {
            System.out.println("Next greater element to the right of " + arr[i] + " -> " + nge_approach1[i]);
        }

        System.out.println("\n\n");

        // Printing Next Greater Element To The Right Using Approach 2
         System.out.println("NGE to the right using approach 2 : \n");
        for(int i = 0; i < n; i++) {
            System.out.println("Next greater element to the right of " + arr[i] + " -> " + nge_approach2[i]);
        }


    }
    
    // Approach 1
    public static int[] nextGreaterElementToTheRight_Approach1(int[] arr) {

        int[] nge = new int[arr.length];

        Stack<Integer> st = new Stack<>();
        for(int i = arr.length - 1; i >=0; i--) {
            while(st.size() > 0 && st.peek() <= arr[i]) {
                st.pop();
            }

            if(st.size() == 0) {
                nge[i] = -1;
            } else {
                nge[i] = st.peek();
            }

            st.push(arr[i]);
        }

        return nge;
    }

    // Approach 2
    public static int[] nextGreaterElementToTheRight_Approach2(int[] arr) {
        int[] nge = new int[arr.length];

        Stack<Integer> st = new Stack<>();

        st.push(0); // Pushing the first index in stack

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] < arr[i] ) {
                int positionToBePopped = st.peek();
                nge[positionToBePopped] = arr[i];
                st.pop();
            }

            st.push(i);
        }

        while(st.size() > 0) {
            int positionToBePopped = st.peek();
            nge[positionToBePopped] = -1;
            st.pop();
        }

        return nge;
    }
}


