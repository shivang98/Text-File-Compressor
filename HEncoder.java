// Developed by Shivang Agarwal

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import july31.GenericHeap;

public class HEncoder {
	private HashMap<Character, String> encoder = new HashMap<>();
	private HashMap<String, Character> decoder = new HashMap<>();

	private static class Node {
		char data;
		int freq;
		Node left;
		Node right;

		public static final comp Ctor = new comp();

		private static class comp implements Comparator<Node> {

			@Override
			public int compare(Node o1, Node o2) {
				return o2.freq - o1.freq;
			}
		}
	}

	public HEncoder(String feeder) {
		// create frequency map
		HashMap<Character, Integer> fm = new HashMap<>();
		for (int i = 0; i < feeder.length(); i++) {
			if (fm.containsKey(feeder.charAt(i))) {
				fm.put(feeder.charAt(i), fm.get(feeder.charAt(i)) + 1);
			} else {
				fm.put(feeder.charAt(i), 1);
			}
		}
		// create heap
		GenericHeap<Node> heap = new GenericHeap<>(Node.Ctor);
		ArrayList<Character> keys = new ArrayList<>(fm.keySet());
		for (Character key : keys) {
			Node node = new Node();
			node.data = key;
			node.freq = fm.get(key);
			heap.add(node);
		}
		while (heap.size() != 1) {
			// prepare tree-remove two, merge, add it back
			Node n1 = heap.rempveHP();
			Node n2 = heap.rempveHP();
			Node merge = new Node();
			merge.freq = n1.freq + n2.freq;
			merge.left = n1;
			merge.right = n2;
			heap.add(merge);
		}
		// traverse
		Node FinalNode = heap.rempveHP();
		traverse(FinalNode, "");
	}

	private void traverse(Node node, String osf) {
		if (node.left == null && node.right == null) {
			encoder.put(node.data, osf);
			decoder.put(osf, node.data);
			return;
		}
		traverse(node.left, osf + "0");
		traverse(node.right, osf + "1");
	}

	public String compress(String str) {
		String rv = "";
		for (int i = 0; i < str.length(); i++) {
			rv += encoder.get(str.charAt(i));
		}
		return rv;
	}

	public String decompress(String str) {
		String rv = "";
		String temp = "";
		for (int i = 0; i < str.length(); i++) {
			temp += str.charAt(i);
			if (decoder.containsKey(temp)) {
				rv += decoder.get(temp);
				temp = "";
			}
		}
		return rv;
	}
}
