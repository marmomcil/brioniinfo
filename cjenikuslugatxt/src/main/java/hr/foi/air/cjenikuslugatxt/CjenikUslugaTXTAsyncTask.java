package hr.foi.air.cjenikuslugatxt;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.List;

import hr.foi.air.cjenikuslugainterface.CjenikUslugaMain;
import hr.foi.air.cjenikuslugainterface.IFragmentCallback;

/**
 * Created by Gabrijel on 05.09.2015..
 */

public class CjenikUslugaTXTAsyncTask extends AsyncTask<Object,Void,List<CjenikUslugaMain>> {

    IFragmentCallback mFragmentCallback;

    @Override
    protected List<CjenikUslugaMain> doInBackground(Object... params) {

        mFragmentCallback = (IFragmentCallback)params[0];

        List<CjenikUslugaMain> cjenikUslugaMainList = null;

        try {
            InputStream file = new FileInputStream(Environment.getExternalStorageDirectory() + "/files/CjenikUslugaMain.bin");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            cjenikUslugaMainList = (List<CjenikUslugaMain>)input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cjenikUslugaMainList;
    }

    protected void onPostExecute(List<CjenikUslugaMain> data) {
        mFragmentCallback.onTaskDone(data);
    }
}
