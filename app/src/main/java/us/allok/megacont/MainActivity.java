package us.allok.megacont;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private ArrayList<Contact> array;
    private Contact ct;
    private String name="", phone="", city="";
    private int size = 0, i = 0, id = -100;
    private int[] colors = new int[2];
    private Intent intent;
    private LinearLayout linLayout;
    private LayoutInflater ltInflater;

    private final int MENU_DELETE = 1;
    private final int MENU_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        array = new ArrayList<Contact>();
        ct = new Contact("Алексей Саникович", "+37529189566", "Солигорск");
        array.add(ct);
        ct = new Contact("Алексей Пась", "+375293567545", "Минск");
        array.add(ct);
        ct = new Contact("Татьяна Тарасенко", "+375291392265", "Слуцк");
        array.add(ct);
        ct = new Contact("Евгений Шкрабо", "+375291392265", "Жодино");
        array.add(ct);
        ct = new Contact("Алексей Юхо", "+375297078040", "Витебск");
        array.add(ct);
        ct = new Contact("Светлана Саникович", "+37529189566", "Солигорск");
        array.add(ct);
        ct = new Contact("Игорь Пась", "+375293567545", "Минск");
        array.add(ct);
        ct = new Contact("Игорь Cкалабан", "+375293567545", "Минск");
        array.add(ct);
        ct = new Contact("Татьяна Cкалабан", "+375291392265", "Минск");
        array.add(ct);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        linLayout = (LinearLayout) findViewById(R.id.linLayout);
        ltInflater = getLayoutInflater();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(v.getId(), MENU_EDIT, 0, getString(R.string.action_edit));
        menu.add(v.getId(), MENU_DELETE, 1, getString(R.string.action_delete));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_EDIT:
                intent = new Intent(this, EditActivity.class);
                ct = (Contact) array.get(item.getGroupId());
                intent.putExtra("name", ct.getName() );
                intent.putExtra("phone", ct.getPhone() );
                intent.putExtra("city", ct.getCity() );
                intent.putExtra("id", item.getGroupId() );
                startActivityForResult(intent, 1);
                break;
            case MENU_DELETE:
                array.remove(item.getGroupId());
                setData();
                Toast.makeText(this, getString(R.string.del_good), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        if(resultCode==RESULT_CANCELED) {
            finish();
            return;
        }
        if(requestCode==1) {
            name = data.getStringExtra("name");
            city = data.getStringExtra("city");
            phone = data.getStringExtra("phone");
            id = data.getIntExtra("id", -100);
            if( id < 0 ) {return;}
            ct = new Contact(name, phone, city);
            array.set(id, ct);
            Toast.makeText(this, getString(R.string.save_good), Toast.LENGTH_SHORT).show();
        }
        if(requestCode==2) {
            name = data.getStringExtra("name");
            city = data.getStringExtra("city");
            phone = data.getStringExtra("phone");
            ct = new Contact(name, phone, city);
            array.add(ct);
            Toast.makeText(this, getString(R.string.add_good), Toast.LENGTH_SHORT).show();
        }
        setData();
        return;
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        array = savedInstanceState.getParcelableArrayList("array");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("array", array);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            finish();
            return true;
        }
        if (id == R.id.action_add) {
            intent = new Intent(this, AddActivity.class);
            startActivityForResult(intent, 2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setData(){
        linLayout.removeAllViews();
        for (i = 0; i < array.size(); i++) {
            ct = (Contact) array.get(i);

            View item = ltInflater.inflate(R.layout.item, linLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(ct.getName());
            TextView tvPhone = (TextView) item.findViewById(R.id.tvPhone);
            tvPhone.setText(ct.getPhone());
            TextView tvCity = (TextView) item.findViewById(R.id.tvCity);
            tvCity.setText(ct.getCity());
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            item.setId(i);
            linLayout.addView(item);
            registerForContextMenu(item);
        }
    }
}