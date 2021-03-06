package com.example.asus.adminpanel;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Edit_NewsfeedActivity extends AppCompatActivity {

    EditText date,author,headline,content;
    Button save;
    private FirebaseAuth mAuth;
    private DatabaseReference refDatabase;
    private Newsfeed_Element newsfeed;
    String sDate,sAuthor,sHeadline,sContent;
    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__newsfeed);

        date=findViewById(R.id.date);
        author=findViewById(R.id.author);
        headline=findViewById(R.id.headline);
        content=findViewById(R.id.content);
        save=findViewById(R.id.save);

        //for calender
        final DatePickerDialog.OnDateSetListener Date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Edit_NewsfeedActivity.this, Date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //calender ends here


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                getAllInputData();
                createStudent();
                createAccountAndSaveInfo();
                date.setText("");
                author.setText("");
                headline.setText("");
                content.setText("");
            }
        });
    }
    void getAllInputData(){
        sDate=date.getText().toString();
        sAuthor=author.getText().toString();
        sHeadline=headline.getText().toString();
        sContent=content.getText().toString();

    }
    void  createStudent(){
        newsfeed=new Newsfeed_Element(sDate,sAuthor,sHeadline,sContent);
    }
    void createAccountAndSaveInfo(){

        DatabaseReference d = FirebaseDatabase.getInstance().getReference("NewsFeed");   ///Give the name of folder
        String primary = d.push().getKey();
        d.child(primary).setValue(newsfeed);
    }

    //for calender
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(sdf.format(myCalendar.getTime()));
        sDate = date.getText().toString().trim();
    }
}
