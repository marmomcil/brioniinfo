package hr.foi.air.cjenikuslugawebmodul;

import android.os.AsyncTask;
import android.os.Environment;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import hr.foi.air.cjenikuslugainterface.CjenikUslugaMain;
import hr.foi.air.cjenikuslugainterface.IFragmentCallback;

/**
 * Created by Gabrijel on 05.09.2015..
 */

public class CjenikUslugaAsyncTask extends AsyncTask<Object, Void, List<CjenikUslugaMain>> {

    IFragmentCallback mFragmentCallback;

    @Override
    protected List<CjenikUslugaMain> doInBackground(Object... params) {

        String url = (String)params[0];
        List<CjenikUslugaMain> cjenikUslugaMainList = callCjenikUsluga(url);

        mFragmentCallback = (IFragmentCallback)params[1];
        return cjenikUslugaMainList;
    }

    public List<CjenikUslugaMain>callCjenikUsluga(String url){
        ResponseHandler<String> handler = new BasicResponseHandler();
        HttpClient httpClient = new DefaultHttpClient();
        String link = url;
        HttpGet request = new HttpGet(link);
        String result = "";
        List<CjenikUslugaMain> cjenikUslugaMainList = null;

        try {
            result = httpClient.execute(request, handler);

            JsonCjenikUsluga jsonCjenikUsluga = new JsonCjenikUsluga();

            cjenikUslugaMainList = jsonCjenikUsluga.listaCjenik(result);
            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        return cjenikUslugaMainList;
    }

    protected void onPostExecute(List<CjenikUslugaMain> data){

        //save
        try {
            File dir = new File(Environment.getExternalStorageDirectory() + "/files");
            dir.mkdirs();
            File foo = new File(dir, "CjenikUslugaMain.bin");
            foo.createNewFile();
            FileOutputStream fos = new FileOutputStream(foo);
            OutputStream buffer = new BufferedOutputStream(fos);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(data);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //callback
        mFragmentCallback.onTaskDone(data);
    }

}
