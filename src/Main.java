import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
       public static final boolean NICE_OUTPUT = true; // set to true for better output

    public static void main(String[] args) {

        try {

            FileReader fileReader = new FileReader("sudoku.txt");
//            FileReader fileReader = new FileReader("sudoku2.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int[][] sudoku = new int[9][9];
            boolean[][] changeable = new boolean[9][9];

            //read input

            for (int i = 0; i < 9; i++) {

                String input = bufferedReader.readLine();

                if (input.length() != 9) { // check for valid input
                    System.err.println("line " + (i + 1) + " is formatted wrong");
                    System.err.println("Each line needs length 9");
                    System.exit(-1);
                }

                for (int j = 0; j < 9; j++) {
                    char temp = input.charAt(j);

                    if (!(temp >= 48 && temp <= 57)) { // check for valid input
                        System.err.println("Number" + "(" + (i + 1) + ", " + (j + 1) + ") is not between 1 and 9");
                        System.err.println("input: 9x9 matrix with numbers between 1 and 9");
                        System.exit(-1);
                    }

                    // write given values in data structure
                    sudoku[i][j] = Integer.parseInt(String.valueOf(temp));

                    if (temp == 48) {
                        changeable[i][j] = true;
                    }

                }
            }

            Sudoku result = new Sudoku(sudoku);

            if(NICE_OUTPUT){
                System.out.println("This Sudoku Puzzle shall be solved: ");
                System.out.println(result);

                System.out.println("Here it is: \n\n");
                result.solve();   // solve sudoku
                System.out.println(result);
            } else {
                result.solve();   // solve sudoku
                result.print();
            }

        } catch (IOException e) {
            System.err.println("IOException:");
            System.err.println(e.getMessage());

        }
    }
}