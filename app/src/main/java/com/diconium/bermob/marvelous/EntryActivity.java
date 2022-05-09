package com.diconium.bermob.marvelous;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

// TODO: https://github.com/diconium/bermob-android-marvelous/issues/4 architecture
// TODO: https://github.com/diconium/bermob-android-marvelous/issues/5 fragment
public class EntryActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: https://github.com/diconium/bermob-android-marvelous/issues/6 style
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
        // TODO: https://github.com/diconium/bermob-android-marvelous/issues/7 type safety
        Intent i = new Intent(this, MainActivity.class);
        i.setData(Uri.parse("marvelous://marvel?" + query));
        startActivity(i);
    }
}
