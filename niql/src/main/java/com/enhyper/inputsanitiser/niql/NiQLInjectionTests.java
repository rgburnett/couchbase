package com.enhyper.inputsanitiser.niql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NiQLInjectionTests {
	
	public static void main(String[] args) {
		
		int ret;
		NiQLInjectionSanitiser n = new NiQLInjectionSanitiser();
		
		// Identifier Injection 
		String injectionIdentifier = "SELECT name, (SELECT * FROM users as users2 USE KEYS users.userPasswordDocumentIds) as passwordDoc FROM users WHERE type = ‘user’;";

		// OR <value> = <value> Injection
		String injectionOR         = "SELECT * WHERE user = 'Bob%'; OR 1002 = 1002;";

		// Comment Injection
		String injectionComment    = "SELECT * FROM users WHERE username = ' + username ‘;  OR 1=1 /*'; AND group = 5 /* only return group 5 */";
		
		n.NiQLInputInjectionFree(injectionIdentifier);
		
		n.NiQLInputInjectionFree(injectionOR);
		
		n.NiQLInputInjectionFree(injectionComment);
		
		n.NiQLInputInjectionFree("This should not trigger an alert");
		
		
		
	}
}
