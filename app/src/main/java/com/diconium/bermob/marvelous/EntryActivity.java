package com.diconium.bermob.marvelous;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

// TODO: Organize the packages according to app architecture
// TODO: Convert to Fragment (in Kotlin) and move to the MainActivity
public class EntryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Refactor to use ViewBinding
        setContentView(R.layout.activity_main);

        // TODO: style the SearchView icon and text
        SearchView search = findViewById(R.id.search);
        search.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        String q = query.trim();
        if (q.length() > 0) {
            onSearch(q);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // no-op
        return true;
    }

    private void onSearch(@NonNull String query) {
        // TODO: Fix to provide data safety
        Intent i = new Intent(this, MainActivity.class);
        i.setData(Uri.parse("marvelous://marvel?" + query));
        startActivity(i);
    }
}
