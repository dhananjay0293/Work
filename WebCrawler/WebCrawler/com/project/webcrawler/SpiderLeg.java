package com.project.webcrawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderLeg {
	
	private List<String> links=new LinkedList<String>();
	private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	
	private Document htmlDocument; 

	public boolean crawl(String currentUrl) {
		try {
			Connection connection=Jsoup.connect(currentUrl).userAgent(USER_AGENT);
			Document htmlDocument=connection.get();
			this.htmlDocument=htmlDocument;
			if(connection.response().statusCode()==200){
			  System.out.println("Successful Received web page at url "+ currentUrl);
			}
		    if(!connection.response().contentType().contains("text/html")){
              System.out.println("Failed Retrieved something other than html");
              return false;
		    }	
			
			Elements linksOnPages=htmlDocument.select("a[href]");
			System.out.println("Found ( "+linksOnPages.size()+" ) links");
			for(Element link:linksOnPages){
				this.links.add(link.absUrl("href"));
			}
			return true;
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error in out HTTP request " + e);
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean searchForWord(String searchWord) {
		if(this.htmlDocument==null){
			System.out.println("ERROR Call Crawl() before searchig for the word");
			return false;
		}
		System.out.println("Searching for the word "+ searchWord+"....");
		String bodyText=this.htmlDocument.body().text();
		return bodyText.toLowerCase().contains(searchWord.toLowerCase());
	}

	public List<String> getLinks() {
		// TODO Auto-generated method stub
		return this.links;
	}

}
