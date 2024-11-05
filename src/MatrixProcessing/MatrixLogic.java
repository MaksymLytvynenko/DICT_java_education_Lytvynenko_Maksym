package MatrixProcessing;

public class MatrixLogic {
    private final double[][] data;
    private final int rows;
    private final int cols;

    public MatrixLogic(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public void fillMatrix(java.util.Scanner scanner) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = scanner.nextInt();
            }
        }
    }

    public MatrixLogic add(MatrixLogic other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            System.out.println("The operation cannot be performed.");
            return null;
        }

        MatrixLogic result = new MatrixLogic(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return result;
    }

    public MatrixLogic multiplyByConstant(int constant) {
        MatrixLogic result = new MatrixLogic(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = this.data[i][j] * constant;
            }
        }
        return result;
    }

    public MatrixLogic multiply(MatrixLogic other) {
        if (this.cols != other.rows) {
            System.out.println("The operation cannot be performed.");
            return null;
        }

        MatrixLogic result = new MatrixLogic(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    result.data[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return result;
    }

    public MatrixLogic transposeMainDiagonal() {
        MatrixLogic result = new MatrixLogic(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    public MatrixLogic transposeSideDiagonal() {
        MatrixLogic result = new MatrixLogic(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[cols - j - 1][rows - i - 1] = this.data[i][j];
            }
        }
        return result;
    }

    public MatrixLogic transposeVertical() {
        MatrixLogic result = new MatrixLogic(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][cols - j - 1] = this.data[i][j];
            }
        }
        return result;
    }

    public MatrixLogic transposeHorizontal() {
        MatrixLogic result = new MatrixLogic(rows, cols);
        for (int i = 0; i < rows; i++) {
            if (cols >= 0) System.arraycopy(this.data[i], 0, result.data[rows - i - 1], 0, cols);
        }
        return result;
    }

    public double calculateDeterminant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Determinant can only be calculated for square matrices.");
        }
        return determinant(data);
    }

    public double determinant(double[][] matrix) {
        int n = matrix.length;
        double det = 0.0;

        if (n == 1) {
            return matrix[0][0];
        }

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        for (int j = 0; j < n; j++) {
            double[][] minor = getMinor(matrix, j);
            det += ((j % 2 == 0) ? 1 : -1) * matrix[0][j] * determinant(minor);
        }
        return det;
    }


    public MatrixLogic inverse() {
        int n = data.length;

        if (calculateDeterminant() == 0) {
            System.out.println("This matrix doesn't have an inverse.");
            return null;
        }

        MatrixLogic augmented = new MatrixLogic(n, 2 * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmented.data[i][j] = data[i][j];
                augmented.data[i][j + n] = (i == j) ? 1.0 : 0.0;
            }
        }

        for (int i = 0; i < n; i++) {
            if (Math.abs(augmented.data[i][i]) < 1e-9) {
                boolean swapped = false;
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(augmented.data[j][i]) > 1e-9) {
                        double[] temp = augmented.data[i];
                        augmented.data[i] = augmented.data[j];
                        augmented.data[j] = temp;
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    System.out.println("This matrix doesn't have an inverse.");
                    return null;
                }
            }

            double diag = augmented.data[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmented.data[i][j] /= diag;
            }

            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmented.data[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        augmented.data[k][j] -= factor * augmented.data[i][j];
                    }
                }
            }
        }

        MatrixLogic inverseMatrix = new MatrixLogic(n, n);
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmented.data[i], n, inverseMatrix.data[i], 0, n);
        }

        return inverseMatrix;
    }

    private double[][] getMinor(double[][] matrix, int col) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];

        for (int i = 1, mi = 0; i < n; i++, mi++) {
            for (int j = 0, mj = 0; j < n; j++) {
                if (j == col) continue;
                minor[mi][mj] = matrix[i][j];
                mj++;
            }
        }
        return minor;
    }

    public void print() {
        for (double[] row : data) {
            for (double value : row) {
                if (value == Math.floor(value)) {
                    System.out.print((int) value + " ");
                } else {
                    System.out.printf("%.2f ", value);
                }
            }
            System.out.println();
        }
    }

}
