import java.util.Random;

public class PartialState {
	
	public static void main(String args[]) {
	
		Random rand = new Random();
		
		int num1 = rand.nextInt();
		int num2 = rand.nextInt();
		
		System.out.println("FIRST VALUE: " + num1);
		System.out.println("SECOND VALUE: " + num2);
		System.out.println();

		long state = getState(num1, num2);
		
		System.out.println("STATE: " + state);
		System.out.println();
		
		Rand predicted = new Rand(state);

		System.out.println("[ PREDICTED ]\t\t\t[ ACTUAL ]");
		for (int i = 0; i < 1000; i++) {
			System.out.print(predicted.nextInt() + "\t\t\t");
			System.out.print(rand.nextInt() + "\n");
		}
	}

	public static long getState(int n1, int n2) {
		long state = ((long) (n1) << 32) >>> 16;
		for (long i = 1; i <= 65535; i++) {
			long s2 = (state * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
			if ((int) (s2 >>> 16) == n2) return s2;
			
			state++;
		}
		return -1;
	}
}
