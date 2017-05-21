package de.htw_berlin.katharinapapke.feelfreetotouchapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.R;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.dummy.DummyContent.ArtistListItem;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.fragments.ArtistListItemFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ArtistListItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyArtistListItemRecyclerViewAdapter extends RecyclerView.Adapter<MyArtistListItemRecyclerViewAdapter.ViewHolder> {

    private final List<ArtistListItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyArtistListItemRecyclerViewAdapter(List<ArtistListItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artistlistitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        // ID und Texte werden gesetzt
        holder.mIdView.setText(mValues.get(position).id);
        holder.artistText.setText(mValues.get(position).content);
        //holder.artistObjectinList.setImageDrawable(double);

        //Pictures from Server/Web would be loaded - now only drawables will be loaded
        /*String url = holder.mItem.details;
        Log.i(url, "onBindViewHolder: ");*/

        Context context = holder.artistObjectinList.getContext();
        Picasso.with(context).load(R.drawable.mainpicture).placeholder(R.drawable.artist_picture)
                .error(R.drawable.artist_picture).into(holder.artistObjectinList);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView artistText;
        public ArtistListItem mItem;
        public final ImageView artistObjectinList;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            artistObjectinList = (ImageView) view.findViewById(R.id.artistObjectinList);
            mIdView = (TextView) view.findViewById(R.id.artistList_Id);
            artistText = (TextView) view.findViewById(R.id.artistList_infoText);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + artistText.getText() + "'";
        }
    }
}
