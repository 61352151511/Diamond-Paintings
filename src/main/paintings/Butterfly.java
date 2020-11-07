package main.paintings;

import main.geminfo.Gem;
import main.geminfo.Painting;

public class Butterfly {
	private static final Painting INSTANCE = new Painting("Butterfly", "A00025274", 
			new Gem(208, 'N', 1194),
			new Gem(300, '3', 54),
			new Gem(310, 'I', 193),
			new Gem(326, '4', 69),
			new Gem(333, 'L', 663),
			new Gem(355, 'K', 57),
			new Gem(445, '?', 156),
			new Gem(550, '2', 213),
			new Gem(606, 'S', 46),
			new Gem(718, '8', 267),
			new Gem(720, 'V', 353),
			new Gem(725, 'Y', 244),
			new Gem(727, '%', 60),
			new Gem(728, 'H', 303),
			new Gem(740, 'G', 299),
			new Gem(741, 'R', 589),
			new Gem(742, 'A', 274),
			new Gem(743, '&', 416),
			new Gem(744, '£', 430),
			new Gem(745, '$', 210),
			new Gem(814, 'Z', 323),
			new Gem(817, '6', 469),
			new Gem(820, 'T', 4158),
			new Gem(823, 'C', 43),
			new Gem(900, 'J', 30),
			new Gem(919, 'F', 116));
	
	public static Painting getInstance() {
		return INSTANCE;
	}
}