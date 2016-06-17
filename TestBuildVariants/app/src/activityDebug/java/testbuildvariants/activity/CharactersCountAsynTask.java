package testbuildvariants.activity;

import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import by.roman.testbuildvariants.R;

/**
 * Created by Roma on 16.06.2016.
 */
public class CharactersCountAsynTask extends AsyncTask<String,Void,Map<Character,Integer>> {

    private Context context;
    public CharactersCountCallBack delegate = null;
    android.support.v4.app.NotificationCompat.Builder mBuilder;

    public CharactersCountAsynTask(AppCompatActivity activity) {
        this.context = activity;
    }
    CharactersCountAsynTask(){}

    @Override
    protected void onPreExecute() {
        createNotify(context.getString(R.string.calculationStart));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onPreExecute();

    }

    @Override
    protected Map<Character,Integer> doInBackground(String... params) {
        Map<Character, Integer> characters = new HashMap<Character, Integer>();
        Scanner scanner = null;

        try {
            Log.d("params",params[0]);
            scanner = new Scanner(params[0]);

            while (scanner.hasNext()) {
                char[] line = scanner.nextLine().toLowerCase().toCharArray();

                for (Character character : line) {
                    if (Character.isLetter(character)){
                        if (characters.containsKey(character)) {
                            characters.put(character, characters.get(character) + 1);
                        } else {
                            characters.put(character, 1);
                        }
                    }
                }
            }
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }


        createNotify(context.getString(R.string.calculationProcessing));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return characters;
    }

    @Override
    protected void onPostExecute(Map<Character,Integer> map) {
        super.onPostExecute(map);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNotify(context.getString(R.string.calculationEnded));
        delegate.getCount(map);
    }
    public void createNotify(String message){


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

         builder.setSmallIcon(R.drawable.sost)
                .setContentTitle(message)
                .setContentText(message);
        // Текст уведомления

        Notification notification = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }
}
