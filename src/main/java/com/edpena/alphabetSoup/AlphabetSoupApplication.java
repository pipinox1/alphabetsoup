package com.edpena.alphabetSoup;

import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edpena.alphabetSoup.dto.AlphabetSoupBuilderDTO;
import com.edpena.alphabetSoup.service.AlphabetSoupServiceImpl;

@SpringBootApplication
public class AlphabetSoupApplication {


	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(AlphabetSoupApplication.class, args);
	
		AlphabetSoupServiceImpl ser = new AlphabetSoupServiceImpl();
		AlphabetSoupBuilderDTO builder = new AlphabetSoupBuilderDTO();
		builder.setW(30);
		builder.setH(30);
		ser.buidAlphabeSoup(builder);
	}
	
}
