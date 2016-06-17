package by.roman.testbuildvariants.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Map;

import by.roman.testbuildvariants.R;


/**
 * Created by Roma on 16.06.2016.
 */
public class SecondFragment extends Fragment implements CharactersCountFragmentCallBack {

    ListView lv;
    private String[] charactersCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        lv = (ListView)view.findViewById(R.id.listView);
        CharactersCountFragmentAsynTask calculationAsynTask = new CharactersCountFragmentAsynTask((AppCompatActivity) getActivity());
        calculationAsynTask.delegate = this;
        calculationAsynTask.execute(getArguments().getString("STRING_EXTRA"),getArguments().getString("LOCALE"));

        return view;
    }

    @Override
    public void getCount(Map<Character, Integer> map) {
        if(!map.isEmpty()) {
            charactersCount = new String[map.size()];
            int i = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                charactersCount[i] = entry.getKey().toString();
                i++;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, charactersCount);

            lv.setAdapter(adapter);
        }
    }
}
