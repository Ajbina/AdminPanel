package com.example.asus.adminpanel;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddEventFragment extends Fragment {

    int t;
    EditText date,headline,content;
    Button save;
    private FirebaseAuth mAuth;
    private DatabaseReference refDatabase;
    private Events events;
    String sDate,sAuthor,sHeadline,sContent;
    Calendar myCalendar = Calendar.getInstance();

    public AddEventFragment() {
        // Required empty public constructor
    }
    public void setT(int t) {
        this.t = t;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event, container, false);
        date=v.findViewById( R.id.date );
        headline=v.findViewById(R.id.headline);
        content=v.findViewById(R.id.content);
        save=v.findViewById(R.id.save);

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
                new DatePickerDialog(getActivity(), Date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                getAllInputData();
                createStudent();
                createAccountAndSaveInfo();
            }
        });


        return v;
    }


    void getAllInputData(){
        sDate=date.getText().toString();
        sHeadline=headline.getText().toString();
        sContent=content.getText().toString();

    }
    void  createStudent(){
        events=new Events(sDate,sHeadline,sContent);
    }

    void createAccountAndSaveInfo(){
       // FirebaseUser user = mAuth.getCurrentUser();
        // DatabaseReference usersRef = refDatabase.child("newselements");
        //refDatabase = FirebaseDatabase.getInstance().getReference("NewsFeed");
        // refDatabase.child(user.getUid()).setValue(newsfeed);
        //usersRef.setValue(newsfeed);
        if(t==1) {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference( "MCC_Events" );   ///Give the name of folder
            String primary = d.push().getKey();
            d.child( primary ).setValue( events );
        }
        else if(t==2)
        {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference( "MLC_Events" );   ///Give the name of folder
            String primary = d.push().getKey();
            d.child( primary ).setValue( events );
        }
        else if(t==3)
        {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference( "MDFS_Events" );   ///Give the name of folder
            String primary = d.push().getKey();
            d.child( primary ).setValue( events );
        }
        else if(t==4)
        {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference( "MRC_Events" );   ///Give the name of folder
            String primary = d.push().getKey();
            d.child( primary ).setValue( events );
        }
        else if(t==5)
        {
            DatabaseReference d = FirebaseDatabase.getInstance().getReference( "MPS_Events" );   ///Give the name of folder
            String primary = d.push().getKey();
            d.child( primary ).setValue( events );
        }


    }


    //for calender
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(sdf.format(myCalendar.getTime()));
        sDate = date.getText().toString().trim();
    }



}
