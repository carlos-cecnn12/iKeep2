package xyz.naatweeb.ikeep2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);
    }

    public void saveButtonClicked(View v){
        String messageText = ((EditText)findViewById(R.id.materia)).getText().toString();
        if(messageText.equals("")){

        }
        else{
            Intent intent = new Intent();
            intent.putExtra(Intent_Constant.INTENT_MATERIA_FIELD,messageText);
            setResult(Intent_Constant.INTENT_RESULT_CODE,intent);
            finish();
        }
    }
}
