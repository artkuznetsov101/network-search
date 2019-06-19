package com.upwork;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Network {

	private static final String ERROR_SIZE = "please, use the size greater then 1";
	private static final String ERROR_EQUALITY = "please, use the different values as input";
	private static final String ERROR_NON_POSITIVE = "please, use the positive values as input";

	List<LinkedList<Integer>> list;

	/**
	 * <code>Network</code> class which represents the set of nodes
	 * 
	 * @param size - network size
	 */
	Network(int size) {
		validate(size);

		Supplier<LinkedList<Integer>> supplier = () -> new LinkedList<>();
		list = Stream.generate(supplier).limit(size).collect(Collectors.toList());
	}

	/**
	 * The <code>connect</code> method connects two network nodes
	 * 
	 * @param src - source node
	 * @param dst - destination node
	 */
	public void connect(int src, int dst) {
		validate(src, dst);

		list.get(src - 1).addFirst(dst);
	}

	/**
	 * The <code>query</code> method checks if the path between two nodes exists
	 * 
	 * @param src - source node
	 * @param dst - destination node
	 * @return <code>true</code> if two nodes are connected; <code>false</code>
	 *         otherwise.
	 */
	public boolean query(int src, int dst) {
		validate(src, dst);

		return search(src, dst);
	}

	private boolean search(int src, int dst) {
		if (src == dst)
			return true;
		else
			return list.get(src - 1).stream().anyMatch(x -> search(x, dst));
	}

	private void validate(int size) {
		if (size < 2)
			throw new InvalidParameterException(ERROR_SIZE);
	}

	private void validate(int from, int to) {
		if (from == to)
			throw new InvalidParameterException(ERROR_EQUALITY);
		if (from <= 0 || to <= 0)
			throw new InvalidParameterException(ERROR_NON_POSITIVE);
		if (from > list.size())
			throw new InvalidParameterException(ERROR_EQUALITY);
	}
}
