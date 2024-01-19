package com.example;

import java.math.BigDecimal;

/**
 * Supporting Class to calculate the determinant of a square matrix.
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */
public class CalculateMatrixDeterminant {

  private double[][] squareMatrix;
  private int sign = 1;

  public CalculateMatrixDeterminant(double[][] squareMatrix) {
    this.squareMatrix = squareMatrix;
  }

  public int getSign() {
    return sign;
  }

  public void setSign(int sign) {
    this.sign = sign;
  }

  /**
   * This Method is used to get the determinant
   *
   * @return determinant of matrix
   */
  public BigDecimal getDeterminant() {
    BigDecimal deter;

    if (!isSquareMatrix(squareMatrix)) {
      System.out.println("Not a square matrix. Aborting.");
      System.exit(0);
    }

    if (isUpperTriangularMatrix() || isLowerTriangularMatrix()) {
      deter = multiplyDiameter().multiply(BigDecimal.valueOf(sign));
    } else {
      makeTriangularMatrix();
      deter = multiplyDiameter().multiply(BigDecimal.valueOf(sign));
    }
    return deter;
  }

  /**
   * This Method is used to check if matrix is a square matrix
   *
   * @param squareMatrix - constructed graph
   * @return boolean as a result
   */
  private boolean isSquareMatrix(double[][] squareMatrix) {
    // Check if all the 2d array has same length as 1d array
    for (int i = 0, l = squareMatrix.length; i < l; i++) {
      if (squareMatrix[i].length != l) {
        return false;
      }
    }
    return true;
  }

  /** This Method is used to make a triangular matrix by adjusting rows and column */
  private void makeTriangularMatrix() {
    int lengthOfMatrix = squareMatrix.length;
    for (int j = 0; j < lengthOfMatrix; j++) {
      sortCol(j);
      for (int i = lengthOfMatrix - 1; i > j; i--) {
        if (squareMatrix[i][j] == 0) continue;

        double x = squareMatrix[i][j];
        double y = squareMatrix[i - 1][j];
        multiplyRow(i, (-y / x));
        addRow(i, i - 1);
        multiplyRow(i, (-x / y));
      }
    }
  }

  /**
   * This Method is used to add two rows and store the value in first row
   *
   * @param row1 - first row
   * @param row2 - second row
   */
  private void addRow(int row1, int row2) {
    for (int j = 0; j < squareMatrix.length; j++) squareMatrix[row1][j] += squareMatrix[row2][j];
  }

  /**
   * This Method is used to multiply the row with given number
   *
   * @param row - row of matrix
   * @param num - number to multiply the row with
   */
  private void multiplyRow(int row, double num) {
    if (num < 0) sign *= -1;

    for (int j = 0; j < squareMatrix.length; j++) {
      squareMatrix[row][j] *= num;
    }
  }

  /**
   * This Method is used to sort the column of matrix
   *
   * @param column - column of matrix
   */
  private void sortCol(int column) {
    int lengthOfMatrix = squareMatrix.length;
    for (int i = lengthOfMatrix - 1; i >= column; i--) {
      for (int k = lengthOfMatrix - 1; k >= column; k--) {
        double tmp1 = squareMatrix[i][column];
        double tmp2 = squareMatrix[k][column];

        if (Math.abs(tmp1) < Math.abs(tmp2)) replaceRow(i, k);
      }
    }
  }

  /**
   * This Method is used to replace row x with row Y and vice versa
   *
   * @param rowX - row to be replaced
   * @param rowY - row to be replaced with
   */
  private void replaceRow(int rowX, int rowY) {
    if (rowX != rowY) sign *= -1;

    int lengthOfMatrix = squareMatrix.length;
    double[] tempRow = new double[lengthOfMatrix];

    for (int i = 0; i < lengthOfMatrix; i++) {
      tempRow[i] = squareMatrix[rowX][i];
      squareMatrix[rowX][i] = squareMatrix[rowY][i];
      squareMatrix[rowY][i] = tempRow[i];
    }
  }

  /**
   * This Method is used to multiply the diameter of matrix
   *
   * @return result of multiplication
   */
  private BigDecimal multiplyDiameter() {
    BigDecimal result = BigDecimal.ONE;
    int lengthOfMatrix = squareMatrix.length;

    for (int i = 0; i < lengthOfMatrix; i++) {
      for (int j = 0; j < lengthOfMatrix; j++) {

        if (i == j) result = result.multiply(BigDecimal.valueOf(squareMatrix[i][j]));
      }
    }
    return result;
  }

  /**
   * This Method is used to check if matrix is a lower triangular matrix
   *
   * @return result of check, true/false
   */
  private boolean isLowerTriangularMatrix() {
    int lengthOfMatrix = squareMatrix.length;
    if (lengthOfMatrix < 2) return false;

    for (int i = 0; i < lengthOfMatrix; i++) {
      for (int j = 0; j < i; j++) {
        if (squareMatrix[i][j] != 0) return false;
      }
    }
    return true;
  }

  /**
   * This Method is used to check if matrix is a upper triangular matrix
   *
   * @return result of check, true/false
   */
  private boolean isUpperTriangularMatrix() {
    int lengthOfMatrix = squareMatrix.length;
    if (lengthOfMatrix < 2) return false;

    for (int j = 0; j < lengthOfMatrix; j++) {
      for (int i = 0; j > i; i++) {
        if (squareMatrix[i][j] != 0) return false;
      }
    }
    return true;
  }
}
