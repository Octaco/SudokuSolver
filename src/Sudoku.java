public class Sudoku {

    private static final int CHANGEABLE = 0;
    private static int count = 0;
    /**
     * This class represents a sudoku puzzle
     * It provides methods to solve and to print this puzzle
     *
     */

    private int[][] sudoku;

    public Sudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    /**
     * This method checks if the given number is in the given row
     *
     * @param row    row to be checked
     * @param number number to be checked
     * @return true if number is in the given row and false if not
     */

    private boolean containsInRow(int row, int number) {

        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == number) {
                return true;
            }
        }
        return false;

    }


    /**
     * This method checks if the given number is in the given column
     *
     * @param column row to be checked
     * @param number number to be checked
     * @return true if number is in the given column and false if not
     */

    private boolean containsInColumn(int column, int number) {

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][column] == number) {
                return true;
            }
        }
        return false;

    }

    /**
     * This method checks if the given number is in a certain 3x3 box
     *
     * @param row    row of box that should be checked
     * @param column column of box that should be checked
     * @param number the number that should be checked
     * @return true if the number is in the box, false if not
     */

    private boolean containsInBox(int row, int column, int number) {

        //divide 9x9 in 9 3x3 matrices and give them new indices
        //check each 3x3 for the number

        int row3 = row - row % 3;
        int column3 = column - column % 3;

        for (int i = row3; i < row3 + 3; i++) {
            for (int j = column3; j < column3 + 3; j++) {
                if (sudoku[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method solves the sudoku
     */

    public boolean solve() {


        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {

                if (sudoku[row][column] == CHANGEABLE) {

                    for (int number = 1; number <= 9; number++) {

                        if (isValid(row, column, number)) {
                            sudoku[row][column] = number;
                            if (solve()) {
                                return true;
                            } else {
                                sudoku[row][column] = CHANGEABLE;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method validates the number at the given indices in the matrix
     *
     * @param row
     * @param column
     * @param number
     * @return true if number already is in the row, the column or the box of given indices, false if not
     */

    private boolean isValid(int row, int column, int number) {
        return !(containsInRow(row, number) || containsInColumn(column, number) || containsInBox(row, column, number));
    }

    /**
     * This method prints out the sudoku puzzle in required format
     */

    public void print() {
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.sudoku[i][j]);
            }
            System.out.println();
        }


    }

    /**
     * This method converts the 9x9 matrix to a String format
     *
     * @return sudoku in String format
     */
    @Override
    public String toString() {
        String sudoku = "";

        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0) {
                sudoku += "----------------------------------\n";
            }

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    sudoku += " | ";
                }

                sudoku += " " + this.sudoku[i][j] + " ";
            }

            sudoku += "\n";
        }
        sudoku += "\n\n__________________________________________\n\n";

        return sudoku;
    }
}
