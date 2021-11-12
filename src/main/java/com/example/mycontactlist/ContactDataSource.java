package com.example.mycontactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactDataSource
{
    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource(Context context)
    {
        dbHelper = new ContactDBHelper(context);
    }

    public void open() throws SQLException
    {
        database=dbHelper.getWritableDatabase();
    }

    public void close ()
    {
        dbHelper.close();
    }

    public boolean insertContact(Contact c)
    {
        boolean didSucceed = false;
        try
        {
            ContentValues values = new ContentValues();
            values.put("contactname",c.getName());
            values.put("streetaddress",c.getAddress());
            values.put("city",c.getCity());
            values.put("state",c.getState());
            values.put("zipCode",c.getZipCode());
            values.put("phonenumber",c.getPhoneNumber());
            values.put("cellnumber",c.getCellNumber());
            values.put("email",c.getEmail());
            values.put("birthday",String.valueOf(c.getBirthday().getTimeInMillis()));
            didSucceed = database.insert("contact",null,values)>0;
        }
        catch(Exception e)
        {

        }
        return didSucceed;
    }
    public boolean updateContact(Contact c)
    {
        boolean didSucceed = false;
        try
        {
            ContentValues values = new ContentValues();
            values.put("contactname",c.getName());
            values.put("streetaddress",c.getAddress());
            values.put("city",c.getCity());
            values.put("state",c.getState());
            values.put("zipCode",c.getZipCode());
            values.put("phonenumber",c.getPhoneNumber());
            values.put("cellnumber",c.getCellNumber());
            values.put("email",c.getEmail());
            values.put("birthday",String.valueOf(c.getBirthday().getTimeInMillis()));
            Long id = (long) c.getContactID();
            didSucceed = database.update("contact",values,"_id="+id,null)>0;
        }
        catch(Exception e)
        {

        }
        return didSucceed;
    }
    public int getLastContactID()
    {
        int lastID = -1;
        try
        {
            String query = "Select MAX(_id) from contact";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        }
        catch(Exception e)
        {

        }
        return lastID;
    }
    public ArrayList<String> getContactNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        try
        {
            String query = "Select contactname from contact";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                names.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e)
        {

        }
        return names;
    }
    public ArrayList<Contact> getContacts()
    {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try
        {
            String query = "Select * from contact";
            Cursor cursor = database.rawQuery(query,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                Contact c = new Contact();
                c.setName(cursor.getString(1));
                c.setAddress(cursor.getString(2));
                contacts.add(c);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch(Exception e)
        {

        }
        return contacts;
    }
}
