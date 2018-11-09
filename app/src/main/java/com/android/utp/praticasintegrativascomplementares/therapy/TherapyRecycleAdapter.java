package com.android.utp.praticasintegrativascomplementares.therapy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.ItemTherapyBinding;
import com.android.utp.praticasintegrativascomplementares.model.TherapyItem;

import java.util.List;

public class TherapyRecycleAdapter extends RecyclerView.Adapter<TherapyRecycleAdapter.ViewHolder> {

    private List<TherapyItem> mTherapies;

    private TherapyAdapterListener therapyAdapterListener;

    public TherapyRecycleAdapter(List<TherapyItem> therapies, TherapyAdapterListener listener) {
        mTherapies = therapies;
        therapyAdapterListener = listener;
    }

    public void add(int position, TherapyItem item) {
        mTherapies.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mTherapies.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Define the layout which will be displayed for each item from RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());

        ItemTherapyBinding binding  = ItemTherapyBinding.inflate(inflater, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        TherapyItem item = mTherapies.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mTherapies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTherapyBinding mBinding;

        public ViewHolder(ItemTherapyBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(TherapyItem item) {
            mBinding.listTherapyIcon.setImageResource(R.drawable.ic_logo_colorful);
            mBinding.setTherapy(item);
            mBinding.executePendingBindings();
        }
    }

    public interface TherapyAdapterListener {
        void onPhotoClick(TherapyItem therapyItem);
        void onTherapySelected(TherapyItem therapyItem);
        void onButtonShare(String name, String description);
    }
}
