package com.androidprojects.sunilsharma.martialartclub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.androidprojects.sunilsharma.martialartclub.Model.DatabaseHandler;
import com.androidprojects.sunilsharma.martialartclub.Model.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener , View.OnClickListener
{
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);

        databaseHandler = new DatabaseHandler(DeleteMartialArtActivity.this);

        updateTheUserInterface();
    }

    private void updateTheUserInterface()
    {
        ArrayList<MartialArt> allMartialArtObjects = databaseHandler.returnAllMartialArtObjects();

        RelativeLayout relativeLayout = new RelativeLayout(DeleteMartialArtActivity.this);
        ScrollView scrollView = new ScrollView(DeleteMartialArtActivity.this);
        RadioGroup radioGroup = new RadioGroup(DeleteMartialArtActivity.this);

        for(MartialArt martialArt : allMartialArtObjects)
        {
            RadioButton currentRadioButton = new RadioButton(DeleteMartialArtActivity.this);
            currentRadioButton.setId(martialArt.getMartialArtID());
            currentRadioButton.setText(martialArt.toString());
            radioGroup.addView(currentRadioButton);
        }

        radioGroup.setOnCheckedChangeListener(DeleteMartialArtActivity.this);

        Button buttonBack = new Button(DeleteMartialArtActivity.this);
        buttonBack.setText("Go Back");
        buttonBack.setOnClickListener(DeleteMartialArtActivity.this);
        scrollView.addView(radioGroup);
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT ,
                                                    RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0 , 0 , 0 , 70);
        relativeLayout.addView(buttonBack , buttonParams);

        ScrollView.LayoutParams  scrollViewParams = new ScrollView.LayoutParams(
                                                    ScrollView.LayoutParams.MATCH_PARENT ,
                                                    ScrollView.LayoutParams.MATCH_PARENT);

        relativeLayout.addView(scrollView , scrollViewParams);

        setContentView(relativeLayout);


    }

    /**
     * <p>Called when the checked radio button has changed. When the
     * selection is cleared, checkedId is -1.</p>
     *
     * @param group     the group in which the checked radio button has changed
     * @param checkedId the unique identifier of the newly checked radio button
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        databaseHandler.deleteMartialArtObjectFromDatabaseByID(checkedId);
        Toast.makeText(DeleteMartialArtActivity.this ,
                "The Martial Art Object is Deleted" ,
                Toast.LENGTH_SHORT).show();

        updateTheUserInterface();


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        finish();
    }
}
