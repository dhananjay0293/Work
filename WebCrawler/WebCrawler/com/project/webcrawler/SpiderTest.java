package com.project.webcrawler;

public class SpiderTest {

	public static void main(String[] args) {
		Spider spider=new Spider();
        spider.search("http://www.geeksforgeeks.org/data-structures/", "Count Inversions in an array | Set 1 (Using Merge Sort).");
	}
}
