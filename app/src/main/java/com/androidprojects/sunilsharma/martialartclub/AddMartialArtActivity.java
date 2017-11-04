package com.androidprojects.sunilsharma.martialartclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidprojects.sunilsharma.martialartclub.Model.DatabaseHandler;
import com.androidprojects.sunilsharma.martialartclub.Model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText editName;
    EditText editPrice;
    EditText editColor;
    Button buttonAddMartialArt;
    Button buttonGoBack;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editColor = (EditText) findViewById(R.id.editColor);

        buttonAddMartialArt = (Button) findViewById(R.id.buttonAddMartialArt);
        buttonGoBack = (Button) findViewById(R.id.buttonGoBack);

        databaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

        buttonAddMartialArt.setOnClickListener(AddMartialArtActivity.this);
        buttonGoBack.setOnClickListener(AddMartialArtActivity.this);
    }

    /** Here We are Going to create a method for Adding the Data To The Table*/
    private void addMartialArtObjectToDatabase()
    {
        String nameValue = editName.getText().toString();
        String priceValue = editPrice.getText().toString();
        String colorValue = editColor.getText().toString();

        try
        {
            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArtObject = new MartialArt(
                                                            0 ,
                                                            nameValue ,
                                                            priceDoubleValue ,
                                                            colorValue
                                                          );

            databaseHandler.addMartialArt(martialArtObject);

            Toast.makeText(AddMartialArtActivity.this ,
                                    martialArtObject + "Your Data Inserted To Our Database!" ,
                                    Toast.LENGTH_SHORT).show();




        }
        catch (Exception e)
        {
            e.printStackTrace();
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
        switch (view.getId())
        {
            case R.id.buttonAddMartialArt:
                addMartialArtObjectToDatabase();
                break;

            case R.id.buttonGoBack:
                this.finish();
                break;
        }
    }
}
