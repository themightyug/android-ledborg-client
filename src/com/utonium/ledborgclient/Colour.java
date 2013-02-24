package com.utonium.ledborgclient;


public class Colour {

	private int _red;
	private int _green;
	private int _blue;


	public Colour() {
	}


	public Colour(String rgb) {
		//Toast.makeText(MainActivity._application_context, rgb, Toast.LENGTH_LONG).show();

		this._red = Integer.parseInt(Character.toString(rgb.charAt(0)));
		this._green = Integer.parseInt(Character.toString(rgb.charAt(1)));
		this._blue = Integer.parseInt(Character.toString(rgb.charAt(2)));
	}


	public String getLedBorgColour() {
		String c = Integer.toString(_red);
		c += Integer.toString(_green);
		c += Integer.toString(_blue);

		return(c);
	}


	public String getHtmlColour() {
		String html = "#";

		String[] conversion = new String[3];
		conversion[0] = "00";
		conversion[1] = "AA";
		conversion[2] = "FF";

		html += conversion[_red];
		html += conversion[_green];
		html += conversion[_blue];

		return(html);
	}
}
