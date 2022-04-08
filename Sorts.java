
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Sorts {
    private final ArrayList<Integer> data = new ArrayList<>();
    private final Duration timeElapsed;

    public Sorts(int size, int choice) {
        Instant start = Instant.now();  // time capture -- start
        // build an array
        for (int i = 0; i < size; i++) {
            data.add((int)(Math.random() * (size+1)));
        }
        // use Inheritance and Polymorphism to replace data.sort with your own algorithm
       // data.sort(Comparator.naturalOrder());

        if(choice==1) {
            allsort bubble = new bubblesort();
            bubble.sort(data);
        }
        else if(choice==2){
            allsort selection = new selectionsort();
            selection.sort(data);
        }
        else if(choice==3){
            allsort insertion = new Insertionsort();
            insertion.sort(data);
        }
        else if(choice==4){
            allsort merge = new mergesort();
            merge.sort(data);
        }

        Instant end = Instant.now();    // time capture -- end
        this.timeElapsed = Duration.between(start, end);

    }


    public ArrayList<Integer> getData() {
        return data;
    }

    public int getTimeElapsed() {
        return timeElapsed.getNano();
    }


    public static void main(String[] args) {
        int sum=0, time=0, TIMES=12, SIZE=5000;

        Scanner scan = new Scanner(System.in);
        int a=1;

while(a!=0) {
    System.out.println("Choose a sort: 1,2,3,4 \n 0 to exit");
    a = scan.nextInt();
    if(a==0){
        break;
    }
    for (int i = 0; i < TIMES; i++) {
        Sorts s = new Sorts(SIZE, a);
        for (int j = 0; j < s.getData().size(); j++) {
            // To see data, uncomment next line
            //System.out.println(s.getData());
            sum += s.getData().get(j);
        }
        System.out.println("Average random: " + sum / ((i + 1) * SIZE));
        System.out.println("Nanoseconds: " + s.getTimeElapsed());
        time += s.getTimeElapsed();
    }

    System.out.println("Average random: " + sum / (TIMES * SIZE));
    System.out.println("Total Nanoseconds: " + time);
    System.out.println("Total Seconds: " + time / 1000000000.0);

}
        scan.close();
    }
}
