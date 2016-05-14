package com.kalis.beata.workmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.models.Task;

import java.util.List;

/**
 * Created by Beata on 2016-05-13.
 */
public class TaskAdapter  extends BaseAdapter {

    private List<Task> tasks;

    public TaskAdapter(List<Task> array) {
        tasks = array;
    }

    @Override
    public Task getItem(int position) {
        return tasks.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Task item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(item.getName());
        holder.date.setText(item.getDate());
        return convertView;
    }

    @Override
    public int getCount() {
        return tasks.size();
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
