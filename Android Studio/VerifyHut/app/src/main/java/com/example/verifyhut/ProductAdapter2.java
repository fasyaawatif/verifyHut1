package com.example.verifyhut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    //
    private LayoutInflater inflater;

    private Context mCtx;
    private List<listProduct2> productList;


    public ProductAdapter2(Context mCtx, List<listProduct2> productList){
        this.mCtx = mCtx;
        this.productList = productList;


    }

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list2,null);
        return new ProductAdapter.ProductViewHolder(view);
    }

    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        final listProduct2 product = productList.get(position);

        //loading the image
        //Glide.with(mCtx)
        //.load(product.getImage())
        //.into(holder.imageView);

        holder.pName.setText(product.getProductName());
        holder.pBrand.setText(product.getBrand());
        holder.pIngredient.setText(product.getIngredient());
        holder.pHalalCertified.setText(product.getHalalCertified());
        holder.pDesc.setText(product.getProductDesc());
        holder.pType.setText(product.getType());
        holder.pID.setText(product.getProductID());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    @Nullable
    public Object getItem(int location){
        return productList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) mCtx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.product_list2, null);

        TextView txt_name = (TextView) convertView.findViewById(R.id.pName);
        TextView txt_brand = (TextView) convertView.findViewById(R.id.pBrand);
        TextView txt_ingredient = (TextView) convertView.findViewById(R.id.pIngredient);
        TextView txt_halal = (TextView) convertView.findViewById(R.id.pHalalCertified);
        TextView txt_desc = (TextView) convertView.findViewById(R.id.pDesc);
        TextView txt_type = (TextView) convertView.findViewById(R.id.pType);
        TextView txt_nama = (TextView) convertView.findViewById(R.id.pID);

        txt_name.setText(productList.get(position).getProductName());
        txt_brand.setText(productList.get(position).getBrand());
        txt_ingredient.setText(productList.get(position).getIngredient());
        txt_halal.setText(productList.get(position).getHalalCertified());
        txt_desc.setText(productList.get(position).getProductDesc());
        txt_type.setText(productList.get(position).getType());
        txt_nama.setText(productList.get(position).getProductID());

        return convertView;
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView pName, pBrand, pIngredient, pHalalCertified, pDesc, pType, pID;
        //ImageView imageView;
        RelativeLayout relative;

        public ProductViewHolder(View itemView) {
            super(itemView);

            pName = itemView.findViewById(R.id.pName);
            pBrand = itemView.findViewById(R.id.pBrand);
            pIngredient = itemView.findViewById(R.id.pIngredient);
            pHalalCertified = itemView.findViewById(R.id.pHalalCertified);
            pDesc = itemView.findViewById(R.id.pDesc);
            pType = itemView.findViewById(R.id.pType);
            pID = itemView.findViewById(R.id.pID);
            //imageView = itemView.findViewById(R.id.imageView);
            relative = itemView.findViewById(R.id.relativeCategory);

        }
    }

}