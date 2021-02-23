import java.lang.Math;

class ComplexService {

	private static double[] polarize(double num[]) {
		double r = Math.sqrt(Math.pow(num[0], 2) + Math.pow(num[1], 2));
		double theta = Math.atan(num[1] / num[0]);
		double[] polar2 = { r, theta };
		return polar2;

	}

	// modification: z = r(cos x + i sin x)
	// r^(1/n) ( cos((x+2pi*k)/(n)) + i*sin((x+2pi*k)/n))

	/**
	 * @param num1
	 * @param sign
	 * @param num2
	 * @return
	 * @deprecated Use the specific operation methods instead
	 */
	public static double[] calculateComplex(double[] num1, char sign, double[] num2) {
		double[] num3 = new double[2]; // Complex-ate this

		switch (sign) {
			case '+':
				num3[0] = num1[0] + num2[0];
				num3[1] = num1[1] + num2[1];
				break;

			case '-':
				num3[0] = num1[0] - num2[0];
				num3[1] = num1[1] - num2[1];
				break;

			case '*':
				num3[0] = (num1[0] * num2[0]) - (num1[1] * num2[1]);
				num3[1] = (num1[1] * num2[0]) + (num1[0] * num2[1]);
				break;

			case '^': // ONLY REAL INT EXPONENTS ALLOWED

				num3[0] = 1;
				num3[1] = 0;
				if (num2[0] > 0) {
					for (int i = 0; i < (int) num2[0]; i++) {
						num3 = calculateComplex(num3, '*', num1);
					}
				} else // num2[0] < 0
				{
					for (int i = 0; i > (int) (num2[0]); i--) {
						num3 = calculateComplex(num3, '/', num1);
					}
				}

				break;

			case '/':

				// ((ac+bd) / (c^2 + d^2)+ i(bc-ad)/(c^2 + d^2)
				num3[0] = ((num1[0] * num2[0]) + num1[1] * num2[1]) / (Math.pow(num2[0], 2) + Math.pow(num2[1], 2));
				num3[1] = ((num1[1] * num2[0]) - (num1[0] * num2[1])) / (Math.pow(num2[0], 2) + Math.pow(num2[1], 2));
				break;

			case '3': // cube root
				if (num1[1] == 0) {
					num3[1] = 0.0;
					num3[0] = Math.pow(Math.abs(num1[0]), 1.0 / 3.0);
				} else {
					double[] polar = polarize(num1);
					// You add a 2pi*k for other roots
					num3[0] = Math.pow(polar[0], 1.0 / 3.0) * Math.cos((polar[1] / (3)));
					// You add a 2pi*k for other roots
					num3[1] = Math.pow(polar[0], 1.0 / 3.0) * Math.sin((polar[1] / (3)));
				}
				break;

			case '2': // square root
				throw new IllegalArgumentException("Use ComplexService.findSquareRoot() instead");

			default:
				throw new IllegalArgumentException("Moron! Wrong operator");

		}

		return num3;
	}

	public static double[] findSum(double[] addend, double[] augend) {
		return new double[] { addend[0] + augend[0], addend[1] + augend[1] };
	}

	public static double[] findDifference(double[] subtrahend, double[] minuend) {
		return new double[] { subtrahend[0] - minuend[0], subtrahend[1] - minuend[1] };
	}

	public static double[] findProduct(double[] multiplicand, double[] multiplier) {
		return new double[] { multiplicand[0] * multiplier[0] - multiplicand[1] * multiplier[1],
				multiplicand[1] * multiplier[0] + (multiplicand[0] * multiplier[1]) };
	}

	public static double[] findQuotient(double[] dividend, double[] divisor) {
		return new double[] {
				((dividend[0] * divisor[0]) + dividend[1] * divisor[1])
						/ (Math.pow(divisor[0], 2) + Math.pow(divisor[1], 2)),
				((dividend[1] * divisor[0]) - (dividend[0] * divisor[1]))
						/ (Math.pow(divisor[0], 2) + Math.pow(divisor[1], 2)) };
	}

	/**
	 * Takes the square root of a set of values and finds its complex value DM:
	 * (cosx + i*sin x)^n = cos (nx) + i*sin(nx) modification: z = r(cos x + i sin
	 * x) r^(1/n) ( cos((x+2pi*k)/(n)) + i*sin((x+2pi*k)/n))
	 * 
	 * @param values The values under the radical
	 * @return A double with the first element representing the real part and the
	 *         second element the imaginary part
	 */
	public static double[] findSquareRoot(double[] num1) {
		double[] num3 = new double[2];
		if (num1[1] == 0) {
			num3[0] = 0.0;
			num3[1] = Math.pow(Math.abs(num1[0]), 1.0 / 2.0);
		} else {
			double[] polar = polarize(num1);
			num3[0] = Math.pow(polar[0], 1.0 / 2.0) * Math.cos((polar[1] / (2))); // You add a 2pi*k for other roots
			num3[1] = Math.pow(polar[0], 1.0 / 2.0) * Math.sin((polar[1] / (2))); // You add a 2pi*k for other roots
		}

		return num3;
	}
}
