package com.github.leandrohsilveira.specfy.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SpecfyUtilsTest {

	@Test
	public void safeConcat_Test() {
		String safeConcat = SpecfyUtils.safeConcat("safe", "concat");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_baseStringEndsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("safe/", "concat");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_baseStringStartsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("/safe", "concat");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_baseStringStartsAndEndsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("/safe/", "concat");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_concatStartsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("safe", "/concat");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_concatEndsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("safe", "concat/");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_concatStartsAndEndsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("safe", "/concat/");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void safeConcat_baseStringEndsWithDash_concatStartsWithDash_Test() {
		String safeConcat = SpecfyUtils.safeConcat("safe/", "/concat");
		assertEquals("safe/concat", safeConcat);
	}

	@Test
	public void removeLastDash_Test() {
		String url = SpecfyUtils.removeLastDash("remove/");
		assertEquals("remove", url);
	}

	@Test
	public void removeLastDash_mustRemoveOnlyLastDash_Test() {
		String url = SpecfyUtils.removeLastDash("remove/last/dash/");
		assertEquals("remove/last/dash", url);
	}

	@Test
	public void removeLastDash_urlNotEndsWithDash_Test() {
		String url = SpecfyUtils.removeLastDash("remove/last/dash");
		assertEquals("remove/last/dash", url);
	}

	@Test
	public void removeFirstDash_Test() {
		String url = SpecfyUtils.removeFirstDash("/remove");
		assertEquals("remove", url);
	}

	@Test
	public void removeFirstDash_mustRemoveOnlyFirstDash_Test() {
		String url = SpecfyUtils.removeFirstDash("/remove/first/dash");
		assertEquals("remove/first/dash", url);
	}

	@Test
	public void removeFirstDash_urlNotStartWithDash_Test() {
		String url = SpecfyUtils.removeFirstDash("remove/first/dash");
		assertEquals("remove/first/dash", url);
	}

	@Test
	public void endsWithDash_Test() {
		assertTrue(SpecfyUtils.endsWithDash("ends/"));
	}

	@Test
	public void endsWithDash_urlNotEndsWithDash_Test() {
		assertFalse(SpecfyUtils.endsWithDash("ends"));
	}

	@Test
	public void endsWithDash_middleDashesDontMatter_Test() {
		assertTrue(SpecfyUtils.endsWithDash("ends/with/dash/"));
		assertFalse(SpecfyUtils.endsWithDash("ends/with/dash"));
	}

	@Test
	public void startsWithDash_Test() {
		assertTrue(SpecfyUtils.startsWithDash("/ends"));
	}

	@Test
	public void startsWithDash_urlNotStartsWithDash_Test() {
		assertFalse(SpecfyUtils.startsWithDash("ends"));
	}

	@Test
	public void startsWithDash_middleDashesDontMatter_Test() {
		assertTrue(SpecfyUtils.startsWithDash("/ends/with/dash"));
		assertFalse(SpecfyUtils.startsWithDash("ends/with/dash"));
	}
}
