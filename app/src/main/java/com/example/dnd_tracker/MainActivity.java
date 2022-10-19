package com.example.dnd_tracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import net.cachapa.expandablelayout.ExpandableLayout;

public final class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        ExpandableLayout spellSlotExpandable = findViewById(R.id.spell_slots_expandable_layout);
        findViewById(R.id.spell_slots_text).setOnClickListener(
                view -> {
                    if (spellSlotExpandable.isExpanded()) {
                        spellSlotExpandable.collapse();
                    } else {
                        spellSlotExpandable.expand();
                    }
                }
        );
    }
}