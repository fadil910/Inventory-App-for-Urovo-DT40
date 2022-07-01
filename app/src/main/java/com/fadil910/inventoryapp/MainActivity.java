package com.fadil910.inventoryapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.device.ScanManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private static final String SCAN_ACTION = ScanManager.ACTION_DECODE;
    public TextInputEditText input;
    public TextInputEditText input1;
    public ListView listView;
    public Button OK;
    public Button btn_annuler;
    public Button btn_raz;
    public Button btn2;

    private final BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(SCAN_ACTION);
            input.setText(result);
            if (result != null) {
                input1.requestFocus();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.textInputEditText);
        input1 = findViewById(R.id.textInputEditText1);
        OK = findViewById(R.id.btn3);
        listView = findViewById(R.id.listView);
        btn_annuler = findViewById(R.id.btn_annuler);
        btn_raz = findViewById(R.id.btn_raz);
        btn2 = findViewById(R.id.btn2);

        input.requestFocus();

        input.setShowSoftInputOnFocus(false);
        input1.setShowSoftInputOnFocus(false);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        OK.setOnClickListener(v -> {
            listItems.add(Objects.requireNonNull(input.getText()).toString() + ';' + Objects.requireNonNull(input1.getText()));
            adapter.notifyDataSetChanged();
            input.setText("");
            input1.setText("");
            input.requestFocus();
        });

        btn_annuler.setOnClickListener(v -> {
            input.setText("");
            input1.setText("");
            input.requestFocus();
        });

        btn_raz.setOnClickListener(v -> {
            input.setText("");
            input.requestFocus();
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
            }
        });
    }

    /*public void save(View v){
        File file = new File(this.getFilesDir(), "ficGloba.dat");
        String fileContent = listItems.toString();
        try {
            FileOutputStream fos = this.openFileOutput(String.valueOf(file), Context.MODE_PRIVATE);
            fos.write(fileContent.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*public void save(View v){
        String filename = "ficGloba.dat";
        String fileContent = listItems.toString();
        try (FileOutputStream fos = this.openFileOutput(filename, Context.MODE_PRIVATE)){
            fos.write(fileContent.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void save(View v) {
        String info = listItems.toString();

        try {
            FileOutputStream fos = new FileOutputStream("/ficGloba.dat", true);
            fos.write(info.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*public void save(View v){
        String root = Environment.getExternalStorageDirectory().toString();
        File Decharge = new File(root + "/ficGloba.dat");
        Decharge.mkdirs();
        String info = listItems.toString();
        File file = new File(Decharge, info);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}