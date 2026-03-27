package com.example.iamnoob;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// ContextMenuActivity: demonstrates floating context menu, contextual action mode, and long-press popup
public class ContextMenuActivity extends AppCompatActivity {

    TextView contextText;
    ListView listView;
    Button longPressBtn;

    ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        // Floating context menu: register a view for context menu
        contextText = findViewById(R.id.contextText);
        registerForContextMenu(contextText);

        // ListView used for Contextual Action Mode (CAB)
        listView = findViewById(R.id.listView);
        String[] items = new String[]{"Item 1","Item 2","Item 3","Item 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Long click on list item starts contextual action mode
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) return false;
                actionMode = startActionMode(actionModeCallback);
                view.setSelected(true);
                return true;
            }
        });

        // Long press triggering menu using PopupMenu on a button
        longPressBtn = findViewById(R.id.longPressBtn);
        longPressBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(ContextMenuActivity.this, v);
                popup.getMenu().add(Menu.NONE, 1, 1, "Option A");
                popup.getMenu().add(Menu.NONE, 2, 2, "Option B");
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ContextMenuActivity.this, "Popup long-press: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
                return true; // consumed
            }
        });
    }

    // Floating context menu creation for registered view
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    // Handle floating context menu item clicks
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Use if/else because resource IDs may not be compile-time constants
        if (id == R.id.ctx_action1) {
            Toast.makeText(this, "Floating Context: Action 1", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.ctx_action2) {
            Toast.makeText(this, "Floating Context: Action 2", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    // Contextual Action Mode callback: shows top action bar menu
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_context, menu);
            mode.setTitle("Select");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.ctx_action1) {
                Toast.makeText(ContextMenuActivity.this, "CAB: Action 1", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            } else if (id == R.id.ctx_action2) {
                Toast.makeText(ContextMenuActivity.this, "CAB: Action 2", Toast.LENGTH_SHORT).show();
                mode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
