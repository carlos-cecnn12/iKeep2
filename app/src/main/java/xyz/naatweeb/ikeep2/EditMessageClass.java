package xyz.naatweeb.ikeep2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditMessageClass extends AppCompatActivity {
    String messageText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);
        Intent intent = getIntent();
        messageText = intent.getStringExtra(Intent_Constant.INTENT_MESSAGE_DATA);
        position=intent.getIntExtra(Intent_Constant.INTENT_ITEM_POSOTION,-1);
        EditText messageData = (EditText) findViewById(R.id.materia);
        messageData.setText(messageText);
    }

    public void saveButtonClicked(View v){
        String changedMessageText=((EditText)findViewById(R.id.materia)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constant.INTENT_CHANGED_MESSAGE,changedMessageText);
        intent.putExtra(Intent_Constant.INTENT_ITEM_POSOTION,position);
        setResult(Intent_Constant.INTENT_RESULT_CODE2,intent);
        finish();

    }
}
