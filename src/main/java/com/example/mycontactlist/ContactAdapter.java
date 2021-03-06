package com.example.mycontactlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter
{
    private ArrayList<String> contactData;
    private View.OnClickListener onClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewContact;
        public ContactViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName);
            textViewContact.setOnClickListener(onClickListener);
        }
        public TextView getTextViewContact()
        {
            return textViewContact;
        }
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        onClickListener = listener;
    }

    public ContactAdapter(ArrayList<String>arrayList)
    {
        contactData = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view,parent,false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getTextViewContact().setText(contactData.get(position));
    }

    @Override
    public int getItemCount()
    {
        return contactData.size();
    }
}
