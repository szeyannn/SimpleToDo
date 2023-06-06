package sg.edu.rp.c346.id22026408.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import android.text.TextUtils;

public class MainActivity extends AppCompatActivity {


    EditText etTask;
    Button btnAdd,btnDel, btnClear;
    ListView lvTask;

    Spinner spn;
    ArrayList<String> alTask;

    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.taskList);
        spn = findViewById(R.id.spinner);

        alTask = new ArrayList<>();


        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Task = etTask.getText().toString();
                alTask.add(Task);
                aaTask.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTask.isEmpty()){
                    Toast.makeText(getApplicationContext(),"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }
                else {
                    String input = etTask.getText().toString().trim();
                    if (!TextUtils.isEmpty(input) && TextUtils.isDigitsOnly(input)) {
                        int pos = Integer.parseInt(etTask.getText().toString());
                        for (int i = 0; i < alTask.size(); i++) {
                            if (pos == i) {
                                alTask.remove(pos);
                                aaTask.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Index", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "Please enter valid input", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etTask.setHint("Type a new task here");
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;


                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
