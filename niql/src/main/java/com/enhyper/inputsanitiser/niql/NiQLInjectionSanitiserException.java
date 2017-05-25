package com.enhyper.inputsanitiser.niql;

@SuppressWarnings("serial")
public class NiQLInjectionSanitiserException extends Exception 
{
	public NiQLInjectionSanitiserException(String message) {
		super(message);
	}
}
