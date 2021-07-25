package com.example.app_part_1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {


    final private OnListItemClickListener myOnListItemClickListener;

    private ArrayList<Person> myPersons;

    //  adapter constructor (used to give the adapter simple data for now)
    public PersonsAdapter(ArrayList<Person> myPersons, OnListItemClickListener listener) {
        this.myPersons = myPersons;
        myOnListItemClickListener = listener;
    }

    /*  method responsible for converting the single list item layout file (person_list_item) from XML
            into individual View Objects and store them in a ViewHolder
         */
    @NonNull
    @Override
    public PersonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.person_list_item, parent, false);
        return new ViewHolder(view);
    }

    /*
        method responsible for setting the data from the data source into the adapter on each
        relevant view.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.personName.setText(myPersons.get(position).getPersonName());
        viewHolder.personAddress.setText(myPersons.get(position).getAddress());
        viewHolder.personOccupation.setText(myPersons.get(position).getOccupation());
        viewHolder.personImage.setImageResource(myPersons.get(position).getIconId());

        if(position % 2 == 0)
        {
            viewHolder.itemView.findViewById(R.id.persons_list_item_container).setBackgroundColor(Color.parseColor("#0000ff"));
        }
        else {
            viewHolder.itemView.findViewById(R.id.persons_list_item_container).setBackgroundColor(Color.parseColor("#FFB6C1"));
        }
    }


    //  return the length (size) of the provided data
    @Override
    public int getItemCount() {
        return myPersons.size();
    }

    // ViewHolder inner class, used to contain the Views that will be send to the RecyclerView
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //  Override the default onClick()
        @Override
        public void onClick(View view) {

            //  callback to the listener reference (takes the adapter position as an argument)
            myOnListItemClickListener.onListItemClick(getAdapterPosition());





                //view.findViewById(R.id.persons_list_item_container).setBackgroundColor(Color.parseColor("#FFB6C1"));


        }

        //  Needed Views for displaying their data
        TextView personName, personOccupation, personAddress;
        ImageView personImage;


        ViewHolder(View itemView){
            super(itemView);
            personName = itemView.findViewById(R.id.persons_list_item_textView_name);
            personOccupation = itemView.findViewById(R.id.persons_list_item_textView_occupation);
            personAddress = itemView.findViewById(R.id.persons_list_item_textView_address);
            personImage = itemView.findViewById(R.id.persons_list_item_image);
            itemView.setOnClickListener(this); // attach listener to the list to listen for any clicks


        }
    }


    //  Interface needed to observe the click events (the recycler view does not have a method for
    //  this type of functionality)
    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
