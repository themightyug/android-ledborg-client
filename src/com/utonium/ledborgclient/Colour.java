package com.utonium.ledborgclient;


public class Colour {

	private int _red;
	private int _green;
	private int _blue;


	public int get_red() {
		return _red;
	}


	public void set_red(int _red) {
		this._red = _red;
	}


	public int get_green() {
		return _green;
	}


	public void set_green(int _green) {
		this._green = _green;
	}


	public int get_blue() {
		return _blue;
	}


	public void set_blue(int _blue) {
		this._blue = _blue;
	}


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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _blue;
		result = prime * result + _green;
		result = prime * result + _red;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colour other = (Colour) obj;
		if (_blue != other._blue)
			return false;
		if (_green != other._green)
			return false;
		if (_red != other._red)
			return false;
		return true;
	}
}
