package com.rujirakongsomran.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rujirakongsomran.calendar.databinding.ActivityMainBinding;

import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialize description hash map
        HashMap<Object, Property> descHashMap = new HashMap<>();

        // Initialize default property
        Property defaultProperty = new Property();

        // Initialize default resource
        defaultProperty.layoutResource = R.layout.default_view;

        // Initialize and assign variable
        defaultProperty.dateTextViewResource = R.id.tvDate;

        // Put object and property
        descHashMap.put("default", defaultProperty);

        // For current date
        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.current_view;
        currentProperty.dateTextViewResource = R.id.tvDate;
        descHashMap.put("current", currentProperty);

        // For present date
        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.present_view;
        presentProperty.dateTextViewResource = R.id.tvDate;
        descHashMap.put("present", presentProperty);

        // For absent
        Property absentProperty = new Property();
        absentProperty.layoutResource = R.layout.absent_view;
        absentProperty.dateTextViewResource = R.id.tvDate;
        descHashMap.put("absent", absentProperty);

        // Set desc hash map on custom calendar
        binding.customCalendar.setMapDescToProp(descHashMap);

        // Initialize date hash map
        HashMap<Integer, Object> dateHashMap = new HashMap<>();

        // Initialize calendar
        Calendar calendar = Calendar.getInstance();

        // Put value
        dateHashMap.put(calendar.get(Calendar.DAY_OF_MONTH), "current");
        dateHashMap.put(1, "present");
        dateHashMap.put(2, "absent");
        dateHashMap.put(3, "present");
        dateHashMap.put(4, "absent");
        dateHashMap.put(20, "present");
        dateHashMap.put(30, "absent");

        // Set date
        binding.customCalendar.setDate(calendar, dateHashMap);

        binding.customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                // Get string date
                String strDate = selectedDate.get(Calendar.DAY_OF_MONTH)
                        + "/" + (selectedDate.get(Calendar.MONTH) + 1)
                        + "/" + selectedDate.get(Calendar.YEAR);

                // Display date
                Toast.makeText(getApplicationContext(), strDate, Toast.LENGTH_SHORT).show();
            }
        });
    }
}