package com.quadcore.impact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.quadcore.impact.databinding.FragmentCreateFormModalBinding;

public class CreateFormModalFragment extends BottomSheetDialogFragment {
    private FragmentCreateFormModalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateFormModalBinding.inflate(inflater, container, false);

        binding.btnSubmitForm.setOnClickListener(v -> {
            // Add your logic to save the form data here
            dismiss();
        });

        return binding.getRoot();
    }
}