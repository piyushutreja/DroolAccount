package com.sample.entities.util;

public class OutputDisplay {
	
	public void showtext(String text)
	{
		long time= System.currentTimeMillis();
		System.out.println("time"+time+"-"+text);
	}

}
