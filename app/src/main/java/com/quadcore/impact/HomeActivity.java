package com.quadcore.impact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.quadcore.impact.databinding.ActivityHomeBinding;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // View Binding replaces findViewById and prevents null pointer crashes
    private ActivityHomeBinding binding;
    private FormAdapter formAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1. Setup the List (RecyclerView)
        setupRecyclerView();

        // 2. Setup the "Create" Action (Floating Action Button)
        binding.fabCreateForm.setOnClickListener(v -> {
            // This opens the slide-up modal we created earlier
            CreateFormModalFragment modal = new CreateFormModalFragment();
            modal.show(getSupportFragmentManager(), "CreateFormModal");
        });

        // 3. Setup Bottom Navigation logic (If-Else to prevent Gradle errors)
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // You are already here!
                Toast.makeText(this, "Home Screen", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_exit) {
                // Closes the app
                finishAffinity();
                return true;
            }

            return false;
        });
    }

    private void setupRecyclerView() {
        // Set layout manager (Vertical list)
        binding.formListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get dummy data for the QuadCore_023 project
        List<Form> formList = fetchInitialData();

        // Initialize and set the adapter
        formAdapter = new FormAdapter(formList);
        binding.formListRecyclerView.setAdapter(formAdapter);
    }

    private List<Form> fetchInitialData() {
        List<Form> data = new ArrayList<>();
        // Title, Description, Date
        data.add(new Form("Tech Impact Survey", "Collecting data on local tech usage", "Mar 25"));
        data.add(new Form("Community Feedback", "Feedback for Taguig City University", "Mar 24"));
        data.add(new Form("Nail Parlor Booking", "Client appointment records", "Mar 22"));
        return data;
    }
}