package hr.foi.air.brioniinfo.db;

public class Stanice {
	private long mjesto_id;
	private long linija_id;
	private long kilometar;
	private long vrijeme;
	private long cijena;
	
	public Stanice(){
		super();
	}
	
	public Stanice(long mjesto_id, long linija_id, long kilometar, long vrijeme, long cijena){
		super();
		this.mjesto_id = mjesto_id;
		this.linija_id = linija_id;
		this.kilometar = kilometar;
		this.vrijeme = vrijeme;
		this.cijena = cijena;
	}
	
	public long getMjestoId(){
		return mjesto_id;
	}
	
	public long getLinijaId(){
		return linija_id;
	}
	
	public long getKilometar(){
		return kilometar;
	}
	
	public long getVrijeme(){
		return vrijeme;
	}
	
	public long getCijena(){
		return cijena;
	}
}
