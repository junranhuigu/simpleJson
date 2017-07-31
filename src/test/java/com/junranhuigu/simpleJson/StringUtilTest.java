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
	
	@Test
	public void testUnionExecutingLanguage(){
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList.id", true), "root.cardList.id");
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList.id > 20010", true), "root.cardList.id where root.cardList.id > 20010");
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList.id where . > 20010", true), "root.cardList.id where root.cardList.id > 20010");
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList.id where root.cardList.id > 20010", true), "root.cardList.id where root.cardList.id > 20010");
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList", false), "root.cardList");
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList where .id > 20010 and .count > 1", false), "root.cardList where root.cardList.id > 20010 and root.cardList.count > 1");
		assertEquals(StringUtil.unionExecutingLanguage("root.cardList where root.cardList.id > 20010", false), "root.cardList where root.cardList.id > 20010");
	}

}
