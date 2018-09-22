package com.example.uzzal.notemaintain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter  extends ArrayAdapter<Note> {

    private LayoutInflater inflater;
    private Context context;

    public NoteAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Note> notes) {
        super(context, resource, notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note,null);
        }

        Note note = getItem(position);

        if(note != null){
            TextView title = (TextView) convertView.findViewById(R.id.list_note_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_note_content);

            title.setText(note.getmTitle());
            date.setText(note.getDateTimeFormatted(getContext()));

            if(note.getmContent().length()>50){
                content.setText(note.getmContent().substring(0,50));
            }else {
                content.setText(note.getmContent());
            }

            // try to animation set in Noteadapter ..

             Animation animation = null;

            switch (MainActivity.animationItem){

                case R.id.slideLeft:
                    animation = AnimationUtils.loadAnimation(context,R.anim.slide_left);
                    convertView.startAnimation(animation);
                    break;

                case R.id.slideUp:
                    animation = AnimationUtils.loadAnimation(context,R.anim.slide_up);
                    convertView.startAnimation(animation);
                    break;
            }

        }

        return convertView;
    }
}
