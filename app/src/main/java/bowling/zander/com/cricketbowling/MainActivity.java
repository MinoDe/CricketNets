package bowling.zander.com.cricketbowling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    MyDBHandler myDBHandler;
    EditText myInput;
    TextView myText;
    public Map<String, String> deliverNames; //= BowlingCategory.getDeliveryNames();
    List<String> deliveryCodes;// = BowlingCategory.getDeliverCodes();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBHandler = new MyDBHandler(this, null, null, 1);
        myInput = findViewById(R.id.bowlerName);
        myText = findViewById(R.id.statsView);
        BowlingCategory bc = new BowlingCategory();
        deliverNames = bc.getDeliveryNames();
        deliveryCodes = bc.getDeliverCodes();

    }

    public void onRecordClick(View view){
        Intent i = new Intent(this, CricketPitch.class);
       String editMessage  = myInput.getText().toString();
       if(editMessage == null || editMessage.isEmpty()){
           return;
       }
        i.putExtra("bowlerName", editMessage);
        myInput.setText("");
        startActivity(i);
    }

    public void onViewClick(View view){
        String bowlerNameString = myInput.getText().toString();
        if(bowlerNameString == null || bowlerNameString.isEmpty()){
            return;
        }
        Map<String, Integer> stats = myDBHandler.viewRecord(bowlerNameString);
        if(stats == null || stats.isEmpty()){
            myText.setText("No Records Found");
            return;
        }
        String output = formatOutput(bowlerNameString, stats);
        Log.e("Stats for the bowler", output);
        myText.setText(output);
        myInput.setText("");
    }

    public void onBowlerListClick(View view){
        List<String> bowlerNames = myDBHandler.getAllBowlers();
        String output = formatBowlers(bowlerNames);
        myText.setText(output);
        myInput.setText("");
    }

    private String formatBowlers(List<String> bowlerNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bowler Names : ").append("\n");
        for(String bowler : bowlerNames){
            sb.append(bowler).append("\n");
        }
        return sb.toString();
    }

    private String formatOutput(String bowlerName, Map<String,Integer> stats) {
        StringBuilder sb = new StringBuilder();
        sb.append("Stats  for bowler : ").append(bowlerName).append("\n");
        sb.append("Wides : ").append(stats.get("wide")).append(" , ");
        sb.append("Good Length: ").append(stats.get("ogl")+stats.get("mgl")+stats.get("lgl")).append(" , ");
        sb.append("Short Pitch: ").append(stats.get("osp")+stats.get("msp")+stats.get("lsp")).append("\n");
        sb.append("Over Pitch : ").append(stats.get("oop")+stats.get("mop")+stats.get("lop")).append("\n");
        for(String s : deliveryCodes) {
            Log.i("category :", s);
            sb.append(deliverNames.get(s)).append(stats.get(s)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
