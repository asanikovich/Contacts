package us.allok.megacont;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends ActionBarActivity implements View.OnClickListener{

    private EditText edt_name, edt_city, edt_phone;
    private Button btn_save;
    private Intent intent;
    int id;
    private String name="", city="", phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        intent = getIntent();
        id = (int) intent.getIntExtra("id", -100);
        name = intent.getStringExtra("name");
        city = intent.getStringExtra("city");
        phone = intent.getStringExtra("phone");

        if(id < 0) {
            setResult(RESULT_CANCELED, intent);
            finish();
        }
        else {
            btn_save = (Button) findViewById(R.id.btn_save);
            btn_save.setOnClickListener(this);

            edt_name = (EditText) findViewById(R.id.edt_name);
            edt_name.setText( name );
            edt_city = (EditText) findViewById(R.id.edt_city);
            edt_city.setText( city );
            edt_phone = (EditText) findViewById(R.id.edt_phone);
            edt_phone.setText( phone );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
            return true;
        }
        if (id == R.id.action_back) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        intent = new Intent();
        intent.putExtra("name", edt_name.getText().toString() );
        intent.putExtra("phone", edt_phone.getText().toString() );
        intent.putExtra("city", edt_city.getText().toString() );
        intent.putExtra("id", id);
        setResult(RESULT_OK, intent);
        finish();
    }
}
