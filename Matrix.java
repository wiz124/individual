
public class Matrix {
    private final int[][] matrix;

    // store matrix
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    // Hack: create toString method using nested for loops to format output of a matrix

    public String toString(){

        //traverse matrix
        for(int i = 0; i<matrix.length; i++){
            for(int j =0; j<matrix[i].length; j++){
                if(matrix[i][j]>=0) {
                    System.out.print(Integer.toHexString(matrix[i][j]));
                    System.out.print(" ");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println(" ");
        }
System.out.println("");

        //traverse matrix from other side
        for (int i =matrix.length-1; i>=0; i--){
            for(int j=matrix[i].length-1; j>=0; j--){
                if(matrix[i][j]>=0) {
                    System.out.print(Integer.toHexString(matrix[i][j]));
                    System.out.print(" ");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println(" ");
        }
        return "";
    }

    // declare and initialize a matrix for a keypad
   public static int[][] keypad() {
        return new int[][]{ { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, {-1, 0, -1} };
    }

    // declare and initialize a random length arrays
   public static int[][] numbers() {
        return new int[][]{ { 0, 1 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };
    }

}
