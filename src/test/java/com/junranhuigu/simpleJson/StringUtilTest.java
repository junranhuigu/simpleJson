package com.junranhuigu.simpleJson;

import junit.framework.TestCase;

import org.junit.Test;

public class StringUtilTest extends TestCase{

	@Test
	public void testMatchSelectNoContidion() {
		assertTrue(StringUtil.matchSelectNoContidion("root[0].fufufufu[0].4"));
		assertTrue(StringUtil.matchSelectNoContidion("root.fufufufu.fu"));
		assertFalse(StringUtil.matchSelectNoContidion("root.fufufufu[a]"));
	}

	@Test
	public void testFindSelect() {
		assertEquals(StringUtil.findSelect("root.soulList.id"), "root.soulList.id");
		assertEquals(StringUtil.findSelect("root.soulList.id > 4"), "root.soulList.id");
	}

}
