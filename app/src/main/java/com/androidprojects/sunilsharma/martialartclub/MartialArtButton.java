package com.androidprojects.sunilsharma.martialartclub;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.androidprojects.sunilsharma.martialartclub.Model.MartialArt;

/**
 * Created by sunil sharma on 11/4/2017.
 */

public class MartialArtButton extends AppCompatButton
{
    private MartialArt martialArtObject;

    public MartialArtButton(Context context , MartialArt martialArt)
    {
        super(context);
        martialArtObject = martialArt;
    }

    public String getMartialArtColor()
    {
        return martialArtObject.getMartialArtColor();
    }

    public double getMartilArtPrice()
    {
        return martialArtObject.getMartialArtPrice();
    }

    public String getMartialArtName()
    {
        return martialArtObject.getMartialArtName();
    }
}
