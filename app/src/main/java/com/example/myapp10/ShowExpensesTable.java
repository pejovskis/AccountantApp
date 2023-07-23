package com.example.myapp10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowExpensesTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowExpensesTable extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowExpensesTable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowExpensesTable.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowExpensesTable newInstance(String param1, String param2) {
        ShowExpensesTable fragment = new ShowExpensesTable();
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
        View view = inflater.inflate(R.layout.fragment_show_expenses_table, container, false);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        // Retrieve and display the expenses
        retrieveAndDisplayExpenses(view);

        return view;
    }

    private void retrieveAndDisplayExpenses(View view) {
        DatabaseReference expensesRef = FirebaseDatabase.getInstance().getReference("expenses");
        expensesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear any previous data from the table
                TableLayout tableLayoutExpenses = view.findViewById(R.id.tableLayoutExpenses);
                tableLayoutExpenses.removeAllViews();

                // Loop through the children of the "expenses" node
                for (DataSnapshot expenseSnapshot : dataSnapshot.getChildren()) {
                    // Retrieve the expense object from the snapshot
                    Expense expense = expenseSnapshot.getValue(Expense.class);

                    // Add the expense data to the table
                    addExpenseToTable(tableLayoutExpenses, expense);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that may occur while retrieving data
            }
        });
    }

    private void addExpenseToTable(TableLayout tableLayout, Expense expense) {
        // Create a new TableRow
        TableRow row = new TableRow(requireContext());

        // Create TextViews to display each attribute of the expense
        TextView tvId = createTextView(expense.getId());
        TextView tvWhere = createTextView(expense.getWhere());
        TextView tvEssentials = createTextView(expense.getEssentials());
        TextView tvCategory = createTextView(expense.getCategory());
        TextView tvDate = createTextView(expense.getDate());
        TextView tvPrice = createTextView(expense.getPrice());

        // Add TextViews to the TableRow
        row.addView(tvId);
        row.addView(tvWhere);
        row.addView(tvEssentials);
        row.addView(tvCategory);
        row.addView(tvDate);
        row.addView(tvPrice);

        // Add the TableRow to the TableLayout
        tableLayout.addView(row);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(requireContext());
        textView.setText(text);
        textView.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));
        return textView;
    }
}