public class Rand {
	private static long seed;

	public Rand(long s) {
		seed = s;
	}

	public synchronized void setSeed(long s) {
		seed = s;
	}

	public int nextInt() {
		return next(32);
	}

	protected synchronized int next(int bits) {
		seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
		return (int) (seed >>> (48 - bits));
	}

	public int nextInt(int n) {
		if (n <= 0) throw new IllegalArgumentException("Error: Must be N > 0");
		if ((n & -n) == n) return (int) ((n * (long) next(31)) >> 31);
		int bits, val;
		do {
			bits = next(31);
			val = bits % n;
		} while (bits - val + (n - 1) < 0);
		return val;
	}
}
