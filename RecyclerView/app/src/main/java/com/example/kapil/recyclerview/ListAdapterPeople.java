package com.example.kapil.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// for explaning baseAdapter concept now we have to use Recycler View to make our app more robust

public class ListAdapterPeople extends BaseAdapter {

    public static final String TAG = ListAdapterPeople.class.getSimpleName();

    List<Person> people;
    Context context;
    LayoutInflater layoutInflater;

    public ListAdapterPeople(Context context, List<Person> people){

        this.context =context;
        this.people= people;

    }


    @Override
    public int getCount() {
      return   people.size();
    }

    @Override
    public Object getItem(int position) {
       return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        PersonViewHolder personViewHolder;
        if(view == null){

            layoutInflater = LayoutInflater.from(this.context);
            view=layoutInflater.inflate(R.layout.layout_person_row_item,null);
            personViewHolder =new PersonViewHolder();
            personViewHolder.textViewName=((TextView)view.findViewById(R.id.textViewName));
            personViewHolder.textViewLastName=((TextView)view.findViewById(R.id.textViewLastName));
            personViewHolder.textViewGender=((TextView)view.findViewById(R.id.textViewGender));
            personViewHolder.textViewNationality=((TextView)view.findViewById(R.id.textViewNationality));

            view.setTag(personViewHolder);


        }

        else{

            personViewHolder= (PersonViewHolder) view.getTag();
            }
        // now in this code every time you scroll the new view gets created and increases the size in memory
        // so to get rid of it we have to check if the view is null or not if it is null then inflate the new view otherwise use the previously created view
       // layoutInflater = LayoutInflater.from(this.context); // this get hold of the view

        final Person person = people.get(position);// this get hold of every particular object of person in view
     //   view=layoutInflater.inflate(R.layout.layout_person_row_item,null);

        // now here again is problem of finding the ids and it has heavy cost
        // So to solve this problem we have to implement ViewHolder and ViewHolder is a class that holds the views and we have to only once find the ids
      /*  ((TextView)view.findViewById(R.id.textViewName)).setText(person.getName());
        ((TextView)view.findViewById(R.id.textViewLastName)).setText(person.getLastname());
        ((TextView)view.findViewById(R.id.textViewGender)).setText(person.getGender()==Person.GENDER.MALE?"Male":"Female");
        ((TextView)view.findViewById(R.id.textViewNationality)).setText(person.getNationality());   */

        personViewHolder.textViewName.setText(person.getName());
        personViewHolder.textViewLastName.setText(person.getLastname());
        personViewHolder.textViewGender.setText((person.getGender()== Person.GENDER.MALE?"Male":"Female"));
        personViewHolder.textViewNationality.setText(person.getNationality());






        Log.i(TAG,"Index:"+position+":"+view);

        return view;

    }


    // class viewholder to hold the views for every UI element
    private static class PersonViewHolder{

        public TextView textViewName;
        public TextView textViewLastName;
        public TextView textViewGender;
        public TextView textViewNationality;

    }










}
