package com.edpena.alphabetSoup;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class AlphabetSoupApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AlphabetSoupApplication.class, args);
		
		int widht = 20;
		int height = 30;
		String palabras[];
		char sopaLetras[][];
		
		
		
		for(int i = 0 ; i< 10 ; i++) {
			System.out.println(RandomStringUtils.randomAlphabetic(1).toLowerCase());
		}
		
	
	}
	public char[][] buidAlphabeSoup(int width,int height){
		
		return new char[width][height];
	}
	public DirectionEnum getDirection(){
		return DirectionEnum.values()[(int)(Math.random() * ((3 -0) + 0)) + 0];
	}

}
