package com.enhyper.inputsanitiser.niql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* OR <value> = <value> Injection
*
* Here we want to catch the OR injection attack - taking care not 
* to just look for the simple case of 1 = 1. It could be any two 
* same values so we must compare and throw an exception declaring 
* a NiQL injection attack
*/

public class NiQLInjectionSanitiser implements NiQLInputSanitiserInterface {
	
	private Pattern crgxOR = null;
	private Pattern crgxComment = null;

	private Matcher mOR         = null;
	private Matcher mComment    = null;
	
	private NiQLKeywordsTrie keywords = null;
	
	//TODO - This needs wo compare the values for equality once detected ideally. However, number = number is a fairly good indication of suspicion.
	final static String regexNiQLOR = "\\s+OR\\s(\\d+)\\s*=\\s*(\\d+)\\s*;";  // 
	final static String regexNiQLComment = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";  // Comment injection
	
	public NiQLInjectionSanitiser() {
		
		this.crgxOR = Pattern.compile(regexNiQLOR);
		this.crgxComment = Pattern.compile(regexNiQLComment);
		
		this.keywords = new NiQLKeywordsTrie();
	
	}
	
	public boolean NiQLInputInjectionFree(String s) {
		
		if(NiQLInjectionOR(s)) {
			return false;
		}
		
		if(NiQLInjectionComment(s)) {
			return false;
		}
		
		if(NiQLInjectionIdentifier(s) <= NiQLInputSanitiserInterface.MIN_NIQL_KEYWORDS_FOR_INJECTION) {
			return true;
		}
		
		return false;
	}

	public boolean NiQLInjectionOR(String s) {

		Matcher mOR = crgxOR.matcher(s);
		   
		 if (mOR.find()) {  
			   System.out.println("~S: Possible NiQL OR Injection Attack [" +  mOR.group(0) + "]");
			   return true;
		   }
		   return false;
	}
	
	public boolean NiQLInjectionComment(String s) {
		Matcher mComment = this.crgxComment.matcher(s);
		   
		if (mComment.find()) {  
			System.out.println("~S: Possible NiQL Comment Injection Attack [" +  mComment.group(0) + "]");
			return true;
		}
		return false;
	}
	
	public int NiQLInjectionIdentifier(String s) {
		
		int keywordCount = this.keywords.NiQLKeywordMatchCount(s);
		
		if(keywordCount >= NiQLInputSanitiserInterface.MIN_NIQL_KEYWORDS_FOR_INJECTION) {
			System.out.println("~S: Possible NiQL Identifier Injection Attack. [" + keywordCount + "] couchbase NiQL keywords detected [" + s +"]"); //TODO Insert your favourite logger here
			return keywordCount;
		}
		 
		return 0;
	}
}
