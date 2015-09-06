package hr.foi.air.cjenikuslugatxt;

import hr.foi.air.cjenikuslugainterface.ICjenikUslugaInterface;
import hr.foi.air.cjenikuslugainterface.IFragmentCallback;

/**
 * Created by Maja on 05.09.2015..
 */

public class CjenikUslugaTXTMain implements ICjenikUslugaInterface {
    @Override
    public void dohvatiCjenikUsluga(IFragmentCallback mFragmentCallback) {

        Object object[] = new Object[]{mFragmentCallback};

        CjenikUslugaTXTAsyncTask cjenikUslugaTXTAsyncTask = new CjenikUslugaTXTAsyncTask();
        cjenikUslugaTXTAsyncTask.execute(object);

    }
}
