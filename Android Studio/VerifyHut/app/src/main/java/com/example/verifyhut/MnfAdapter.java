package com.example.verifyhut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MnfAdapter extends RecyclerView.Adapter<MnfAdapter.MnfViewHolder>{

    private Context context;
    private List<listMnf> mnfList;

    public MnfAdapter(Context context, List<listMnf> mnfList){
        this.context = context;
        this.mnfList = mnfList;
    }
    @Override
    public MnfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mnf_list, null);
        return new MnfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MnfAdapter.MnfViewHolder holder, int position) {
        final listMnf manufacturer = mnfList.get(position);

        holder.mID.setText(manufacturer.getSsmID());
        holder.mEmail.setText(manufacturer.getEmail());
        holder.mName.setText(manufacturer.getName());
        holder.mDesc.setText(manufacturer.getDescription());
        holder.mPhone.setText(manufacturer.getPhone());
        holder.mAddress.setText(manufacturer.getAddress());

    }

    @Override
    public int getItemCount() {
        return mnfList.size();
    }

    class MnfViewHolder extends RecyclerView.ViewHolder{
        TextView mID, mEmail, mName, mDesc, mPhone, mAddress;
        RelativeLayout relative;

        public MnfViewHolder(View itemView){
            super(itemView);
            mID = itemView.findViewById(R.id.viewMnfID);
            mEmail = itemView.findViewById(R.id.viewMnfEmail);
            mName = itemView.findViewById(R.id.viewMnfName);
            mDesc = itemView.findViewById(R.id.viewMnfDesc);
            mPhone = itemView.findViewById(R.id.viewMnfPhone);
            mAddress = itemView.findViewById(R.id.viewMnfAddress);
            relative = itemView.findViewById(R.id.relativeMnf);
        }
    }
}
