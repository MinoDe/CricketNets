package bowling.zander.com.cricketbowling;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CricketPitch extends Activity {

    String bowlerName="";
    MyDBHandler myDBHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket_pitch);
        Bundle firstData = getIntent().getExtras();
        if(firstData == null){
            return;
        }
        bowlerName = firstData.getString("bowlerName");
        Log.i("bowlerName", bowlerName);
        myDBHandler = new MyDBHandler(this, null, null, 1);
    }

    public void wideClick(View view){
        myDBHandler.insertDelivery(bowlerName, "wide", 1);
    }

    public void oopClick(View view){
        myDBHandler.insertDelivery(bowlerName, "oop", 1);
    }
    public void mopClick(View view){
        myDBHandler.insertDelivery(bowlerName, "mop", 1);
    }
    public void lopClick(View view){
        myDBHandler.insertDelivery(bowlerName, "lop", 1);
    }
    public void oglClick(View view){
        myDBHandler.insertDelivery(bowlerName, "ogl", 1);
    }
    public void mglClick(View view){
        myDBHandler.insertDelivery(bowlerName, "mgl", 1);
    }
    public void lglClick(View view){
        myDBHandler.insertDelivery(bowlerName, "lgl", 1);
    }
    public void ospClick(View view){
        myDBHandler.insertDelivery(bowlerName, "osp", 1);
    }
    public void mspClick(View view){
        myDBHandler.insertDelivery(bowlerName, "msp", 1);
    }
    public void lspClick(View view){
        myDBHandler.insertDelivery(bowlerName, "lsp", 1);
    }

}
