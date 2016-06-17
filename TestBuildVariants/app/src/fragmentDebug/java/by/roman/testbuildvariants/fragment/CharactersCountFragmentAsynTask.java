package by.roman.testbuildvariants.fragment;

import android.app.Notification;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import by.roman.testbuildvariants.R;


/**
 * Created by Roma on 16.06.2016.
 */
public class CharactersCountFragmentAsynTask extends AsyncTask<String,Void,Map<Character,Integer>> {

    private Context context;
    public CharactersCountFragmentCallBack delegate = null;
    android.support.v4.app.NotificationCompat.Builder mBuilder;

    public CharactersCountFragmentAsynTask(AppCompatActivity activity) {
        this.context = activity;
    }
    CharactersCountFragmentAsynTask(){}

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
            String [] alphabet = { "B", "C", "D", "F", "G","H", "J", "K", "L", "M", "N", "P", "O", "R", "S", "T", "V", "W", "X", "Y", "Z","A", "E", "I", "O", "U"};
            String [] ruAlphabet = { "А", "Б", "В", "Г", "Д","Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У","Ф", "Х", "Ц", "Ч", "Ш","Щ","Ъ","Ы","Ь","Э","Ю","Я"};
            Log.d("params",params[0]);
            scanner = new Scanner(params[0]);

            while (scanner.hasNext()) {
                char[] line = scanner.nextLine().toLowerCase().toCharArray();

                for (Character character : line) {
                    if (Character.isLetter(character)){
                        if (!characters.containsKey(character)) {
                            Log.e("locale",getLocale()+","+params[1]);
                            if(getLocale().toString().equals("en_US")){
                                for(int i=0;i<alphabet.length;i++) {
                                    if (alphabet[i].equals(character.toString().toUpperCase())) {
                                        characters.put(character, 1);
                                    }
                                }
                            }else{
                                for(int i=0;i<ruAlphabet.length;i++) {
                                    if (ruAlphabet[i].equals(character.toString().toUpperCase())) {
                                        characters.put(character, 1);
                                    }
                                }
                            }
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
//        Intent notificationIntent = new Intent(context, SecondActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context,
//                0, notificationIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);//change

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

         builder.setSmallIcon(R.drawable.sost)
                .setContentTitle(message)
                .setContentText(message);
        // Текст уведомления

        Notification notification = builder.build();

        //notification.flags = Notification.FLAG_AUTO_CANCEL;//change
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);
    }

    private Locale getLocale() {
        return context.getResources().getConfiguration().locale;
    }
}
