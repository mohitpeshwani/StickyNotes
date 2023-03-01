package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText note;
    private Button flotter;
    private Button incSize;
    private Button decSize;
    private Button boldBtn;
    private Button italicBtn;
    private Button underlineBtn;
    private TextView sizeTv;
    StickyNotes notes = new StickyNotes();

    float currentTextSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        note = findViewById(R.id.editText1);
        flotter = findViewById(R.id.new_note);
        incSize = findViewById(R.id.minusBtn);
        decSize = findViewById(R.id.plusBtn);
        boldBtn = findViewById(R.id.boldbutton);
        italicBtn = findViewById(R.id.italicButton);
        underlineBtn = findViewById(R.id.underlineButton);
        sizeTv = findViewById(R.id.sizeTv);
        currentTextSize = note.getTextSize();
        sizeTv.setText(""+currentTextSize);
        // creating the onclick listner to perform some actions onclicking on it
        incSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeTv.setText(""+currentTextSize);
                currentTextSize=currentTextSize+1;
                note.setTextSize(currentTextSize);
            }
        });

        decSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeTv.setText(""+currentTextSize);
                currentTextSize--;
                note.setTextSize(currentTextSize);
            }
        });

        boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                italicBtn.setTextColor(getResources().getColor(R.color.white));
                italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if (note.getTypeface().isBold()) {
                    note.setTypeface(Typeface.DEFAULT);
                    boldBtn.setTextColor(getResources().getColor(R.color.white));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                } else {
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    note.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                }
            }});

        italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boldBtn.setTextColor(getResources().getColor(R.color.white));
                boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if (note.getTypeface().isItalic()) {
                    note.setTypeface(Typeface.DEFAULT);
                    boldBtn.setTextColor(getResources().getColor(R.color.white));
                    boldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                } else {
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    italicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                    note.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                }


            }
        });

        underlineBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if(note.getPaintFlags()==8)
                    {
                        underlineBtn.setTextColor(getResources().getColor(R.color.white));
                        underlineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                        note.setPaintFlags(note.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                    }
                    else {
                        underlineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                        underlineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                        note.setPaintFlags(note.getPaintFlags() & ~note.getPaintFlags());
                    }

            }
        });
    }

    public void saveButton(View view) {
        notes.setStick(note.getText().toString(),this);
        Toast.makeText(this, "Successfully saved", Toast.LENGTH_LONG).show();

    }

    public void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews =new RemoteViews(this.getPackageName(),R.id.widget_layout1);
        ComponentName thisWidget=new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.textView1,note.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);

    }
}