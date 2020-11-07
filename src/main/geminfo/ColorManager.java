package main.geminfo;

import java.awt.Color;
import java.util.HashMap;

public class ColorManager {
	
	private static final Color defaultColor = new Color(127, 127, 127);
	private static final Color selectedColor = new Color(0, 255, 230);
	
	private static final HashMap<Integer, Color> colorMap = new HashMap<Integer, Color>() {
		private static final long serialVersionUID = 7235437716703877278L;
		{
			// this.put(, new Color(, , ));
			this.put(151, new Color(172, 152, 150));
			this.put(154, new Color(76, 56, 58));
			this.put(158, new Color(50, 70, 157));
			this.put(208, new Color(161, 107, 205));
			this.put(223, new Color(163, 117, 113));
			this.put(300, new Color(90, 31, 25));
			this.put(310, new Color(51, 51, 41));
			this.put(318, new Color(170, 194, 240));
			this.put(341, new Color(129, 171, 219));
			this.put(351, new Color(241, 107, 98));
			this.put(400, new Color(125, 83, 64));
			this.put(402, new Color(232, 152, 129));
			this.put(472, new Color(125, 125, 91));
			this.put(600, new Color(225, 49, 87));
			this.put(613, new Color(187, 185, 171));
			this.put(647, new Color(150, 150, 138));
			this.put(677, new Color(190, 182, 156));
			this.put(720, new Color(189, 114, 93));
			this.put(722, new Color(255, 150, 116));
			this.put(740, new Color(255, 117, 51));
			this.put(741, new Color(246, 128, 54));
			this.put(745, new Color(186, 178, 149));
			this.put(754, new Color(253, 195, 193));
			this.put(762, new Color(232, 238, 255));
			this.put(772, new Color(230, 248, 226));
			this.put(797, new Color(153, 190, 255));
			this.put(798, new Color(97, 161, 249));
			this.put(799, new Color(142, 180, 253));
			this.put(800, new Color(184, 229, 255));
			this.put(809, new Color(166, 211, 255));
			this.put(819, new Color(250, 243, 251));
			this.put(820, new Color(40, 58, 192));
			this.put(823, new Color(22, 38, 64));
			this.put(842, new Color(213, 179, 170));
			this.put(844, new Color(20, 40, 65));
			this.put(905, new Color(69, 138, 73));
			this.put(919, new Color(175, 40, 47));
			this.put(934, new Color(72, 73, 68));
			this.put(938, new Color(51, 25, 38));
			this.put(945, new Color(253, 213, 205));
			this.put(961, new Color(255, 127, 174));
			this.put(3031, new Color(45, 32, 42));
			this.put(3033, new Color(228, 218, 216));
			this.put(3052, new Color(160, 168, 145));
			this.put(3072, new Color(199, 222, 228));
			this.put(3078, new Color(240, 239, 185));
			this.put(3348, new Color(206, 209, 152));
			this.put(3354, new Color(193, 170, 168));
			this.put(3363, new Color(128, 159, 125));
			this.put(3705, new Color(255, 88, 88));
			this.put(3713, new Color(247, 196, 205));
			this.put(3727, new Color(234, 176, 198));
			this.put(3747, new Color(219, 232, 255));
			this.put(3756, new Color(232, 255, 254));
			this.put(3770, new Color(252, 243, 248));
			this.put(3805, new Color(237, 78, 134));
			this.put(3822, new Color(245, 211, 104));
			this.put(3837, new Color(94, 49, 144));
			this.put(3853, new Color(238, 115, 56));
			this.put(3854, new Color(250, 164, 79));
			this.put(3856, new Color(249, 200, 160));
			this.put(3860, new Color(98, 70, 92));
			this.put(3865, new Color(239, 248, 255));
			this.put(5200, new Color(238, 246, 255));
		}
	};
	
	public static Color getColorForCode(int code) {
		if (colorMap.containsKey(code)) {
			return colorMap.get(code);
		}
		
		return defaultColor;
	}
	
	public static Color getSelectedColor() {
		return selectedColor;
	}
	
	public static boolean isDark(Color color) {
		double darkness = 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
		
		return (darkness >= 0.75);
	}
}