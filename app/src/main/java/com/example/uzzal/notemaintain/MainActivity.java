package com.example.uzzal.notemaintain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListViewNotes;
    NoteAdapter noteAdapter;
    public static int animationItem;

    //search
    // private NoteAdapter adapter;
    //search
     ArrayList<Note> arrayList = new ArrayList<Note>();
     ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewNotes = (ListView) findViewById(R.id.main_listview_notes);


        adapter = new ArrayAdapter<String>(this,R.layout.activity_note,R.id.search_id,databaseList());
        mListViewNotes.setAdapter(adapter);
        mListViewNotes.setOnItemClickListener(this);




    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String value = adapter.getItem(position);
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main,menu);

                   // search view

//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_main,menu);
//
//        MenuItem menuItem = menu.findItem(R.id.search_id);
//
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                adapter.getFilter().filter(newText);
//
//                return false;
//            }
//        });
//
//
//        return super.onCreateOptionsMenu(menu);




//        MenuItem myActionmenuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView)myActionmenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                if(TextUtils.isEmpty(s)){
//                    noteAdapter.getFilter();
//                }else {
//                    noteAdapter.getFilter();
//                }
//
//                return true;
//            }
//        });



        //getMenuInflater().inflate(R.menu.menu_main,menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
       // return super.onCreateOptionsMenu(menu);





//        final MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_main,menu);
//        menuInflater.inflate(R.menu.menu_note_new,menu);
//
//      //  MenuItem menuItem = menu.findItem(R.id.action_main_new_note);
//
//        final MenuItem menuItem = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//
//                return false;
//            }
//        });

        // search bar icon starting to code...........!
        // MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        // SearchView searchView = (SearchView) myActionMenuItem.getActionView();

//       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//           @Override
//           public boolean onQueryTextSubmit(String s) {
//               return false;
//           }
//
//           @Override
//           public boolean onQueryTextChange(String s) {
//               if(TextUtils.isEmpty(s)){
//                   mListViewNotes.clearTextFilter();
//               }else{
//                    mListViewNotes.getTextFilter();
//               }
//
//               return true;
//           }
//       });



        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if(id==R.id.action_main_new_note){
            Toast.makeText(this, "Add Button is clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,NoteActivity.class));
        }



//        animationItem =item.getItemId();
//        return super.onOptionsItemSelected(item);
       return true;

    }




           // eita silo age just modify korsi  onOptions item selected er modify

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.action_main_new_note:
//                // Intent newNoteActivity = new Intent(this, NoteActivity.class);
//                startActivity(new Intent(this,NoteActivity.class));
//                break;
//
//            // add buttoon Click is place......!
////            case R.id.action_search:
////                startActivity(new Intent(this,MainActivity.class));
////               break;
//        }
//        return true;
//
//    }
//





    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);

        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);

        if(notes==null || notes.size() == 0){
            Toast.makeText(this, "You have no Saved Notes!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            NoteAdapter na = new NoteAdapter(this,R.layout.item_note, notes);
            mListViewNotes.setAdapter(na);

            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((Note)mListViewNotes.getItemAtPosition(position)).getmDateTime()
                            + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(),NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE",fileName);
                    startActivity(viewNoteIntent);

                }
            });
        }

    }




}

