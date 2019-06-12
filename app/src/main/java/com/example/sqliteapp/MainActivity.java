package com.example.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper mydb;
EditText name, sirname, marks;
Button adddata,viewall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        sirname = (EditText)findViewById(R.id.edtSirname);
        marks = (EditText)findViewById(R.id.edtmarks);
        name = (EditText)findViewById(R.id.edtName);
        adddata = (Button) findViewById(R.id.btnadd_id);
        viewall = (Button) findViewById(R.id.btnViewall_id);
        addData();
        viewall();
    }

    public void addData (){
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(name.getText().toString(), sirname.getText().toString(), marks.getText().toString());

                if (isInserted =  true){
                    Toast.makeText(MainActivity.this,"Data inserted succesfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Data was not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewall (){
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Cursor res = mydb.getAllData();
              if (res.getCount()== 0){
                  //show messages
                  showmessage("Error","Nathing found");
                  return;
              }
              StringBuffer buffer = new StringBuffer();

              while (res.moveToNext()){
                 buffer.append("ID :"+res.getString(0)+"\n");
                  buffer.append("NAME :"+res.getString(1)+"\n");
                  buffer.append("SIRNAME :"+res.getString(2)+"\n");
                  buffer.append("MARKS :"+res.getString(3)+"\n");
              }
              // show all data as message
                showmessage("Data",buffer.toString());
            }
        });
    }

    public void showmessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
