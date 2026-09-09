package StacksAndQueues;

import java.util.Scanner;
import java.util.Stack;

/*
INTUITION:
    Stock Span is closely related to the "Next Greater Element to the Left" problem.
    For every element, we need the nearest previous greater element to the left; that element acts as the boundary,
    and the distance between its index and the current index gives the stock span.

APPROACH:
    Although Stock Span is related to nextGreaterElement(NGE) to the Left, its implementation follows the same pattern as
    nextGreaterElement to the Right.
    
    We can use the Approach 2 nextGreaterElement to the Right as that works with indices.
    Which means we traverse from left to right, store indices in the stack, and pop
    smaller/equal elements when the current element is greater than or equal to them.

    The difference is in how we resolve the answer:
    In NGE to the Right, we resolve the answer for an element at the time we pop it, using the current element.
    In Stock Span (which is based on NGE to the left algorithm), we only use popping to remove useless smaller/equal elements. 
    After popping, the stack's top
    is the nearest previous greater element (i.e NGE to the left), whose index can be used to calculate the span for the current element.

    If the stack becomes empty, there is no previous greater element, so the span is the entire range from
    index 0 to the current index, i.e. i + 1.
*/
public class StockSpan {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        // Taking input array
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }

        int[] span = stockSpan(arr);

        // Printing Stock Span
        for(int i = 0; i < n; i++) {
            System.out.println("Stock span of " + arr[i] + " -> " + span[i]);
        }
    }

    public static int[] stockSpan(int[] arr) {
        int[] span = new int[arr.length];
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < arr.length; i++) {
            while(st.size() > 0 && arr[i] >= arr[st.peek()] ) {
                st.pop();
            }

            if(st.size() == 0) span[i] = i + 1;
            else span[i] = i - st.peek();

            st.push(i);
        }
        return span;
    }
    
}
