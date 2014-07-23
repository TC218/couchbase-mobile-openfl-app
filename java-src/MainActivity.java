package ::APP_PACKAGE::;

import android.os.Bundle;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends org.haxe.lime.GameActivity {
  
 static Database database;
 static String doc_id;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final String TAG = "HelloWorld";
        Log.d(TAG, "Begin Hello World App");


// create a manager
        Manager manager;
        try {
            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
            Log.d (TAG, "Manager created");
        } catch (IOException e) {
            Log.e(TAG, "Cannot create manager object");
            return;
        }

// create a name for the database and make sure the name is legal
        String dbname = "hello";
        if (!Manager.isValidDatabaseName(dbname)) {
            Log.e(TAG, "Bad database name");
            return;
        }
// create a new database
        
        try {
            database = manager.getDatabase(dbname);
            Log.d (TAG, "Database created");
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot get database");
            return;
        }


        try {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("title", "SOME TITLE");
            Document document = database.createDocument();
            document.putProperties(properties);

            Log.d(TAG, "doc id ".concat(document.getId()) );
            doc_id = document.getId();
        } catch (CouchbaseLiteException e) {
            Log.d(TAG, "Cannot save doc");
            return;
        }

        
        Log.d(TAG, "End Hello World App");

    }
   
    public static String doSomething(String in){
      
        Document doc = database.getDocument(doc_id);
        //Log.d(TAG, doc.getProperties().toString());
      
        return in + " / yes! / " + doc.getProperties().get("title");
    }    
}

