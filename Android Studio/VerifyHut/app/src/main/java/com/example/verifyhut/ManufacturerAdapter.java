package com.example.verifyhut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ManufacturerAdapter extends RecyclerView.Adapter<ManufacturerAdapter.ManufacturerViewHolder> {

    //
    private LayoutInflater inflater;

    private Context context;
    private List<listManufacturer> manufacturerList;


    public ManufacturerAdapter(Context context, List<listManufacturer> manufacturerList){
        this.context = context;
        this.manufacturerList = manufacturerList;

    }
    @Override
    public ManufacturerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.manufacturer_list, null);
        return new ManufacturerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ManufacturerViewHolder holder, int position) {
        final listManufacturer manufacturer = manufacturerList.get(position);

        holder.mName.setText(manufacturer.getName());
        holder.mEmail.setText(manufacturer.getEmail());
        holder.mDesc.setText(manufacturer.getDescription());
        holder.mAddress.setText(manufacturer.getAddress());
        holder.mPhone.setText(manufacturer.getPhone());
        holder.mID.setText(manufacturer.getSsmID());
    }

    @Override
    public int getItemCount() {
        return manufacturerList.size();
    }


    @Nullable
    public Object getItem(int location){
        return manufacturerList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.product_list, null);

        TextView txt_name = (TextView) convertView.findViewById(R.id.mName);
        TextView txt_email = (TextView) convertView.findViewById(R.id.pBrand);
        TextView txt_description = (TextView) convertView.findViewById(R.id.pIngredient);
        TextView txt_address = (TextView) convertView.findViewById(R.id.pHalalCertified);
        TextView txt_phone = (TextView) convertView.findViewById(R.id.pDesc);
        TextView txt_nama = (TextView) convertView.findViewById(R.id.pID);

        txt_name.setText(manufacturerList.get(position).getName());
        txt_email.setText(manufacturerList.get(position).getEmail());
        txt_description.setText(manufacturerList.get(position).getDescription());
        txt_address.setText(manufacturerList.get(position).getAddress());
        txt_phone.setText(manufacturerList.get(position).getPhone());
        txt_nama.setText(manufacturerList.get(position).getSsmID());

        return convertView;
    }



    static class ManufacturerViewHolder extends RecyclerView.ViewHolder{
        TextView mName, mEmail, mDesc, mAddress, mPhone, mID;
        RelativeLayout relative;

        public ManufacturerViewHolder(View itemView){
            super(itemView);

            mName = itemView.findViewById(R.id.mName);
            mEmail = itemView.findViewById(R.id.mEmail);
            mDesc = itemView.findViewById(R.id.mDesc);
            mAddress = itemView.findViewById(R.id.mAddress);
            mPhone = itemView.findViewById(R.id.mPhone);
            mID = itemView.findViewById(R.id.mID);
            relative = itemView.findViewById(R.id.relative3);
        }
    }
}
