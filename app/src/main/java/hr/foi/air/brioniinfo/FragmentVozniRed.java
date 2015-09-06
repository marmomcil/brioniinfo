package hr.foi.air.brioniinfo;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragmentVozniRed extends Fragment implements OnClickListener,AdapterView.OnItemSelectedListener{

    private AutoCompleteTextView text1;
    private AutoCompleteTextView text2;

    InputStream is=null;

    String result=null;

    String line=null;

    Button btnPretrazi;

    HttpClient httpclient;



    public FragmentVozniRed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_fragment_vozni_red, container, false);

        text1 = (AutoCompleteTextView)rootView.findViewById(R.id.autoTxtPolazak);

        text2 = (AutoCompleteTextView)rootView.findViewById(R.id.autoTxtDolazak);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try

        {
            httpclient = new DefaultHttpClient();

            //HttpPost httppost = new HttpPost("http://10.0.3.2/brioni/dohvatiMjesta.php");

            HttpPost httppost = new HttpPost("http://10.0.3.2/brioni/dohvatiMjesta.php");

            HttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();

            httpclient.getConnectionManager().shutdown();

            is = entity.getContent();

            Log.e("Pass 1", "connection success ");


        }

        catch(Exception e)

        {

            Log.e("Fail 1", e.toString());

            Toast.makeText(getActivity().getApplicationContext(), "Invalid IP Address", Toast.LENGTH_LONG).show();

        }

        try

        {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null)

            {

                sb.append(line + "\n");

            }

            is.close();

            result = sb.toString();

            Log.e("Pass 2", "connection success ");

        }

        catch(Exception e)

        {

            Log.e("Fail 2", e.toString());

        }

        try

        {

            JSONArray jsonArray=new JSONArray(result);

            JSONObject jsonObject= null;

            final String[] str1 = new String[jsonArray.length()];

            for(int i=0;i<jsonArray.length();i++)

            {

                jsonObject=jsonArray.getJSONObject(i);

                str1[i]=jsonObject.getString("mjesto_id") + " | " + jsonObject.getString("naziv"); //[1]

            }

            final List<String> list = new ArrayList<String>();

            for(int i=0;i<str1.length;i++)

            {

                list.add(str1[i]);

            }

            Collections.sort(list);


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item, list);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            text1.setThreshold(1);

            text1.setAdapter(dataAdapter);

            text1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                    // TODO Auto-generated method stub

                }

            });

            text2.setThreshold(1);

            text2.setAdapter(dataAdapter);

            text2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                    // TODO Auto-generated method stub

                }

            });

        }

        catch(Exception e)

        {

            Log.e("Fail 3", e.toString());

        }


        btnPretrazi = (Button) rootView.findViewById(R.id.btnPretrazi);
        btnPretrazi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String txt1 = text1.getText().toString();
                String txt2 = text2.getText().toString();
                if(txt1 != null && txt2 != null){

                    String id1 = txt1.split(" | ")[0]; //[2]
                    String id2 = txt2.split(" | ")[0];

                    httpclient = new DefaultHttpClient();

                    //HttpPost httppost = new HttpPost("http://10.0.3.2/brioni/dohvatiMjesta.php");

                    //HttpPost httppost = new HttpPost("http://10.0.2.2/brioni/dohvatiLinije.php?polID=11&dolID=42");

                    HttpPost httppost = new HttpPost("http://10.0.3.2/brioni/dohvatiStanice.php?polID="+id1+"&dolID="+id2); //[3]

                    HttpResponse response = null;
                    try {
                        response = httpclient.execute(httppost);
                        HttpEntity entity = response.getEntity();
                        httpclient.getConnectionManager().shutdown();
                        is = entity.getContent();
                        try
                        {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                            StringBuilder sb = new StringBuilder();
                            while ((line = reader.readLine()) != null)
                            {
                                System.out.println(line);
                                sb.append(line); //[4]
                            }
                            is.close();
                            result = sb.toString();
                            Log.e("Pass 2", "connection success ");
                        }
                        catch(Exception e)
                        {
                            Log.e("Fail 2", e.toString());
                        }
                        Log.e("Pass 1", "connection success ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentRezultat fragmentRezultat = new FragmentRezultat(result); //[5]
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.fragmentholder, fragmentRezultat);
                    fragmentTransaction.commit();
                }


            }
        });


        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

    }

}
