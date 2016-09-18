package progernapplications.mytestapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Олег-PC on 18.09.2016.
 */
public class DBHandler extends SQLiteOpenHelper
{

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "UsersBase";
        private static final String TABLE_USERS = "Users";

        // Columns

        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
        private static final String NAME = "name";
        private static final String SURNAME = "surname";
        private static final String EMAIL = "email";
        private static final String DATEOFBIRTH = "date";

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE if not exists " + TABLE_USERS
                + "("
                + LOGIN + " TEXT,"
                + PASSWORD + " TEXT,"
                + NAME + " TEXT,"
                + SURNAME + " TEXT,"
                + EMAIL + " TEXT,"
                + DATEOFBIRTH + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS);
        // Create DB again
        onCreate(sqLiteDatabase);
    }

    public void addUser(String login, String pass, String name, String surname, String email, String birthday)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOGIN, login);
        values.put(PASSWORD, pass);
        values.put(NAME, name);
        values.put(SURNAME, surname);
        values.put(EMAIL, email);
        values.put(DATEOFBIRTH, birthday);

        myDB.insert(TABLE_USERS, null, values);
        myDB.close();
    }

    public ArrayList<String> getUsers()
    {
        ArrayList<String> usersList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do {
                usersList.add("Login :" + cursor.getString(0)
                        + " \n Password :" + cursor.getString(1)
                        + "\n Username: " + cursor.getString(2)
                        + "\n Surname: " + cursor.getString(3)
                        + "\n Email: " + cursor.getString(4)
                        + "\n Date of birth: " + cursor.getString(5));
            } while (cursor.moveToNext()) ;


            }
        return usersList;
    }
}
