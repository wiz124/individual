import java.util.ArrayList;

public class allsort {
    public void sort(ArrayList<Integer> array){

    }
}
class bubblesort extends allsort{
    public void sort(ArrayList<Integer> array){
//traverse array
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = array.size() - 1; j > i; j--) {
                //compare elements
                if (array.get(j - 1) > array.get(j)) {
                    //Swap
                    int tmp = array.get(j - 1);
                    array.set(j -1, array.get(j));
                    array.set(j, tmp);
                }
            }
        }

    }

}
class selectionsort extends allsort{
    public void sort(ArrayList<Integer> array){
        for (int i = 0; i < array.size(); i++) {
            // find position of smallest num between (i + 1)th element and last element
            int position = i;
            for (int j = i; j < array.size(); j++) {
                if (array.get(j) < array.get(position))
                    position = j;
            }
            // Swap min (smallest num) to current position on array
            int min = array.get(position);
            array.set(position, array.get(i));
            array.set(i, min);
        }
    }
}
class Insertionsort extends allsort{
    public void sort(ArrayList<Integer> array){
        //traverse array
        for (int j = 1; j < array.size(); j++) {
            int current = array.get(j);
            int i = j-1;
            //find place to insert
            while ((i > -1) && (array.get(i) > current)) {
                array.set(i+1,array.get(i));
                i--;
            }
            //insert
            array.set(i+1, current);
        }
    }
}
class mergesort extends allsort{
    private ArrayList<Integer> input;

    public void sort(ArrayList<Integer> array) {
        input=array;
        divide(0,array.size()-1);
    }

    //dividing arraylist and prepping for merge
    public void divide(int start, int end) {
        if (start < end && (end - start) >= 1) {
            int mid = (end + start) / 2;
            divide(start, mid);
            divide(mid + 1,end );
            merger(start, mid, end);
        }
    }
    public void merger(int start, int mid, int end){

        //arraylist that will the new sorted array
        ArrayList<Integer> mergedSortedArray = new ArrayList<>();

        int leftIndex = start;
        int rightIndex = mid+1;

        while(leftIndex<=mid && rightIndex<=end){
            if(input.get(leftIndex)<=input.get(rightIndex)){
                mergedSortedArray.add(input.get(leftIndex));
                leftIndex++;
            }else{
                mergedSortedArray.add(input.get(rightIndex));
                rightIndex++;
            }
        }

        //Either of below while loop will execute
        while(leftIndex<=mid){
            mergedSortedArray.add(input.get(leftIndex));
            leftIndex++;
        }

        while(rightIndex<=end){
            mergedSortedArray.add(input.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = start;


        while(i<mergedSortedArray.size()){
            input.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
}

