package main.paintings;

import main.geminfo.Gem;
import main.geminfo.Painting;

public class TwoKittens {
	private static final Painting INSTANCE = new Painting("Two Kittens", "A00033876", 
			new Gem(151, 'U', 394),
			new Gem(154, 'C', 280),
			new Gem(223, 'P', 317),
			new Gem(310, 'I', 1119),
			new Gem(400, '6', 114),
			new Gem(472, 'D', 969),
			new Gem(613, 'E', 339),
			new Gem(647, 'N', 491),
			new Gem(677, '&', 378),
			new Gem(720, 'K', 228),
			new Gem(722, 'G', 707),
			new Gem(740, 'J', 83),
			new Gem(741, 'X', 225),
			new Gem(745, '$', 569),
			new Gem(754, 'd', 285),
			new Gem(772, '£', 1117),
			new Gem(819, '#', 1433),
			new Gem(823, '7', 62),
			new Gem(842, 'H', 387),
			new Gem(844, '3', 602),
			new Gem(905, '5', 28),
			new Gem(919, '9', 124),
			new Gem(934, 'T', 243),
			new Gem(938, '2', 112),
			new Gem(961, 'O', 156),
			new Gem(3031, 'Z', 199),
			new Gem(3033, '%', 229),
			new Gem(3052, 'L', 348),
			new Gem(3072, 'b', 176),
			new Gem(3078, '¥', 1408),
			new Gem(3348, 'R', 841),
			new Gem(3354, 'B', 333),
			new Gem(3363, 'F', 122),
			new Gem(3713, '‡', 645),
			new Gem(3770, '€', 1123),
			new Gem(3822, 'Y', 97),
			new Gem(3832, '8', 43),
			new Gem(3853, 'V', 345),
			new Gem(3856, 'A', 474),
			new Gem(3860, 'S', 548),
			new Gem(3865, '+', 1507));
	
	public static Painting getInstance() {
		return INSTANCE;
	}
}