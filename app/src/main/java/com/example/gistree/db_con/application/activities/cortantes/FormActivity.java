package com.example.gistree.db_con.application.activities.cortantes;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.gistree.db_con.R;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class FormActivity extends AppCompatActivity implements VerticalStepperForm {

    VerticalStepperFormLayout verticalStepperForm;
    EditText name;
    EditText name1;
    EditText name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        String[] mySteps = {"Name", "Email", "Phone Number"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();

    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createNameStep();
                break;
            case 1:
                view = createEmailStep();
                break;
            case 2:
                view = createPhoneNumberStep();
                break;
        }
        return view;
    }

    //----------------------------------------------------------------------------------------------
    private View createNameStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        name = new EditText(this);
        name.setSingleLine(true);
        name.setHint("Your name");

        return name;
    }

    private View createEmailStep() {
        // In this case we generate the view by inflating a XML file
//        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
//        LinearLayout emailLayoutContent = (LinearLayout) inflater.inflate(R.layout.email_step_layout, null, false);
//        email = (EditText) emailLayoutContent.findViewById(R.id.email);
//
//        return emailLayoutContent;
//        name = new EditText(this);
//        name.setSingleLine(true);
//        name.setHint("Your name");

        name1 = new EditText(this);
        name1.setSingleLine(true);
        name1.setHint("Your name");

        return name1;
    }

    private View createPhoneNumberStep() {
//        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
//        LinearLayout phoneLayoutContent = (LinearLayout) inflater.inflate(R.layout.phone_step_layout, null, false);
//
//        return phoneLayoutContent;

//        name = new EditText(this);
//        name.setSingleLine(true);
//        name.setHint("Your name");

        name2 = new EditText(this);
        name2.setSingleLine(true);
        name2.setHint("Your name");

        return name2;
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                checkName();
                name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        checkName();
                    }
                });
                break;
            case 1:
                checkName();
                break;
            case 2:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(2);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
        }
    }

    private void checkName() {
        if(name.length() >= 3 && name.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        } else {
            String errorMessage = "The name must have between 3 and 40 characters";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }
    }

    @Override
    public void sendData() {

    }
}
