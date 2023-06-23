package com.example.futurelove.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futurelove.databinding.ItemTimelineEventBinding;
import com.example.futurelove.model.EventHomeDto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventTimelineAdapter extends RecyclerView.Adapter<EventTimelineAdapter.EventTimelineViewHolder> {
    private List<EventHomeDto> eventList;


    public void setData(List<EventHomeDto> eventList) {
        this.eventList = eventList;
    }

    public EventTimelineAdapter(List<EventHomeDto> eventList, IOnClickAddEventListener iOnClickAddEvent) {
        this.eventList = eventList;
        this.iOnClickAddEvent = iOnClickAddEvent;
    }

    public final IOnClickAddEventListener iOnClickAddEvent;
    public interface IOnClickAddEventListener {
        void onClickAddEvent(long id_summary_event, long id_event);
    }

    @NonNull
    @Override
    public EventTimelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimelineEventBinding itemTimelineEventBinding = ItemTimelineEventBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EventTimelineAdapter.EventTimelineViewHolder(itemTimelineEventBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventTimelineViewHolder holder, int position) {
        EventHomeDto event = eventList.get(position);
        if (event==null)
            return;

        Picasso.get().load(event.getLink_da_swap()).into(holder.itemTimelineEventBinding.imgContent);

        int commaIndex = event.getReal_time().indexOf(",");
        String date = event.getReal_time().substring(0, commaIndex);
        holder.itemTimelineEventBinding.tvContent.setText(event.getNoi_dung_su_kien());
        holder.itemTimelineEventBinding.tvDate.setText(date);
        holder.itemTimelineEventBinding.btnAddEvnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickAddEvent.onClickAddEvent(event.getId_toan_bo_su_kien(), event.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return null == eventList?0:eventList.size();
    }

    public static class EventTimelineViewHolder extends RecyclerView.ViewHolder{
        private final ItemTimelineEventBinding itemTimelineEventBinding;
        public EventTimelineViewHolder(ItemTimelineEventBinding itemTimelineEventBinding) {
            super(itemTimelineEventBinding.getRoot());
            this.itemTimelineEventBinding = itemTimelineEventBinding;
        }
    }
}
