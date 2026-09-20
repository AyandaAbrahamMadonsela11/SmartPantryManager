package com.example.smartpantrymanager.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;

import java.util.List;

public class IngredientAdapter
        extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;

    private final OnIngredientActionListener listener;

    public interface OnIngredientActionListener {

        void onEdit(Ingredient ingredient);

        void onDelete(Ingredient ingredient);
    }

    public IngredientAdapter(
            List<Ingredient> ingredientList,
            OnIngredientActionListener listener) {

        this.ingredientList = ingredientList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(
                parent.getContext()
        ).inflate(
                R.layout.item_ingredient,
                parent,
                false
        );

        return new IngredientViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(
            @NonNull IngredientViewHolder holder,
            int position) {

        Ingredient ingredient =
                ingredientList.get(position);

        holder.txtIngredientName.setText(
                ingredient.getName()
        );

        holder.txtIngredientQuantity.setText(
                "Quantity: " + ingredient.getQuantity()
        );

        holder.btnEdit.setOnClickListener(v -> {

            if (listener != null) {
                listener.onEdit(ingredient);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {

            if (listener != null) {
                listener.onDelete(ingredient);
            }
        });

        holder.itemView.setOnClickListener(v -> {

            if (listener != null) {
                listener.onEdit(ingredient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(
            List<Ingredient> newList) {

        this.ingredientList = newList;

        notifyDataSetChanged();
    }

    public static class IngredientViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtIngredientName;
        TextView txtIngredientQuantity;
        Button btnEdit;
        Button btnDelete;

        public IngredientViewHolder(
                @NonNull View itemView) {

            super(itemView);

            txtIngredientName =
                    itemView.findViewById(
                            R.id.txtIngredientName
                    );

            txtIngredientQuantity =
                    itemView.findViewById(
                            R.id.txtIngredientQuantity
                    );

            btnEdit =
                    itemView.findViewById(
                            R.id.btnEdit
                    );

            btnDelete =
                    itemView.findViewById(
                            R.id.btnDelete
                    );
        }
    }
}