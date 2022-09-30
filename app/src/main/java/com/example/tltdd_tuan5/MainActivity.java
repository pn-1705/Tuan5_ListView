package com.example.tltdd_tuan5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn_addProduct;
    private EditText edt_name, edt_mt, edt_price;
    ArrayList<Items> items = new ArrayList<>();
    Adapter adapter;
    Boolean kt = false;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addProduct = (Button) findViewById(R.id.btn_addproduct);
        edt_name = (EditText) findViewById(R.id.edt_ten);
        edt_mt = (EditText) findViewById(R.id.edt_mota);
        edt_price = (EditText) findViewById(R.id.edt_price);


        listView = (ListView) findViewById(R.id.list_view);
        items.add(new Items("iPhone 12", "Đen, 128G", "30.000.000", R.drawable.iphone__ky2k6x5u6vue_og));
        items.add(new Items("iPhone 14 Pro Max", "Trắng, 64G", "10.190.000", R.drawable.ip12));
        items.add(new Items("iPhone 13 Pro", "Xanh rêu, 256G", "15.990.000", R.drawable.ip14));
        items.add(new Items("iPhone 11 Pro Max", "Đen, 64G", "10.490.000", R.drawable.ip10));
        items.add(new Items("iPhone XS Max", "Vàng, 128G", "8.590.000", R.drawable.images));

        adapter = new Adapter(MainActivity.this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("dulieu", items.get(i).getTen());
                if (kt != true)
                    startActivity(intent);
                kt = false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                kt = true;
                Xacnhanxoa(i);
                return false;
            }
        });

        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new Items(edt_name.getText().toString(), edt_mt.getText().toString(), edt_price.getText().toString(),0));
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void Xacnhanxoa(final int pos) {
        AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(MainActivity.this);
        alertDiaLog.setTitle("Xác nhận");
        alertDiaLog.setIcon(R.mipmap.ic_launcher);
        alertDiaLog.setMessage("Bạn có chắc chắn muốn xóa ?");
        alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                items.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDiaLog.show();

    }
}