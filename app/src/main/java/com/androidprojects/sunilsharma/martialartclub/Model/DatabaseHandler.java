package com.androidprojects.sunilsharma.martialartclub.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by sunil sharma on 11/3/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";




    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param DATABASE_NAME    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param DATABASE_VERSION number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createDatabaseSQL = "CREATE TABLE " + MARTIAL_ARTS_TABLE +
                "( "
                + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_KEY + " TEXT, "
                + PRICE_KEY + " REAL, "
                + COLOR_KEY + " TEXT" +
                ")";

        db.execSQL(createDatabaseSQL);

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + MARTIAL_ARTS_TABLE);

        onCreate(db);
    }

    /** Adding Data To the MartialArt database*/

    public void addMartialArt(MartialArt martialArtObject)
    {
        SQLiteDatabase database = getWritableDatabase();

        String addMartialArtSQLCommand = "INSERT INTO " + MARTIAL_ARTS_TABLE +
                                        " VALUES(null, '"
                                        + martialArtObject.getMartialArtName()
                                        + "', '" + martialArtObject.getMartialArtPrice()
                                        + "', '" + martialArtObject.getMartialArtColor()
                                        + "')";

        database.execSQL(addMartialArtSQLCommand);
        database.close();
    }

    /** This Method FOr Deleting the Data From Database*/
    public void deleteMartialArtObjectFromDatabaseByID(int id)
    {
        SQLiteDatabase database = getWritableDatabase();

        String deleteMartialArtSQLCommand = "DELETE FROM " + MARTIAL_ARTS_TABLE +
                                            " WHERE " + ID_KEY + " = " + id;

        database.execSQL(deleteMartialArtSQLCommand);
        database.close();
    }

    /** Modifying the Data From the Database*/
    public void modifyMartialArtObject(int martialArtID , String martialArtName ,
                                        double martialArtPrice , String martialArtColor)
    {
        SQLiteDatabase database = this.getWritableDatabase();

        String modifyMartialSQLCommand = "UPDATE " + MARTIAL_ARTS_TABLE +
                                        " SET " + NAME_KEY + " = '" + martialArtName
                                        + "', "
                                        + PRICE_KEY + " = '" + martialArtPrice
                                        + "', "
                                        + COLOR_KEY + " = '" + martialArtColor
                                        + "' "
                                        + "WHERE " + ID_KEY + " = " + martialArtID;

        database.execSQL(modifyMartialSQLCommand);
        database.close();
    }

    /** Here we are getting all The Data form the Database*/
    public ArrayList<MartialArt> returnAllMartialArtObjects()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String sqlQueryCommand = "SELECT * FROM " + MARTIAL_ARTS_TABLE;
        Cursor cursor = database.rawQuery(sqlQueryCommand , null);

        ArrayList<MartialArt> martialArts = new ArrayList<>();

        while(cursor.moveToNext())
        {
            MartialArt currentMartialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                                                    cursor.getString(1) , cursor.getDouble(2) ,
                                                    cursor.getString(3));

            martialArts.add(currentMartialArtObject);
        }

        database.close();
        return martialArts;


    }

    /** Getting Specific Data From The Database*/
    public MartialArt returnMartialArtObjectByID(int id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String sqlQueryCommand = "SELECT * FROM " + MARTIAL_ARTS_TABLE +
                                    " WHERE " + ID_KEY + " = " + id;

        Cursor cursor = database.rawQuery(sqlQueryCommand , null);

        MartialArt martialArtObject = null;

        if(cursor.moveToFirst())
        {
            martialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1) , cursor.getDouble(2) ,
                    cursor.getString(3));
        }

        database.close();
        return martialArtObject;
    }

}
