package hr.foi.air.cjenikuslugawebmodul;

import hr.foi.air.cjenikuslugainterface.ICjenikUslugaInterface;
import hr.foi.air.cjenikuslugainterface.IFragmentCallback;

/**
 * Created by Robert on 05.09.2015..
 */

public class CjenikUslugaNetworkMain implements ICjenikUslugaInterface {

    @Override
    public void dohvatiCjenikUsluga(IFragmentCallback mFragmentCallback) {

        String url = "http://10.0.3.2/brioni/dohvatiCjenik.php";
        Object object[] = new Object[]{url, mFragmentCallback};

        CjenikUslugaAsyncTask cjenikUslugaAsyncTask = new CjenikUslugaAsyncTask();
        cjenikUslugaAsyncTask.execute(object);

    }

}
