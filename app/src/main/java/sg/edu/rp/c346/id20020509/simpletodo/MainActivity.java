package sg.edu.rp.c346.id20020509.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btAdd, btDelete, btClear;
    ListView lvTask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;
    Spinner spnAddRemove;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextTask);
        btAdd = findViewById(R.id.buttonAddTask);
        btDelete = findViewById(R.id.buttonDeleteTask);
        btClear = findViewById(R.id.buttonClearTasks);
        lvTask = findViewById(R.id.listViewTask);
        spnAddRemove = findViewById(R.id.spinner);
        tvMessage = findViewById(R.id.message);

        alTasks = new ArrayList<String>();

        aaTasks = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTask.setAdapter(aaTasks);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //code for item 1 selected
                        etElement.setHint("Type in a new task here");
                        btDelete.setEnabled(false);
                        btAdd.setEnabled(true);
                        break;
                    case 1:
                        //code for item 2 selected
                        etElement.setHint("Type in the index of the task to be removed");
                        btAdd.setEnabled(false);
                        btDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String colour = etElement.getText().toString();
                alTasks.add(colour);
                aaTasks.notifyDataSetChanged();
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTasks.size() == 0) {
                    String message = "You don't have any task to remove";
                    tvMessage.setText(message);
                }
                else if (Integer.parseInt(etElement.getText().toString()) >= alTasks.size()) {
                    String message = "Wrong index number";
                    tvMessage.setText(message);
                }
                else {
                    alTasks.remove(Integer.parseInt(etElement.getText().toString()));
                }
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });

    }
}