package application;

public class KayitlarLogin {

	private int id;
	private String kul_ad;
	private String sifre;
	private int yetki;
	
	public KayitlarLogin() {
		// TODO Auto-generated constructor stub
	this.yetki=0;
	
	}

public KayitlarLogin(int id, String kul_ad, String sifre, int yetki) {
	
	this.id = id;
	this.kul_ad = kul_ad;
	this.sifre = sifre;
	this.yetki = yetki;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getKul_ad() {
	return kul_ad;
}

public void setKul_ad(String kul_ad) {
	this.kul_ad = kul_ad;
}

public String getSifre() {
	return sifre;
}

public void setSifre(String sifre) {
	this.sifre = sifre;
}

public int getYetki() {
	return yetki;
}

public void setYetki(int yetki) {
	this.yetki = yetki;
}
	
	
}
