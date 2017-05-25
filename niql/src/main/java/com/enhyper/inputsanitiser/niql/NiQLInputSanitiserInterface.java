package com.enhyper.inputsanitiser.niql;

public interface NiQLInputSanitiserInterface {
	
	public boolean NiQLInputInjectionFree(String s);
	
	public boolean NiQLInjectionOR(String s);
	
	public boolean NiQLInjectionComment(String s);
	
	public int NiQLInjectionIdentifier(String s);
	
	public final static int MIN_NIQL_KEYWORDS_FOR_INJECTION = 3;

}
