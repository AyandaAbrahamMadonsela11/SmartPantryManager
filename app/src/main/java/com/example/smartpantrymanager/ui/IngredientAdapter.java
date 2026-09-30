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
        extends RecyclerView.Adapter<
        IngredientAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredientList;

    private final OnIngredientActionListener listener;

    // Creating the ingredient adapter
    public IngredientAdapter(
            List<Ingredient> ingredientList,
            OnIngredientActionListener listener) {

        this.ingredientList =
                ingredientList;

        this.listener =
                listener;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        // Creating the ingredient item
        View view =
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                        R.layout.item_ingredient,
                        parent,
                        false
                );

        return new IngredientViewHolder(
                view
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(
            @NonNull IngredientViewHolder holder,
            int position) {

        Ingredient ingredient =
                ingredientList.get(
                        position
                );

        // Showing the ingredient name
        holder.txtIngredientName.setText(
                ingredient.getName()
        );

        // Showing the quantity and unit
        holder.txtIngredientQuantity.setText(
                "Quantity: " +
                        ingredient.getQuantity() +
                        " " +
                        ingredient.getUnit()
        );

        // Showing the expiry date
        holder.txtIngredientExpiry.setText(
                "Expiry Date: " +
                        ingredient.getExpiryDate()
        );

        // Editing the ingredient
        holder.btnEdit.setOnClickListener(
                v -> {

                    if (listener != null) {

                        listener.onEdit(
                                ingredient
                        );
                    }
                }
        );

        // Deleting the ingredient
        holder.btnDelete.setOnClickListener(
                v -> {

                    if (listener != null) {

                        listener.onDelete(
                                ingredient
                        );
                    }
                }
        );

        // Opening the edit screen
        holder.itemView.setOnClickListener(
                v -> {

                    if (listener != null) {

                        listener.onEdit(
                                ingredient
                        );
                    }
                }
        );
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(
            List<Ingredient> newList) {

        this.ingredientList =
                newList;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return ingredientList.size();
    }

    public interface OnIngredientActionListener {

        void onEdit(
                Ingredient ingredient
        );

        void onDelete(
                Ingredient ingredient
        );
    }

    public static class IngredientViewHolder
            extends RecyclerView.ViewHolder {

        TextView txtIngredientName;
        TextView txtIngredientQuantity;
        TextView txtIngredientExpiry;

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

            txtIngredientExpiry =
                    itemView.findViewById(
                            R.id.txtIngredientExpiry
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