package xyz.naatweeb.ikeep2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import android.widget.*;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    String messageText;
    int position;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,EditMessageClass.class);
                intent.putExtra(Intent_Constant.INTENT_MESSAGE_DATA, arrayList.get(position).toString());
                intent.putExtra(Intent_Constant.INTENT_ITEM_POSOTION,position);
                startActivityForResult(intent,Intent_Constant.INTENT_REQUEST_CODE2);

            }
        });
        try{
            Scanner sc = new Scanner(openFileInput("Materias.txt"));
            while(sc.hasNextLine()){
                String data = sc.nextLine();
                arrayAdapter.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public void onClick (View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,EditFieldActivity.class);
        startActivityForResult(intent,Intent_Constant.INTENT_REQUEST_CODE);
    }

    @Override
    public void onBackPressed(){
        try{
            PrintWriter pw = new PrintWriter(openFileOutput("Materias.txt", Context.MODE_PRIVATE));
            for(String data: arrayList){
                pw.println(data);
            }
            pw.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode==Intent_Constant.INTENT_REQUEST_CODE){
            messageText=data.getStringExtra(Intent_Constant.INTENT_MATERIA_FIELD);
            arrayList.add(messageText);
            arrayAdapter.notifyDataSetChanged();
        }

        else if(resultCode==Intent_Constant.INTENT_REQUEST_CODE2){
            messageText=data.getStringExtra(Intent_Constant.INTENT_CHANGED_MESSAGE);
            position=data.getIntExtra(Intent_Constant.INTENT_ITEM_POSOTION,-1);
            arrayList.remove(position);
            arrayList.add(position, messageText);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
