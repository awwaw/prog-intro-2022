import java.util.*;

public class IntList {
	private int[] ints;
	private int ptr = 0; // pointer to the end of array

	public IntList() {
		this.ints = new int[0];
	}

	public IntList(int[] values) { // values.length = values.ptr
		this.ints = Arrays.copyOf(values, values.length);
		this.ptr = values.length;
	}

	public IntList(IntList other) {
		this.ints = Arrays.copyOf(other.ints, other.ptr);
		this.ptr = other.ptr;
	}

	public int size() {
		return this.ptr;
	}

	public int get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException(
				String.format("Index %d is out of bounds", index));
		}
		else {
			return this.ints[index];
		}
	}

	public boolean set(int index, int value) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException(
				String.format("Index %d is out of bounds", index));
		}
		else {
			this.ints[index] = value;
			return true;
		}
	}

	public boolean append(int value) {
		if (ptr >= this.ints.length) {
			this.ints = Arrays.copyOf(this.ints, (this.ints.length * 3 + 1) / 2);
		}
		this.ints[this.ptr++] = value;
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.ptr; i++) {
			sb.append(Integer.toString(this.ints[i]));
			if (i + 1 != this.ptr) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}
}