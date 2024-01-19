package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;

/**
 * Main Class to calculate the determinant of a square matrix.
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */

/** Takes Input from file and process it to calculate the determinant of a square matrix */
public class Main {
  public static void main(String[] args) {
    BufferedReader reader;

    try {
      String path = Paths.get("").toAbsolutePath().toString();
      String filePath = path + "/src/main/resources/input.txt";
      reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();
      int dimention = 0;
      double[][] squareMatrix = new double[0][0];
      int matrixSizeCurrent = 0;
      boolean isFirstLine = true;

      // Read data from file
      while (line != null) {

        if (isFirstLine) {
          dimention = Integer.parseInt(line);
          isFirstLine = false;
          squareMatrix = new double[dimention][dimention];
          line = reader.readLine();
          continue;
        }

        // Add line in matrix
        line = line.trim().replaceAll("\\s{2,}", " ");

        String[] splitedLineOfMatrix = line.split("\\s+");

        if (splitedLineOfMatrix.length != dimention) {
          System.out.println("Invalid Matrix. Aborting");
          reader.close();
          return;
        }

        for (int i = 0; i < splitedLineOfMatrix.length; i++) {
          squareMatrix[matrixSizeCurrent][i] = Double.parseDouble(splitedLineOfMatrix[i]);
        }

        matrixSizeCurrent++;
        line = reader.readLine();
      }

      reader.close();

      if (matrixSizeCurrent != dimention) {
        System.out.println("Invalid Matrix. Not a square matrix. Aborting.");
        return;
      }

      // Calculate Matrix Determinant
      // The determinant of a triangular matrix is the product of the elements of the main diagonal.
      // So, calculate the triangulation first

      /*
      Matrix:
            Sign row 	A1	    A2	    A3	    A4
            +	  1	    12.2	0.5	    11	    3.1
                  2	    4	    63.5	12.8	0.1
                  3	    0	    1.2	    -0.7	16
                  4	    6.31	9.85	1.15	0.86
      */

      CalculateMatrixDeterminant calculateMatrixDeterminant =
          new CalculateMatrixDeterminant(squareMatrix);

      BigDecimal determinant = calculateMatrixDeterminant.getDeterminant();

      System.out.printf("%.3f", determinant);

    } catch (IOException e) {
      System.out.println("Exception occurred. Aborting.");
      System.exit(0);
      // e.printStackTrace();
    }
  }
}
