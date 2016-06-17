package testbuildvariants.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Map;

import by.roman.testbuildvariants.*;

/**
 * Created by Roma on 16.06.2016.
 */
public class SecondActivity extends AppCompatActivity implements testbuildvariants.activity.CharactersCountCallBack {
    private ListView lvMain;
    private String[] charactersCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lvMain = (ListView) findViewById(R.id.charactersListView);
        testbuildvariants.activity.CharactersCountAsynTask calculationAsynTask = new testbuildvariants.activity.CharactersCountAsynTask(this);
        calculationAsynTask.delegate = this;
        calculationAsynTask.execute(getIntent().getExtras().getString("STRING_EXTRA"));
    }

    @Override
    public void getCount(Map<Character, Integer> map) {
        if(!map.isEmpty()) {
            charactersCount = new String[map.size()];
            int i = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                charactersCount[i] = entry.getKey().toString()+":"+ entry.getValue();
                i++;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, charactersCount);

            // присваиваем адаптер списку
            lvMain.setAdapter(adapter);
        }
    }
}
