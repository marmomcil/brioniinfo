package hr.foi.air.cjenikuslugawebmodul;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.cjenikuslugainterface.CjenikUslugaMain;

/**
 * Created by Robert on 05.09.2015..
 */

public class JsonCjenikUsluga {

    public List<CjenikUslugaMain>listaCjenik(String Jsonrezultat){
        List<CjenikUslugaMain>cjenikUslugaMains = new ArrayList<CjenikUslugaMain>();
        try {
            JSONArray  jsonArray = new JSONArray(Jsonrezultat);

            for (int i=0;i<jsonArray.length();i++){
                JSONObject cjenik = jsonArray.getJSONObject(i);
                String naziv = cjenik.getString("naziv");
                String telefon = cjenik.getString("telefon");
                String cijena = cjenik.getString("cijena");
                CjenikUslugaMain cjenikUslugaMain = new CjenikUslugaMain(naziv, telefon, cijena);
                cjenikUslugaMains.add(cjenikUslugaMain);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return cjenikUslugaMains;
    }

}
