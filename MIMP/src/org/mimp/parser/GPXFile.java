package org.mimp.parser;

import java.io.FileInputStream;

import org.xml.sax.InputSource;

public class GPXFile {

	private String mName = "";
	private String mPath = "";
	private GPXObject gpxObject;
	
	public GPXFile() {

	}
	
	public GPXFile(String name, String path) {
		mName = name;
		mPath = path;
		GPXHandlerImpl gpxHandlerImpl = new GPXHandlerImpl();
		GPXParserLight gpxParserLight = new GPXParserLight(gpxHandlerImpl, null);
		FileInputStream byteStream = null;
		try {
			byteStream = new FileInputStream(path);
			InputSource inputSource = new InputSource(byteStream);
			gpxParserLight.parse(inputSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public String getPath() {
		return mPath;
	}

	public void setPath(String mPath) {
		this.mPath = mPath;
	}
	
	public GPXObject getGpxObject() {
		return gpxObject;
	}
}
