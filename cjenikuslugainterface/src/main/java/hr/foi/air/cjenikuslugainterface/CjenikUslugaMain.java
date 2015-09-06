package hr.foi.air.cjenikuslugainterface;

import java.io.Serializable;

/**
 * Created by Marko on 05.09.2015..
 */

public class CjenikUslugaMain implements Serializable {

    private String naziv;

    private String telefon;

    private String cijena;

    public CjenikUslugaMain(){
    }

    public CjenikUslugaMain(String naziv, String telefon, String cijena){
        this.naziv = naziv;
        this.telefon = telefon;
        this.cijena = cijena;
    }

    //getters
    public String getNaziv() {
        return this.naziv;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public String getCijena() {
        return this.cijena;
    }

}
