//package DataBaseHelper;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.provider.SyncStateContract;
//
//public class DBAdapter {
//
//    Context c;
//    SQLiteDatabase db;
//    DOA_DatabaseHelper helper;
//
//    public DBAdapter(Context c) {
//        this.c = c;
//        helper=new DOA_DatabaseHelper(c);
//    }
//
//    //OPEN CON
//    public void openDB()
//    {
//        try
//        {
//            db=helper.getWritableDatabase();
//        }catch (SQLException e)
//        {
//
//        }
//    }
//    //CLOSE DB
//    public void closeDB()
//    {
//        try
//        {
//            helper.close();
//        }catch (SQLException e)
//        {
//
//        }
//    }
//
//    //SAVE
//    public boolean add(String name)
//    {
//        try
//        {
//            ContentValues cv=new ContentValues();
//            cv.put(Constants.NAME,name);
//
//            long result=db.insert(SyncStateContract.Constants.TB_NAME,Constants.ROW_ID,cv);
//            if(result>0)
//            {
//                return true;
//            }
//        }catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    //SELECT
//    public Cursor retrieve()
//    {
//        String[] columns={Constants.ROW_ID,Constants.NAME};
//
//        Cursor c=db.query(Constants.TB_NAME,columns,null,null,null,null,null);
//        return c;
//    }
//
//    //UPDATE/edit
//    public boolean update(String newName,int id)
//    {
//        try
//        {
//            ContentValues cv=new ContentValues();
//            cv.put(Constants.NAME,newName);
//
//            int result=db.update(Constants.TB_NAME,cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
//            if(result>0)
//            {
//                return true;
//            }
//        }catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return false;
//
//    }
//
//    //DELETE/REMOVE
//    public boolean delete(int id)
//    {
//        try
//        {
//            int result=db.delete(Constants.TB_NAME,Constants.ROW_ID+" =?",new String[]{String.valueOf(id)});
//            if(result>0)
//            {
//                return true;
//            }
//
//        }catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//}