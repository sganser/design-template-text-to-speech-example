package de.andreasschrade.androidtemplate.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;
import de.andreasschrade.androidtemplate.util.TTSStarter;

/**
 * Activity demonstrates some GUI functionalities from the Android support library.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ViewSamplesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samples);
        ButterKnife.bind(this);
        setupToolbar();
    }

    //TODO add long click listener
    @OnLongClick(R.id.sample_text_email)
    public boolean onEmailLongClick() {
        String text = getString(R.string.samples_hint_email);
        TTSStarter.startTTS(ViewSamplesActivity.this, text);
        return true;
    }

    @OnLongClick(R.id.sample_text_username)
    public boolean onUserNameLongClick() {
        String text = getString(R.string.samples_hint_username);
        TTSStarter.startTTS(ViewSamplesActivity.this, text);
        return true;
    }

    @OnLongClick(R.id.sample_title_subhead_layout)
    public boolean onSampleTitleLongClick() {
        String text = getString(R.string.samples_title) + " <break/> " +  getString(R.string.samples_subhead);
        TTSStarter.startTTS(ViewSamplesActivity.this, text);
        return true;
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View view) {
        Snackbar.make(view, "Hello Snackbar!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        //TODO TTS
        TTSStarter.startTTS(ViewSamplesActivity.this, "Hello Snackbar!");
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getSelfNavDrawerItem() {
        return R.id.nav_samples;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }
}
