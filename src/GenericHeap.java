// Contributed by Shivang Agarwal

import java.util.ArrayList;
import java.util.Comparator;

public class GenericHeap<T> {
	ArrayList<T> data = new ArrayList<>();
	Comparator<T> cptr;

	public GenericHeap(Comparator<T> cptr) {
		this.cptr = cptr;
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public void display() {
		System.out.println(data);
	}

	public void add(T value) {
		data.add(value);
		upHeapify(data.size() - 1);
	}

	private void upHeapify(int ci) {
		if (ci == 0) {
			return;
		}
		int pi = (ci - 1) / 2;
		boolean max = isLarger(ci, pi);
		if (max == true) {
			swap(ci, pi);
			upHeapify(pi);
		}

	}

	public T rempveHP() {
		swap(0, data.size() - 1);
		T rv = data.remove(data.size() - 1);
		downHeapify(0);
		return rv;
	}

	private void downHeapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;

		int max = pi;
		if (lci < data.size() && isLarger(lci, max)) {
			max = lci;
		}
		if (rci < data.size() && isLarger(rci, max)) {
			max = rci;
		}
		if (max != pi) {
			swap(max, pi);
			downHeapify(max);
		}
	}

	public T getHP() {
		return data.get(0);
	}

	private void swap(int ci, int pi) {
		T temp = data.get(ci);
		data.set(ci, data.get(pi));
		data.set(pi, temp);
	}

	private boolean isLarger(int i, int j) {
		T ith = data.get(i);
		T jth = data.get(j);
		if (cptr.compare(ith, jth) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void priorityUpdate(T val) {
		int idx = -1;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).equals(val)) {
				idx = i;
				break;
			}
		}
		upHeapify(idx);
		downHeapify(idx);
	}

}
