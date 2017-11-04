package com.androidprojects.sunilsharma.martialartclub;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidprojects.sunilsharma.martialartclub.Model.DatabaseHandler;
import com.androidprojects.sunilsharma.martialartclub.Model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener
{
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);

        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);

        modifyUserInterface();
    }

    private void modifyUserInterface()
    {
        ArrayList<MartialArt> martialArtObjects = databaseHandler.returnAllMartialArtObjects();

        if(martialArtObjects.size() > 0)
        {
            ScrollView scrollView = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(martialArtObjects.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArtObjects.size()];
            EditText[][] editNamesPricesAndColors = new EditText[martialArtObjects.size()][3];

            Button[] modifyButtons = new Button[martialArtObjects.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for(MartialArt martialArtObject : martialArtObjects)
            {
                idTextViews[index] = new TextView(UpdateMartialArtActivity.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArtObject.getMartialArtID() + " ");

                editNamesPricesAndColors[index][0] = new EditText(UpdateMartialArtActivity.this);
                editNamesPricesAndColors[index][1] = new EditText(UpdateMartialArtActivity.this);
                editNamesPricesAndColors[index][2] = new EditText(UpdateMartialArtActivity.this);

                editNamesPricesAndColors[index][0].setText(martialArtObject.getMartialArtName());
                editNamesPricesAndColors[index][1].setText(martialArtObject.getMartialArtPrice() + " ");
                editNamesPricesAndColors[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                editNamesPricesAndColors[index][2].setText(martialArtObject.getMartialArtColor());

                editNamesPricesAndColors[index][0].setId(martialArtObject.getMartialArtID() + 10);
                editNamesPricesAndColors[index][1].setId(martialArtObject.getMartialArtID() + 20);
                editNamesPricesAndColors[index][2].setId(martialArtObject.getMartialArtID() + 30);

                modifyButtons[index] = new Button(UpdateMartialArtActivity.this);
                modifyButtons[index].setText("Modify");
                modifyButtons[index].setId(martialArtObject.getMartialArtID());
                modifyButtons[index].setOnClickListener(UpdateMartialArtActivity.this);

                gridLayout.addView(idTextViews[index] ,
                        (int)(screenWidth * 0.05) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(editNamesPricesAndColors[index][0] ,
                        (int)(screenWidth * 0.20) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(editNamesPricesAndColors[index][1] ,
                        (int) (screenWidth * 0.20) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(editNamesPricesAndColors[index][2] ,
                        (int) (screenWidth * 0.20) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButtons[index] ,
                        (int)(screenWidth * 0.35) ,
                        ViewGroup.LayoutParams.WRAP_CONTENT);



                index++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }


    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view)
    {
        int martialArtObjectID = view.getId();

        EditText editMartialArtName = (EditText) findViewById(martialArtObjectID + 10);
        EditText editMartialArtPrice = (EditText) findViewById(martialArtObjectID + 20);
        EditText editMartialArtColor = (EditText) findViewById(martialArtObjectID + 30);

        String martialArtNameStringValue = editMartialArtName.getText().toString();
        String martialArtPriceStringValue = editMartialArtPrice.getText().toString();
        String martialArtColorStringValue = editMartialArtColor.getText().toString();

        try
        {
            double martialArtPriceDoubleValue = Double.parseDouble(martialArtPriceStringValue);

            databaseHandler.modifyMartialArtObject(martialArtObjectID ,
                    martialArtNameStringValue ,
                    martialArtPriceDoubleValue ,
                    martialArtColorStringValue);

            Toast.makeText(UpdateMartialArtActivity.this,
                    "You Entered Value Is Updated",
                    Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }
}
