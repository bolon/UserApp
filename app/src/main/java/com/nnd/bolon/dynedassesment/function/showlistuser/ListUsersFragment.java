package com.nnd.bolon.dynedassesment.function.showlistuser;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nnd.bolon.dynedassesment.R;
import com.nnd.bolon.dynedassesment.R2;
import com.nnd.bolon.dynedassesment.dependency.App;
import com.nnd.bolon.dynedassesment.dependency.data.user.User;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListUsersFragment extends Fragment {
    @Inject
    Realm realm;
    @BindView(R2.id.searchViewListUser)
    SearchView searchView;
    @BindView(R2.id.listUser)
    RecyclerView recyclerView;
    private OnListFragmentInteractionListener mListener;
    private List<User> userList = new ArrayList<>();
    private ListUsersRecyclerViewAdapter recyclerViewAdapter;

    public ListUsersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(getContext()).getInjector().inject(this);

        if (getArguments() != null) {
            //TODO : get data from activity. fragment only ui
        }
        userList = realm.copyFromRealm(realm.where(User.class).findAll());
        recyclerViewAdapter = new ListUsersRecyclerViewAdapter(userList, mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listusers_list, container, false);
        ButterKnife.bind(this, view);

        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search by Name");

        // Set the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .marginResId(R.dimen.activity_horizontal_margin, R.dimen.activity_horizontal_margin).build());
        recyclerView.setAdapter(recyclerViewAdapter);

        setSearchFunction();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setSearchFunction() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<User> filteredList = filter(newText);
                recyclerViewAdapter.animateTo(filteredList);
                recyclerView.scrollToPosition(0);

                return true;
            }
        });
    }

    private List<User> filter(String query) {
        query = query.toLowerCase();
        final List<User> filteredList = new ArrayList<>();
        for (User user : realm.where(User.class).findAll()) {
            final String text = user.getName().toLowerCase();
            if (text.contains(query)) {
                filteredList.add(user);
            }
        }
        return filteredList;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onItemClicked(User user);
    }
}
