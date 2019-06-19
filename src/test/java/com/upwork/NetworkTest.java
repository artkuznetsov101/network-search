package com.upwork;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.junit.Test;

public class NetworkTest {

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongSize1() throws Exception {
		new Network(-1);
	}

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongSize2() throws Exception {
		new Network(0);
	}

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongSize3() throws Exception {
		new Network(1);
	}

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongConnect1() throws Exception {
		Network network = new Network(2);
		network.connect(-1, -1);
	}

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongConnect2() throws Exception {
		Network network = new Network(2);
		network.connect(0, 0);
	}

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongConnect3() throws Exception {
		Network network = new Network(2);
		network.connect(1, 1);
	}

	@Test(expected = InvalidParameterException.class)
	public void testNetworkWrongConnect4() throws Exception {
		Network network = new Network(2);
		network.connect(3, 4);
	}

	@Test
	public void testNetworkTrueQuery() throws Exception {
		Network network = new Network(4);
		network.connect(1, 2);
		network.connect(2, 3);
		network.connect(3, 4);
		network.connect(4, 1);

		assertTrue(network.query(1, 4));
		assertTrue(network.query(4, 3));
		assertTrue(network.query(3, 2));
		assertTrue(network.query(2, 1));

		assertTrue(network.query(1, 3));
		assertTrue(network.query(2, 4));
	}

	@Test
	public void testNetworkFalseQuery() throws Exception {
		Network network = new Network(4);
		network.connect(1, 2);
		network.connect(3, 4);

		assertFalse(network.query(2, 3));
		assertFalse(network.query(1, 4));
	}
}
