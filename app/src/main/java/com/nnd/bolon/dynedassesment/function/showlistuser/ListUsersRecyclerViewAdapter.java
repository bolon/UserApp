package com.nnd.bolon.dynedassesment.function.showlistuser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.dependency.data.user.User;

import java.util.List;

public class ListUsersRecyclerViewAdapter extends RecyclerView.Adapter<ListUsersRecyclerViewAdapter.ViewHolder> {

    private final List<User> listUser;
    private final ListUsersFragment.OnListFragmentInteractionListener mListener;

    public ListUsersRecyclerViewAdapter(List<User> items, ListUsersFragment.OnListFragmentInteractionListener listener) {
        listUser = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_listusers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = listUser.get(position);
        holder.name.setText(listUser.get(position).getName());

        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onItemClicked(holder.user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public void animateTo(List<User> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<User> newModels) {
        for (int i = listUser.size() - 1; i >= 0; i--) {
            final User model = listUser.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<User> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final User model = newModels.get(i);
            if (!listUser.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<User> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final User model = newModels.get(toPosition);
            final int fromPosition = listUser.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public void removeItem(int position) {
        listUser.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int position, User model) {
        listUser.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final User model = listUser.remove(fromPosition);
        listUser.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name;
        public User user;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }
}
