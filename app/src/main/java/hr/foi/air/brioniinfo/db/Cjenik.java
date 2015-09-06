package hr.foi.air.brioniinfo.db;

import android.content.Context;

public class Cjenik{

    private long id;
    private String naziv;
    private String telefon;
    private String cijena;

    public Cjenik(Context applicationContext){
        super();
    }

    public Cjenik(long id, String naziv, String telefon, String cijena){
        super();
        this.id = id;
        this.naziv = naziv;
        this.telefon = telefon;
        this.cijena = cijena;
    }

    public long getId(){
        return id;
    }

    public String getNaziv(){
        return naziv;
    }

    public String getTelefon(){
        return telefon;
    }

    public String getCijena(){
        return cijena;
    }


}
