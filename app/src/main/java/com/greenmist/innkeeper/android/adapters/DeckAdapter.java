package com.greenmist.innkeeper.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.greenmist.innkeeper.android.BR;
import com.greenmist.innkeeper.android.databinding.ItemTestEntityBinding;
import com.greenmist.innkeeper.android.databinding.viewholders.DataBindingViewHolder;
import com.greenmist.innkeeper.model.entities.hs.HSEntity;
import com.greenmist.innkeeper.mvvm.MVVMHSScan;

/**
 * Created by geoff.powell on 2/8/17.
 */
public class DeckAdapter extends RecyclerView.Adapter<DataBindingViewHolder> {

    private int playerId;
    private MVVMHSScan.ViewModel viewModel;

    public DeckAdapter(MVVMHSScan.ViewModel viewModel, int playerId) {
        this.viewModel = viewModel;
        this.playerId = playerId;
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataBindingViewHolder(ItemTestEntityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public int getItemCount() {
        return viewModel.getDeckCount(playerId);
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        HSEntity hsEntity = viewModel.getEntityInDeck(playerId, position);
        holder.bind(BR.entity, hsEntity);
    }

}
