package com.edpena.alphabetSoup.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.edpena.alphabetSoup.DirectionEnum;
import com.edpena.alphabetSoup.PositionHelper;
import com.edpena.alphabetSoup.dto.AlphabetSoupBuilderDTO;

@Service
public class AlphabetSoupServiceImpl {

	String palabras[] = { "HOLA", "NARANJA", "PERA", "KIWI" };

	private DirectionEnum getDirection() {

		return DirectionEnum.values()[(int) (Math.random() * ((3 - 0) + 0)) + 0];
	}

	public char[][] buidAlphabeSoup(AlphabetSoupBuilderDTO alphabetBuilder) throws InterruptedException {
		Set<PositionHelper> positions = new HashSet<PositionHelper>();
		Random aleatorio = new Random(System.currentTimeMillis());

		char sopaLetras[][] = new char[alphabetBuilder.getW()][alphabetBuilder.getH()];

		for (int i = 0; i < alphabetBuilder.getH() - 1; i++) {
			for (int j = 0; j < alphabetBuilder.getW() - 1; j++) {
				sopaLetras[j][i] = RandomStringUtils.randomAlphabetic(1).toLowerCase().charAt(0);
			}
		}

		boolean validPosition = false;
		DirectionEnum direction = DirectionEnum.VERTICAL;

		int counter = 0;

		for (String word : Arrays.asList(palabras)) {
			Random sentido = new Random();
			boolean orientation = sentido.nextBoolean();
			int x = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getW());
			int y = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getH());
			switch (direction) {
			case VERTICAL:
				if (alphabetBuilder.isBtt()) {
					for (int i = 0; i < word.toCharArray().length; i++) {
						if (positions.contains(new PositionHelper(x, y - counter)) || x >= alphabetBuilder.getW()
								|| (y - counter) >= alphabetBuilder.getH()) {
							x = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getW());
							y = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getH());
							i = 0;
							counter = 0;
						}
						counter++;
					}
				} else if (alphabetBuilder.isTtb()) {
					for (int i = 0; i < word.toCharArray().length; i++) {
						if (positions.contains(new PositionHelper(x, y + counter)) || x >= alphabetBuilder.getW()
								|| (y + counter) >= alphabetBuilder.getH()) {
							x = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getW());
							y = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getH());
							i = 0;
							counter = 0;
						}
						counter++;
					}
				}
				break;
			case HORIZONTA:
				for (int i = 0; i < word.toCharArray().length; i++) {
					if (positions.contains(new PositionHelper(x + counter, y))
							|| (x + counter) >= alphabetBuilder.getW() || y >= alphabetBuilder.getH()) {
						x = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getW());
						y = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getH());
						i = 0;
						counter = 0;
					}
					counter++;
				}
				break;
			case LEFT_DIAGONAL:
				for (int i = 0; i < word.toCharArray().length; i++) {
					if (positions.contains(new PositionHelper(x + counter, y + counter))
							|| (x + counter) >= alphabetBuilder.getW() || (y + counter) >= alphabetBuilder.getH()) {
						x = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getW());
						y = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getH());
						i = 0;
						counter = 0;
					}
					counter++;
				}
				break;
			case RIGTH_DIAGONAL:
				for (int i = 0; i < word.toCharArray().length; i++) {
					if (positions.contains(new PositionHelper(x + counter, y - counter))
							|| (x + counter) >= alphabetBuilder.getW() || (y - counter) >= alphabetBuilder.getH()) {
						x = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getW());
						y = ThreadLocalRandom.current().nextInt(0, alphabetBuilder.getH());
						i = 0;
						counter = 0;
					}
					counter++;
				}
				break;
			default:

			}

			counter = 0;
			switch (direction) {
			case VERTICAL:
				if (orientation && alphabetBuilder.isBtt()) {
				for (int i = 0; i < word.toCharArray().length; i++) {
					sopaLetras[x][y - counter] = word.toCharArray()[i];
					positions.add(new PositionHelper(x, y - counter));
					counter++;
				}
				}else if(orientation && alphabetBuilder.isTtb()) {
					for (int i = 0; i < word.toCharArray().length; i++) {
						sopaLetras[x][y + counter] = word.toCharArray()[i];
						positions.add(new PositionHelper(x, y + counter));
						counter++;
				}
				}
				break;
			case HORIZONTA:
				for (int i = 0; i < word.toCharArray().length; i++) {
					sopaLetras[x + counter][y] = word.toCharArray()[i];
					positions.add(new PositionHelper(x + counter, y));
					counter++;
				}
				break;
			case LEFT_DIAGONAL:
				for (int i = 0; i < word.toCharArray().length; i++) {
					sopaLetras[x + counter][y + counter] = word.toCharArray()[i];
					positions.add(new PositionHelper(x + counter, y + counter));
					counter++;
				}
				break;
			case RIGTH_DIAGONAL:
				for (int i = 0; i < word.toCharArray().length; i++) {
					sopaLetras[x + counter][y - counter] = word.toCharArray()[i];
					positions.add(new PositionHelper(x + counter, y - counter));
					counter++;
				}
				break;
			default:

			}

		}
		positions.stream().forEach(e -> System.out.println("Posiciones x: " + e.getX() + " y :" + e.getY()));
		for (int i = 0; i < alphabetBuilder.getH() - 1; i++) {
			for (int j = 0; j < alphabetBuilder.getW() - 1; j++) {
				System.out.print(" " + sopaLetras[j][i] + " ");
			}
			System.out.print(System.lineSeparator());
		}

		return sopaLetras;
	}
}
