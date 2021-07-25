package com.example.app_part_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//  Adapter for the recycler views
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notesList = new ArrayList<>();   //  reference to the data that will be provided to the adapter
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = notesList.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    //  get the number of items in the list (in the recycler view)
    @Override
    public int getItemCount() {
        return notesList.size();
    }

    //  method to set the data (LiveData) to the adapter
    public void setNotesList(List<Note> notesList) {
        this.notesList = notesList;
        notifyDataSetChanged(); // if the data changes, notify (this is also how we give the adapter data as we are using LiveData), if the data changes, we drop the list and recreate it
    }

    //  helper method (public to be accessible from the MainActivity) to get the position of the Note
    //  at a specific position in the adapter
    public Note getNoteAt(int position) {
        return notesList.get(position);
    }


    //  Holds a view item within the adapter
    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.text_view_title);
            this.textViewDescription = itemView.findViewById(R.id.text_view_description);
            this.textViewPriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(this); // set the listener on the card view itself
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) // it is not guaranteed that the listener exists
                listener.onItemClicked(notesList.get(position));
        }
    }

    //  interface for handling item selection
    public interface OnItemClickListener {
        void onItemClicked(Note note);
    }

    //  set the observer for clicks
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
