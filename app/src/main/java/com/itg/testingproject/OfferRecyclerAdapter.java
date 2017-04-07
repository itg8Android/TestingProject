package com.itg.testingproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class OfferRecyclerAdapter extends RecyclerView.Adapter<OfferRecyclerAdapter.ViewHolder>{
    private final Context context;
    private final int i;

    OfferRecyclerAdapter(Context context, int i) {
        this.context = context;
        this.i = i;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offers,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return i;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
