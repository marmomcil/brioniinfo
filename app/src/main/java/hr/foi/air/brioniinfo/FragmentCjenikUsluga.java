package hr.foi.air.brioniinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hr.foi.air.cjenikuslugainterface.CjenikUslugaMain;
import hr.foi.air.cjenikuslugainterface.ICjenikUslugaInterface;
import hr.foi.air.cjenikuslugainterface.IFragmentCallback;
import hr.foi.air.cjenikuslugatxt.CjenikUslugaTXTMain;
import hr.foi.air.cjenikuslugawebmodul.CjenikUslugaNetworkMain;

/**
 * Created by Gabrijel on 05.09.2015..
 */

public class FragmentCjenikUsluga extends Fragment {

    public FragmentCjenikUsluga() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_fragment_cjenik_usluga, container, false);

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        ICjenikUslugaInterface cjenik;

        if (networkInfo != null && networkInfo.isConnected()){
            Toast.makeText(getActivity(),"Network is available",Toast.LENGTH_LONG).show();
            cjenik = new CjenikUslugaNetworkMain();
        }
        else {
            Toast.makeText(getActivity(),"Network is not available",Toast.LENGTH_LONG).show();
            cjenik = new CjenikUslugaTXTMain();
        }

        cjenik.dohvatiCjenikUsluga(new IFragmentCallback() {
            @Override
            public void onTaskDone(List<CjenikUslugaMain> data) {
                TextView responseTextView = (TextView) rootView.findViewById(R.id.responseTextView);
                String s = "";
                for(int i=0; i<data.size(); i++){
                    CjenikUslugaMain cjenik = data.get(i);
                    s += cjenik.getNaziv() + "\n";
                    s += cjenik.getTelefon() + "\n";
                    s += cjenik.getCijena() + "\n\n";
                }
                responseTextView.setText(s);
            }
        });

        return rootView;
    }
}
