package com.androindian.finance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androindian.finance.databinding.ManlistBinding;

import java.util.List;

public class MadororyADP extends RecyclerView.Adapter<MadororyADP.MyViewHolder> {

    Context context;
    List<ManlistArray> adpdata;
    ManlistBinding binding;
    public MadororyADP(Mandory mandory, List<ManlistArray> data) {
        adpdata=data;
        context=mandory;
    }

    @NonNull
    @Override
    public MadororyADP.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding= DataBindingUtil.inflate(inflater,R.layout.manlist,null,false);


        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MadororyADP.MyViewHolder holder, int position) {
        holder.binding.textView.setText(adpdata.get(position).getId());
        holder.binding.textView2.setText(adpdata.get(position).getAmount());
        holder.binding.textView3.setText(adpdata.get(position).getDatetime());
        holder.binding.textView4.setText(adpdata.get(position).getEnddate());
        holder.binding.textView5.setText(adpdata.get(position).getReason());
        holder.binding.textView6.setText(adpdata.get(position).getStatus());
        //holder.binding.textView7.setText(adpdata.get(position).getStype());




    }

    @Override
    public int getItemCount() {
        return adpdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ManlistBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=DataBindingUtil.getBinding(itemView);
        }
    }
}
