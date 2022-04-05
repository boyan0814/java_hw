package com.example.android.waitlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.waitlist.data.WaitlistEntry;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    final private ItemClickListener mItemClickListener;

    private List<WaitlistEntry> mWaitlistEntries;
    private Context mContext;

    public TaskAdapter(Context context, ItemClickListener listener) {
        this.mContext = context;
        this.mItemClickListener = listener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.guest_list_item, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        // Determine the values of the wanted data
        WaitlistEntry taskEntry = mWaitlistEntries.get(position);

        String guestname = taskEntry.getGuest_name();
        String partysize = taskEntry.getGuest_party_size();

        //Set values
        holder.GuestNameView.setText(guestname);
        holder.GuestPartySizeView.setText(partysize);
    }

    @Override
    public int getItemCount() {
        if (mWaitlistEntries == null) {
            return 0;
        }
        return mWaitlistEntries.size();
    }

    public List<WaitlistEntry> getTasks() {
        return mWaitlistEntries;
    }

    public void setTasks(List<WaitlistEntry> taskEntries) { //設定進入後刷新變更
        mWaitlistEntries = taskEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description and priority TextViews
        TextView GuestNameView;
        TextView GuestPartySizeView;

        public TaskViewHolder(View itemView) {
            super(itemView);

            GuestNameView = (TextView) itemView.findViewById(R.id.name_text_view);
            GuestPartySizeView = (TextView) itemView.findViewById(R.id.party_size_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mWaitlistEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}