package com.kalis.beata.workmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.models.Event;

import java.util.List;

/**
 * Created by Beata on 2016-05-13.
 */
public class EventAdapter extends BaseAdapter {

    private List<Event> events;

    public EventAdapter(List<Event> array) {
        events = array;
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Event item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(item.getName());
        holder.date.setText(item.getEndDate());
        return convertView;
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView name;
        TextView date;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.nameTextView);
            date = (TextView)convertView.findViewById(R.id.dateTextView);
        }
    }
}
