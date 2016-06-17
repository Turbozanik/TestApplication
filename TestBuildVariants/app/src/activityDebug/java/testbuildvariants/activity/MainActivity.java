package testbuildvariants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import by.roman.testbuildvariants.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText mainEt;
    Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainEt = (EditText)findViewById(R.id.mainEditText);
        runButton = (Button)findViewById(R.id.runButton);
        runButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.runButton:{
                Log.d("run","runOnclick");
                Intent mainIntent = new Intent(MainActivity.this, SecondActivity.class);
                mainIntent.putExtra("STRING_EXTRA",mainEt.getText().toString());
                MainActivity.this.startActivity(mainIntent);
                break;
            }
        }
    }
}
