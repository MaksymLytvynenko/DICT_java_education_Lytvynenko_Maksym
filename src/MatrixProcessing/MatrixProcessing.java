package MatrixProcessing;

import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    1. Add matrices
                    2. Multiply matrix by a constant
                    3. Multiply matrices
                    4. Transpose matrix
                    5. Calculate a determinant
                    6. Inverse matrix
                    0. Exit""");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter size of first matrix: ");
                    MatrixLogic matrix1 = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter first matrix:");
                    matrix1.fillMatrix(scanner);

                    System.out.print("Enter size of second matrix: ");
                    MatrixLogic matrix2 = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter second matrix:");
                    matrix2.fillMatrix(scanner);

                    MatrixLogic sumResult = matrix1.add(matrix2);
                    if (sumResult != null) {
                        System.out.println("The result is:");
                        sumResult.print();
                    }
                }
                case 2 -> {
                    System.out.print("Enter matrix size: ");
                    MatrixLogic matrix = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter matrix:");
                    matrix.fillMatrix(scanner);

                    System.out.print("Enter constant: ");
                    int constant = scanner.nextInt();

                    MatrixLogic scaledResult = matrix.multiplyByConstant(constant);
                    System.out.println("The result is:");
                    scaledResult.print();
                }
                case 3 -> {
                    System.out.print("Enter size of first matrix: ");
                    MatrixLogic mat1 = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter first matrix:");
                    mat1.fillMatrix(scanner);

                    System.out.print("Enter size of second matrix: ");
                    MatrixLogic mat2 = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter second matrix:");
                    mat2.fillMatrix(scanner);

                    MatrixLogic multipliedResult = mat1.multiply(mat2);
                    if (multipliedResult != null) {
                        System.out.println("The result is:");
                        multipliedResult.print();
                    }
                }
                case 4 -> {
                    System.out.println("""
                            
                            1. Main diagonal
                            2. Side diagonal
                            3. Vertical line
                            4. Horizontal line""");
                    System.out.print("Your choice: ");
                    int transposeChoice = scanner.nextInt();

                    System.out.print("Enter matrix size: ");
                    MatrixLogic transMatrix = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter matrix:");
                    transMatrix.fillMatrix(scanner);

                    MatrixLogic transposedResult = null;
                    switch (transposeChoice) {
                        case 1 -> transposedResult = transMatrix.transposeMainDiagonal();
                        case 2 -> transposedResult = transMatrix.transposeSideDiagonal();
                        case 3 -> transposedResult = transMatrix.transposeVertical();
                        case 4 -> transposedResult = transMatrix.transposeHorizontal();
                        default -> System.out.println("ERROR");
                    }

                    if (transposedResult != null) {
                        System.out.println("The result is:");
                        transposedResult.print();
                    }
                }
                case 5 -> {
                    System.out.print("Enter matrix size: ");
                    MatrixLogic matrixForDeterminant = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter matrix:");
                    matrixForDeterminant.fillMatrix(scanner);

                    try {
                        double determinant = matrixForDeterminant.calculateDeterminant();
                        System.out.println("The result is:\n" + determinant);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 6 -> {
                    System.out.print("Enter matrix size: ");
                    MatrixLogic matrixForInverse = new MatrixLogic(scanner.nextInt(), scanner.nextInt());
                    System.out.println("Enter matrix:");
                    matrixForInverse.fillMatrix(scanner);

                    MatrixLogic inverseResult = matrixForInverse.inverse();
                    if (inverseResult != null) {
                        System.out.println("The result is:");
                        inverseResult.print();
                    }
                }

                case 0 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("ERROR");
            }
        }
    }
}
