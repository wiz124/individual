import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class astack {
    public static Queue first(){
        Queue<Integer> first = new LinkedList<>();
        first.add(1);
        first.add(2);
        first.add(3);
        return first;
    }
    public static void reverse(Queue<Integer> a){
       int n=a.size();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0; i<n; i++){
            int curr = a.poll();
            stack.push(curr);
        }
        for (int i = 0; i < n; i++) {
            int curr = stack.pop();
            a.add(curr);
        }
        System.out.print("Reversed Queue: ");
        for (Integer i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("Original queue: " +first().toString());
        astack.reverse(first());
    }
}