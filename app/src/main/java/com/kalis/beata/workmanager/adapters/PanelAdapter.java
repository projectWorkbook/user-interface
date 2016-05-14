package com.kalis.beata.workmanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kalis.beata.workmanager.R;
import com.kalis.beata.workmanager.models.MenuOption;

import java.util.List;

/**
 * Created by Beata on 2016-05-10.
 */
public class PanelAdapter  extends BaseAdapter {

    private List<MenuOption> options;

    public PanelAdapter(List<MenuOption> array) {
        options = array;
    }

    @Override
    public MenuOption getItem(int position) {
        return options.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MenuOption item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.panel_row, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(item.getName());

        return convertView;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView name;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.menuOptionName);

        }
    }
}
