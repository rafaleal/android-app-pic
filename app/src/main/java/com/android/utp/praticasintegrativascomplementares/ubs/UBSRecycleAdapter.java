package com.android.utp.praticasintegrativascomplementares.ubs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.utp.praticasintegrativascomplementares.databinding.ItemUbsBinding;
import com.android.utp.praticasintegrativascomplementares.models.ubs.UBS;

import java.util.List;

public class UBSRecycleAdapter extends RecyclerView.Adapter<UBSRecycleAdapter.ViewHolder> {

    private List<UBS> mUBSList;

    private UBSAdapterListener ubsAdapterListener;

    public UBSRecycleAdapter(List<UBS> mUBSList, UBSAdapterListener listener) {
        this.mUBSList = mUBSList;
        this.ubsAdapterListener = listener;
    }

    public void add(int position, UBS item) {
        mUBSList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mUBSList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        ItemUbsBinding binding = ItemUbsBinding.inflate(inflater, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        UBS item = mUBSList.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mUBSList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemUbsBinding mBinding;

        public ViewHolder(ItemUbsBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(final UBS item) {
            mBinding.setUbs(item);
            mBinding.ubsContainer.setOnClickListener(view -> ubsAdapterListener.onUbsSelected(item));
            mBinding.executePendingBindings();
        }
    }

    public interface UBSAdapterListener {
        void onUbsSelected(UBS ubsItem);
    }
}
