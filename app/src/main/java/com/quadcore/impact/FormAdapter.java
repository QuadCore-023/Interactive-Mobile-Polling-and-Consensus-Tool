package com.quadcore.impact;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.quadcore.impact.databinding.ItemFormBinding;
import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.FormViewHolder> {

    private final List<Form> formList;

    public FormAdapter(List<Form> formList) {
        this.formList = formList;
    }

    @NonNull
    @Override
    public FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFormBinding binding = ItemFormBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new FormViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FormViewHolder holder, int position) {
        Form form = formList.get(position);
        holder.binding.textTitle.setText(form.getTitle());
        holder.binding.textDescription.setText(form.getDescription());
        holder.binding.textDate.setText(form.getDateCreated());
    }

    @Override
    public int getItemCount() {
        return formList.size();
    }

    static class FormViewHolder extends RecyclerView.ViewHolder {
        ItemFormBinding binding;
        FormViewHolder(ItemFormBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}