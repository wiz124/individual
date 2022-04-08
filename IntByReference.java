
public class IntByReference {
    private int value;

    public IntByReference(int a){
        value =a;

    }
    public void swap(IntByReference a){
        int value = this.value;
        this.value=a.value;
        a.value=value;
    }
    public String toString(){
        return Integer.toString(value);
    }
    // Hack: create IntByReference, swapToLowHighOrder and toString methods

    // static method that enables me to see numbers swapped by reference (before, after)
    public static void swapper(int n0, int n1) {
        IntByReference a = new IntByReference(n0);
        IntByReference b = new IntByReference(n1);
        System.out.println("Before: " + a + " " + b);
        System.out.println("Swapped!");
        a.swap(b);  // conditionally build swap method to change values of a, b
        System.out.println("After: " + a + " " + b);
        System.out.println();
    }

}
