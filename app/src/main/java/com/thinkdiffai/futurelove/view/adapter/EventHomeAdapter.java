package com.thinkdiffai.futurelove.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thinkdiffai.futurelove.databinding.ItemRcvHistoryEventBinding;
import com.thinkdiffai.futurelove.model.Comon;
import com.thinkdiffai.futurelove.model.EventHomeDto;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class EventHomeAdapter extends  RecyclerView.Adapter<EventHomeAdapter.EventHomeViewHolder>{

    private List<List<EventHomeDto>> eventList;
    public final IOnClickItemListener iOnClickItem;

    public void setData(List<List<EventHomeDto>> eventList) {
        this.eventList = eventList;
    }

    Context context;
    public EventHomeAdapter(List<List<EventHomeDto>> eventList, IOnClickItemListener iOnClickItem, Context context) {
        this.eventList = eventList;
        this.iOnClickItem = iOnClickItem;
        this.context = context;
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

//        random tu 2-4
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
        //Toast.makeText(context,  events.get(number).getTom_Luoc_Text(), Toast.LENGTH_SHORT).show();

        holder.itemRcvHistoryEventBinding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickItem.onClickItem(events.get(number).getId_toan_bo_su_kien());
                Comon.link_nam_chua_swap=events.get(number).getLink_nam_chua_swap();
                Comon.link_nam_goc=events.get(number).getLink_nam_goc();
                Comon.link_nu_chua_swap=events.get(number).getLink_nu_chua_swap();
                Comon.link_nu_goc=events.get(number).getLink_nu_goc();
                Comon.tom_Luoc_Text=events.get(number).getTom_Luoc_Text();
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
