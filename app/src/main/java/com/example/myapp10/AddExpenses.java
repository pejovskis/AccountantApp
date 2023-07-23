package com.example.myapp10;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExpenses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpenses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddExpenses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExpenses.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExpenses newInstance(String param1, String param2) {
        AddExpenses fragment = new AddExpenses();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expenses, container, false);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnAddExpenses = view.findViewById(R.id.btnAddExpenses);
        btnCancel.setOnClickListener(view2 -> Navigation.findNavController(view).navigateUp());
        btnAddExpenses.setOnClickListener(view3 -> addExpense(view));


        return view;
    }

    public void addExpense(View view) {
        int id = 2;
        EditText inWhere = view.findViewById(R.id.inWhere);
        EditText inCategory = view.findViewById(R.id.inCategory);
        EditText inPrice = view.findViewById(R.id.inPrice);

        // Radio Btn
        RadioGroup inRadioGroup = view.findViewById(R.id.radioGroupOptions);
        int selectedRadioBtnId = inRadioGroup.getCheckedRadioButtonId();
        String inEssentials = "not provided";
        if (selectedRadioBtnId != -1) {
            RadioButton selectedRadioBtn = view.findViewById(selectedRadioBtnId);
            if (selectedRadioBtn != null) {
                inEssentials = selectedRadioBtn.getText().toString();
            }
        }

        // Date Picker
        DatePicker datePicker = view.findViewById(R.id.inDate);
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int dayOfMonth = datePicker.getDayOfMonth();
        String dateIn = dayOfMonth + "." + (month + 1) + "." + year; // Month is zero-based, so add 1
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        Date selectedTime = calendar.getTime();

        TextView consoleOutput = view.findViewById(R.id.consoleOutput);

        consoleOutput.setText(inWhere.getText() + " " + inCategory.getText() + " " + dateIn + " " + inPrice.getText() + " " + inEssentials);

        // Expense Object generate
        Expense newExpense = new Expense(id, inWhere.getText().toString(), inCategory.getText().toString(), inEssentials, selectedTime, Integer.parseInt(inPrice.getText().toString()));

        //Insert to DB
        // Insert data
        ContentValues contentValues = new ContentValues();
        contentValues.put("where", "Grocery Store");
        contentValues.put("category", "Food");
        contentValues.put("essentials", "Yes");
        contentValues.put("date", System.currentTimeMillis()); // or use a Date object
        contentValues.put("price", 100.0);
        //long newRowId = db.insert("expenses", null, contentValues);

    }

}