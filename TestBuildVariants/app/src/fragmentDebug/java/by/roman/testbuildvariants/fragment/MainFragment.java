package by.roman.testbuildvariants.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import by.roman.testbuildvariants.R;


/**
 * Created by Roma on 16.06.2016.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    EditText mainEt;
    Button runButton;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mainEt = (EditText)view.findViewById(R.id.mainEditText);
        runButton = (Button)view.findViewById(R.id.runButton);
        tv = (TextView)view.findViewById(R.id.textView);
        tv.setText(getLocale()+"");

        runButton.setOnClickListener(this);

        return view;
    }

    private Locale getLocale() {
        return getResources().getConfiguration().locale;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.runButton:{
                Log.d("run","runOnclick");
                Bundle bundle=new Bundle();
                bundle.putString("STRING_EXTRA",mainEt.getText().toString());
                bundle.putString("LOCALE",tv.getText()+"");

                SecondFragment frag = new SecondFragment();
                frag.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameContainer,frag).addToBackStack("main");
                transaction.commit();

                break;
            }
        }
    }
}
