package com.xtouchme.gamebase.managers;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResourceManager {

	private URL baseDirectory				= null;
	
	private static ResourceManager instance	= null;
	
	public Image getImage(String filename) {
		Image image = null;
		
		try {
			image = ImageIO.read(new URL(baseDirectory, filename));
		} catch (IOException e) {
			System.err.printf("%s: Error loading image!%n", filename);
		}
		
		return image;
	}
	
	/* TODO: change to a JSON parser? */
	/* TODO: return a map of all resources in a .json file? */
	//-- Singleton methods --//
	private ResourceManager(URL baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
	private ResourceManager(URL workingDirectory, String path) {
		if(!path.endsWith("/")) path += "/";
		path = path.trim();
		
		try {
			this.baseDirectory = new URL(workingDirectory, path);
		} catch (MalformedURLException e) {
			System.err.printf("%s: MalformedURL", workingDirectory.toExternalForm());
			baseDirectory = null;
		}
	}
	public static ResourceManager getInstance(URL baseDirectory) {
		if(instance == null) instance = new ResourceManager(baseDirectory);
		return instance;
	}
	public static ResourceManager getInstance(URL workingDirectory, String path) {
		if(instance == null) instance = new ResourceManager(workingDirectory, path);
		if(instance.baseDirectory == null) instance = null; //Error in setting the baseDirectory
		return instance;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}