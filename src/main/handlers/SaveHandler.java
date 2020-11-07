package main.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import main.DiamondPaintings;

public class SaveHandler {
	public static void save() {
		File mainFolder = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Diamond Paintings");
		if (!mainFolder.exists()) {
			mainFolder.mkdirs();
		}
		
		File saveFile = new File(mainFolder.getAbsolutePath() + "\\" + DiamondPaintings.getInstance().getSelectedPainting().getId() + ".txt");
		
		try {
			if (saveFile.exists()) saveFile.delete();
			saveFile.createNewFile();
			
			FileWriter writer = new FileWriter(saveFile);
			writer.write(DiamondPaintings.getInstance().getSelectedPainting().serialize() + "\n");
			writer.write(String.valueOf(DiamondPaintings.getInstance().getSelectedPainting().getTimeWorked()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		File mainFolder = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Diamond Paintings");
		if (!mainFolder.exists()) {
			mainFolder.mkdirs();
		}
		
		File saveFile = new File(mainFolder.getAbsolutePath() + "\\" + DiamondPaintings.getInstance().getSelectedPainting().getId() + ".txt");
		
		try {
			if (!saveFile.exists()) return;
			
			Scanner reader = new Scanner(saveFile);
			int step = 0;
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				if (step == 0) DiamondPaintings.getInstance().getSelectedPainting().deserialize(data);
				if (step == 1) DiamondPaintings.getInstance().getSelectedPainting().addTimeWorked(Long.valueOf(data));
				step ++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}