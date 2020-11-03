package com.voytasic.navigationcomponents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class ChooseRecipientFragment extends Fragment implements View.OnClickListener{
    private NavController navController = null;
    private String recipient = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.next_btn).setOnClickListener(this);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())  {
            case R.id.next_btn: {
                EditText recipientTv = (EditText) getView().findViewById(R.id.input_recipient);
                recipient = recipientTv.getText().toString();
                if (!TextUtils.isEmpty(recipientTv.getText().toString())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("recipient",recipient);
                    navController.navigate(R.id.action_chooseRecipientFragment_to_specifyAmountFragment,bundle);
                } else Toast.makeText(getActivity(),"Enter a recipient",Toast.LENGTH_SHORT).show();
            } break;
            case R.id.cancel_btn: {
                getActivity().onBackPressed();
            } break;
        }

    }
}