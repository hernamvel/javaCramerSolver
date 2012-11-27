package org.hernamvel.utils;

import junit.framework.TestCase;

import org.junit.Test;


public class CramerTest extends TestCase {

	private Cramer cramer;
	private Double [][]workingMatrix;
	
	private void setupScenario2x2() {
		Double [][]A = new Double[2][2];
		Double []B = new Double [2];
		// Initialize the matrix and the vector
		A[0][0] = 5.0;
		A[0][1] = 4.0;
		A[1][0] = 1.0;
		A[1][1] = -3.0;
		B[0] = 0.0;
		B[1] = 2.0;
		// Create a new Cramer Instance
		cramer = new Cramer(A,B);
	}
	
	private void setupMatrix3x3() {
		workingMatrix = new Double[3][3];
		Double c = 1.0;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				workingMatrix[i][j] = c;
				c++;
			}			
		}
	}
	
	private void setupScenario3x3() {
		Double [][]A = new Double[3][3];
		A[0][0] = 5.0;
		A[0][1] = 4.0;
		A[0][2] = 1.0;
		A[1][0] = 1.0;
		A[1][1] = -3.0;
		A[1][2] = -5.0;
		A[2][0] = 8.0;
		A[2][1] = 0.0;
		A[2][2] = -1.0;
		Double []B = new Double [3];
		B[0] = 0.0;
		B[1] = 2.0;
		B[2] = -5.0;
		// Create a new Cramer Instance
		cramer = new Cramer(A,B);

	}
	
	@Test
	public void test2x2() {
		setupScenario2x2();
		Double x = cramer.calculateCoeficientsMatrixDeterminant();
		assertEquals(x, -19.0);
	}

	@Test
	public void testDeterminant3x3() {
		setupScenario3x3();
		Double det = cramer.calculateCoeficientsMatrixDeterminant();
		System.out.println ("Det es " + det);
		// Thanks to this online calculator I can set the test:
		// http://www.bluebit.gr/matrix-calculator/
		// The result is =117 for this matrix
		assertEquals(-117.0, det);
	}

	@Test
	public void testSolve3x3() {
		setupScenario3x3();
		Double []x = cramer.solve();
		// Thanks to this online calculator I can set the test:
		// http://www.bluebit.gr/matrix-calculator/
		assertTrue(-0.795 < x[0] && x[0] < -0.794);
		assertTrue(1.3332 <= x[1] && x[1] <= 1.3334 );
		assertTrue(-1.359 < x[2] && x[2] < -1.358);
		
	}

	@Test
	public void testCopyMatrix() {
		setupMatrix3x3();
		cramer = new Cramer();
		Double [][]x = cramer.copyMatrix(workingMatrix, 3, 0);
		assertEquals(x[0][0], 5.0);
		assertEquals(x[0][1], 6.0);
		assertEquals(x[1][0], 8.0);
		assertEquals(x[1][1], 9.0);
		x = cramer.copyMatrix(workingMatrix, 3, 2);
		assertEquals(x[0][0], 4.0);
		assertEquals(x[0][1], 5.0);
		assertEquals(x[1][0], 7.0);
		assertEquals(x[1][1], 8.0);
		x = cramer.copyMatrix(workingMatrix, 3, 1);
		assertEquals(x[0][0], 4.0);
		assertEquals(x[0][1], 6.0);
		assertEquals(x[1][0], 7.0);
		assertEquals(x[1][1], 9.0);
	}

}
