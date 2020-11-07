package main.paintings;

import main.geminfo.Gem;
import main.geminfo.Painting;

public class NorthernLights {
	private static final Painting INSTANCE = new Painting("Northern Lights", "A20005550", 
			new Gem(158, 'K', 116),
			new Gem(318, 'V', 73),
			new Gem(341, '6', 476),
			new Gem(351, 'W', 153),
			new Gem(402, 'U', 98),
			new Gem(600, 'H', 264),
			new Gem(613, 'S', 38),
			new Gem(722, '9', 60),
			new Gem(762, 'E', 135),
			new Gem(797, 'P', 1363),
			new Gem(798, 'O', 3515),
			new Gem(799, 'N', 1910),
			new Gem(800, 'L', 668),
			new Gem(809, 'M', 1330),
			new Gem(819, '4', 195),
			new Gem(820, 'Q', 3629),
			new Gem(823, 'R', 1599),
			new Gem(945, 'T', 316),
			new Gem(3705, '3', 84),
			new Gem(3727, 'G', 172),
			new Gem(3747, 'J', 691),
			new Gem(3756, '7', 458),
			new Gem(3770, 'A', 207),
			new Gem(3805, '5', 270),
			new Gem(3837, 'I', 380),
			new Gem(3854, 'B', 95),
			new Gem(3865, 'D', 575),
			new Gem(5200, 'F', 170));
	
	public static Painting getInstance() {
		return INSTANCE;
	}
}