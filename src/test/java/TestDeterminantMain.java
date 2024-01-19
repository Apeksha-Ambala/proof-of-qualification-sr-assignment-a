import com.example.CalculateMatrixDeterminant;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

/**
 * This is a test class to calculate the determinant of a square matrix.
 *
 * @author Apeksha Ambala
 * @since 1.0
 * @version 1.0
 */

@PrepareForTest
public class TestDeterminantMain {

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testMatrixDeterminant() throws Exception {
      String expectedResult = "73821.1163400000313593476850293264128375816272808008";
    double[][] squaredMatrix = {
      {12.2, 0.5, 11.0, 3.1},
      {4.0, 63.5, 12.8, 0.1},
      {0.0, 1.2, -0.7, 16.0},
      {6.31, 9.85, 1.15, 0.86}
    };
    CalculateMatrixDeterminant calculateMatrixDeterminant =
        PowerMockito.spy(new CalculateMatrixDeterminant(squaredMatrix));

    BigDecimal determinant = Whitebox.invokeMethod(calculateMatrixDeterminant, "getDeterminant");
    String determinantStr = String.valueOf(determinant);
    Assertions.assertNotNull(determinant);
    Assertions.assertEquals(expectedResult, determinantStr);
  }
}
