package us.allok.megacont;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends ActionBarActivity implements View.OnClickListener{

    private EditText edt_name, edt_city, edt_phone;
    private Button btn_save;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_city = (EditText) findViewById(R.id.edt_city);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
        setResult(RESULT_OK, intent);
        finish();
    }
}
