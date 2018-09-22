package com.example.uzzal.notemaintain;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private EditText mEtTitle;
    private EditText mEtContent;
    private String mNoteFileName;
    private Note mLoadedNote;

    private ImageView buttonSpeach;
    private TextToSpeech tts;



    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        buttonSpeach = findViewById(R.id.buttonspeach_id);



        mEtTitle = (EditText) findViewById(R.id.note_et_title);
        mEtContent = (EditText) findViewById(R.id.note_et_content);
        mNoteFileName=getIntent().getStringExtra("NOTE_FILE");

                // text for the speech
        txvResult = (TextView) findViewById(R.id.txvResult);



        //speak  text for the code
        tts = new TextToSpeech(this,this);

           //speak  text for the code

        buttonSpeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });





         // back button add
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(mNoteFileName != null && !mNoteFileName.isEmpty()){
            mLoadedNote = Utilities.getNoteByName(this,mNoteFileName);

            if(mLoadedNote != null){
                mEtTitle.setText(mLoadedNote.getmTitle());
                mEtContent.setText(mLoadedNote.getmContent());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new,menu);

                // try to search

//        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.menu_main,menu);
////
////        MenuItem menuItem = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) menuItem.getActionView();
////
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////
////                mLoadedNote.setmTitle(newText);
////                return false;
////            }
////        });
//
             //end search


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id =item.getItemId();

               // search button project add
        if(id==R.id.search_id){
            Toast.makeText(this, "Search Button is clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NoteActivity.this,MainActivity.class));
        }


                // back button for code
        if(id== android.R.id.home){
            this.finish();
        }


              // note save and delete for code
        switch (item.getItemId()){

            case R.id.action_note_save:
                saveNote();
                break;
            case R.id.action_note_delete:
                deleteNote();
                break;

        }

        return true;
    }



    private void saveNote(){
        Note note;

        if(mEtTitle.getText().toString().trim().isEmpty()||mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please Enter a title and content ", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mLoadedNote == null){
            note = new Note(System.currentTimeMillis(),mEtTitle.getText().toString()
                    ,mEtContent.getText().toString());
        }else {
            note = new Note(mLoadedNote.getmDateTime(),mEtTitle.getText().toString()
                    ,mEtContent.getText().toString());
        }


//        Note note = new Note(System.currentTimeMillis(),mEtTitle.getText().toString()
//        ,mEtContent.getText().toString());



        if(Utilities.saveNotes(this,note))  {
            Toast.makeText(this, "Congrat's Save Data Your Note.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Cann't Save your Data, Try it please..!", Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    private void deleteNote() {

        if(mEtTitle.getText().toString().trim().isEmpty() || mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, " please enter a title and content", Toast.LENGTH_SHORT).show();
            return;

        }


        if(mLoadedNote == null){
            finish();
        }


        else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle(" Delete ")
                    .setIcon(R.drawable.dele)
                    .setMessage("You are about to Delete. #"+mEtTitle.getText().toString()+"#  -  Are you Sure ? ")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Utilities.deleteNote(getApplicationContext(),
                                    mLoadedNote.getmDateTime()+Utilities.FILE_EXTENSION);

                            Toast.makeText(getApplicationContext(),
                                    mEtTitle.getText().toString()+" , is deleted",Toast.LENGTH_SHORT)
                                    .show();
                            finish();
                        }
                    })

                    .setNegativeButton("no",null)
                    .setNeutralButton("Cancel",null)
                    .setCancelable(false);
            dialog.show();


        }




    }
         //speak  text for the code......................!

    @Override
    protected void onDestroy() {

        if(tts!=null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if(status!=TextToSpeech.SUCCESS){

            int result = tts.setLanguage(Locale.ENGLISH);

            if(result == TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","This Language is not Supported");
            }else {
                buttonSpeach.setEnabled(true);
                speakOut();

            }
        }else {
            Log.e("TTS","Installation is failed");
        }

    }
    public void speakOut(){
        String sp =   mEtContent.getText().toString();
        String text = mEtTitle.getText().toString();


        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        tts.speak(sp,TextToSpeech.QUEUE_FLUSH,null);
    }



              // speech to Text............................!
    public void getSpeechInput(View view) {

          //try
       // speakIn();


        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent,10);
        }else {
            Toast.makeText(this, "Your Wrong !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //txvResult.setText(result.get(0));
                     mEtContent.setText(result.get(0));
                    //mEtTitle.setText(result.get(0));
                }
                break;
        }
    }
          // try................!

//    public  void speakIn(){
//
//        String talk =   mEtContent.getText().toString();
//        String speech = mEtTitle.getText().toString();
//
//
//
//        tts.speak(talk,TextToSpeech.QUEUE_FLUSH,null);
//        tts.speak(speech,TextToSpeech.QUEUE_FLUSH,null);
//
//    }
}
