package hr.foi.air.brioniinfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentRezultat extends Fragment implements AdapterView.OnItemLongClickListener {

    InputStream is=null;

    String res=null;

    String line=null;

    ListView list;

    @SuppressLint("ValidFragment")
    public FragmentRezultat(String r) {
        Log.d("Test: ", r);
        res = r;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_fragment_rezultat, container, false);

        List<String> l = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, l);
        list = (ListView) rootView.findViewById(R.id.list1);

        list.setOnTouchListener(new View.OnTouchListener() {//ova metoda sluzi da se moze scrollati kroz listu
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        list.setLongClickable(true);

        try {

            JSONObject jsonObject;
            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject=jsonArray.getJSONObject(i);
                String naziv = jsonObject.getString("naziv");
                String vrijeme = jsonObject.getString("vrijeme");
                String udaljenost = jsonObject.getString("udaljenost");
                String cijena = jsonObject.getString("cijena");
                //String opis = jsonObject.getString("opis");
                Log.e("STRING", naziv);
                l.add("Stanica: " + naziv);
                l.add("Vrijeme dolaska/polaska: " + vrijeme + " h");
                l.add("Udaljenost: " + udaljenost + " km");
                l.add("Cijena: " + cijena + " kn");
                //l.add(opis);
                list.setAdapter(adapter);

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



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
    public boolean onItemLongClick(AdapterView<?> list, View v, int position, long id) {
        Toast.makeText(getActivity().getApplicationContext(), "long clicked pos: " + position, Toast.LENGTH_LONG).show();
        return true;
    }
}
