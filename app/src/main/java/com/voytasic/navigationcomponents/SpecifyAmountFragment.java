package com.voytasic.navigationcomponents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;


public class SpecifyAmountFragment extends Fragment implements View.OnClickListener{
    private NavController navController= null;
    private String recipient = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipient = getArguments().getString("recipient");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.send_btn).setOnClickListener(this);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);
        String msg = "Sending money to " + recipient;
        ((TextView) view.findViewById(R.id.recipient)).setText(msg);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())  {
            case R.id.send_btn: {
                EditText amountTv = (EditText) getView().findViewById(R.id.input_amount);

                String amountStr = amountTv.getText().toString();
                Log.d("Amount",amountStr);
                if (!TextUtils.isEmpty(amountStr)) {
                    Bundle bundle = new Bundle();
                    Money amount = new Money(new BigDecimal(amountStr));
                    bundle.putString("recipient",recipient);
                    bundle.putParcelable("amount",amount);

                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle);
                } else Toast.makeText(getActivity(),"Enter an amount",Toast.LENGTH_SHORT).show();

            } break;
            case R.id.cancel_btn: {
                getActivity().onBackPressed();
            } break;
        }
    }
}