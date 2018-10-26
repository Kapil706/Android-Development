package com.example.kapil.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ListAdapterWithRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


     public interface PersonModifier{

         public void onPersonSelected(int position);
         public void onPersonDeleted(int position);

         }


    private static final String TAG = ListAdapterWithRecycleView.class.getSimpleName();
    private List<Object> catalogue;
   // private List<Person> personList;
    private Context context;

    private PersonModifier personModifier;

//    public ListAdapterWithRecycleView(Context context,List<Person> personList){
//
//        this.context=context;
//        this.personList=personList;
//
//
//    }

    public ListAdapterWithRecycleView(Context context,List<Object> catalogue){
        this.catalogue = catalogue;
        this.context=context;
    }

    public void setPersonModifier(PersonModifier personModifier) {
        this.personModifier = personModifier;
    }

    @Override
    public int getItemViewType(int position) {
//        if(position % 3==0){
//            return R.layout.layout_advertisement_row_item;
//        }
//        else{
//            return R.layout.layout_person_row_item;
//        }

        if(catalogue.get(position) instanceof Advertisement){
            return R.layout.layout_advertisement_row_item;
        } else if (catalogue.get(position) instanceof Person) {
            return R.layout.layout_person_row_item;
        }else{
            return -1;
        }



    }

    @NonNull
    @Override

    // For inflation of the view of over screen
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_person_row_item,parent,false);
//        final PersonViewHolder personViewHolder = new PersonViewHolder(view);

        final RecyclerView.ViewHolder holder;
        View view;
        switch(viewType) {
            case R.layout.layout_advertisement_row_item:

                view = LayoutInflater.from(context).inflate(R.layout.layout_advertisement_row_item, parent, false);
                holder = new AdvertisementHolder(view);
                break;

            case R.layout.layout_person_row_item:
                view = LayoutInflater.from(context).inflate(R.layout.layout_person_row_item, parent, false);
                holder = new PersonViewHolder(view);


                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getAdapterPosition();
                        Toast.makeText(context, "Item at position" + position + "deleted", Toast.LENGTH_SHORT).show();
                      //  personList.remove(position);
                        catalogue.remove(position);
                        notifyDataSetChanged();
                        if (personModifier != null) {

                            personModifier.onPersonDeleted(position);


                        }
                        return true;
                    }

                });

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (personModifier != null) {

                            personModifier.onPersonSelected(holder.getAdapterPosition());


                        }
                    }
                });
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.layout_advertisement_row_item, parent, false);
                holder = new AdvertisementHolder(view);
                break;
        }
        Log.i(TAG,"onCreateViewHolder Invoked");
        return holder ;


    }



    @Override

    // for binding the view to ui widget
    public void onBindViewHolder(final  RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PersonViewHolder) {
            final Person person = (Person) catalogue.get(position);
//        personViewHolder.textViewName.setText(person.getName());
//        personViewHolder.textViewLastName.setText(person.getLastname());
//        personViewHolder.textViewNationality.setText(person.getNationality());
//        personViewHolder.textViewGender.setText(person.getGender()==Person.GENDER.MALE?"Male":"Female");
//        personViewHolder.textViewNationality.setEnabled(true);
//
//        personViewHolder.textViewGender.setEnabled(true);


            ((PersonViewHolder) holder).textViewName.setText(person.getName());
            ((PersonViewHolder) holder).textViewLastName.setText(person.getLastname());
            ((PersonViewHolder) holder).textViewNationality.setText(person.getNationality());
            ((PersonViewHolder) holder).textViewGender.setText(person.getGender() == Person.GENDER.MALE ? "Male" : "Female");
            ((PersonViewHolder) holder).textViewGender.setEnabled(true);
            ((PersonViewHolder) holder).textViewGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Person.GENDER gender = ((Person)catalogue.get(holder.getAdapterPosition())).getGender();
                    if (gender == Person.GENDER.MALE) {
                        ((Person)catalogue.get(holder.getAdapterPosition())).setGender(Person.GENDER.FEMALE);
                    } else if (gender == Person.GENDER.FEMALE) {
                        ((Person)catalogue.get(holder.getAdapterPosition())).setGender(Person.GENDER.MALE);
                    }
                    notifyItemChanged(holder.getAdapterPosition());
                }
            });

        }else if(holder instanceof AdvertisementHolder){

            ((AdvertisementHolder)holder).textViewAdvertMessage.setText(((Advertisement)catalogue.get(position)).getAdMessage());
            ((AdvertisementHolder)holder).textViewAdvertMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Advertisement not available",Toast.LENGTH_LONG).show();
                }
            });
        }
        Log.i(TAG,"onBindViewHolder invoked: "+position);

        }

    @Override
    public int getItemCount() {
        //return personList.size();
        return catalogue.size();
    }


    class PersonViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewLastName;
        public TextView textViewGender;
        public TextView textViewNationality;

        public PersonViewHolder(View view){

            super(view);
            textViewName = view.findViewById(R.id.textViewName);
            textViewLastName = view.findViewById(R.id.textViewLastName);
            textViewGender = view.findViewById(R.id.textViewGender);
            textViewNationality = view.findViewById(R.id.textViewNationality);




        }


    }


    class AdvertisementHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAdvertisementBanner;
        public TextView textViewAdvertMessage;

        public AdvertisementHolder(View view){
            super(view);
            imageViewAdvertisementBanner = (ImageView)view.findViewById(R.id.imageViewAdvertisementBanner);
            textViewAdvertMessage = (TextView)view.findViewById(R.id.textViewAdvertMessage);
        }
    }




}
