package com.example.futurelove.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.futurelove.databinding.ItemRcvHistoryEventBinding;
import com.example.futurelove.model.EventHomeDto;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class EventHomeAdapter extends  RecyclerView.Adapter<EventHomeAdapter.EventHomeViewHolder>{

    private List<List<EventHomeDto>> eventList;
    public final IOnClickItemListener iOnClickItem;

    public void setData(List<List<EventHomeDto>> eventList) {
        this.eventList = eventList;
    }

    public EventHomeAdapter(List<List<EventHomeDto>> eventList, IOnClickItemListener iOnClickItem) {
        this.eventList = eventList;
        this.iOnClickItem = iOnClickItem;
    }
    public interface IOnClickItemListener {
        void onClickItem(long id);
    }

    @NonNull
    @Override
    public EventHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRcvHistoryEventBinding itemRcvHistoryEventBinding = ItemRcvHistoryEventBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EventHomeViewHolder(itemRcvHistoryEventBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHomeViewHolder holder, int position) {
        List<EventHomeDto> events = eventList.get(position);
        if (events==null||events.isEmpty())
            return;

//        ranom tu 2-4
        int number = new Random().nextInt(3)+2;
        if (events.get(number)==null)
            return;


        Glide.with(holder.itemView.getContext())
                .load(events.get(number).getLink_nam_goc())
                .into(holder.itemRcvHistoryEventBinding.imgPerson1);

        Glide.with(holder.itemView.getContext())
                .load(events.get(number).getLink_nu_goc())
                .into(holder.itemRcvHistoryEventBinding.imgPerson2);
        Picasso.get().load(events.get(number).getLink_da_swap()).into(holder.itemRcvHistoryEventBinding.imgContent);

        int commaIndex = events.get(number).getReal_time().indexOf(",");
        String date = events.get(number).getReal_time().substring(0, commaIndex);
        holder.itemRcvHistoryEventBinding.tvContent.setText(events.get(number).getTen_su_kien());
        holder.itemRcvHistoryEventBinding.tvDate.setText(date);

        holder.itemRcvHistoryEventBinding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickItem.onClickItem(events.get(number).getId_toan_bo_su_kien());
            }
        });

    }

    @Override
    public int getItemCount() {
        return null == eventList?0:eventList.size();
    }
    public static class EventHomeViewHolder extends RecyclerView.ViewHolder{
        private final ItemRcvHistoryEventBinding itemRcvHistoryEventBinding;
        public EventHomeViewHolder(ItemRcvHistoryEventBinding itemRcvHistoryEventBinding) {
            super(itemRcvHistoryEventBinding.getRoot());
            this.itemRcvHistoryEventBinding = itemRcvHistoryEventBinding;
        }
    }

}
