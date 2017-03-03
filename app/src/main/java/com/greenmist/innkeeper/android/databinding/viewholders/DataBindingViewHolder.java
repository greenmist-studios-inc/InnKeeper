package com.greenmist.innkeeper.android.databinding.viewholders;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by geoff.powell on 2/18/17.
 */
public class DataBindingViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public DataBindingViewHolder(ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        binding = dataBinding;
    }

    public void bind(int variableId, Object object) {
        binding.setVariable(variableId, object);
        binding.executePendingBindings();
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
