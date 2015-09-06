package hr.foi.air.brioniinfo.db;

public class Linija {

	long linija_id;
	String opis;

	public Linija() {
	}

	public Linija(long linija_id, String opis) {
		super();
		this.linija_id = linija_id;
		this.opis = opis;
	}

	public long getLinijaId(int anInt){

		return linija_id;
	}

	public String getOpis(String string){

		return opis;
	}
}