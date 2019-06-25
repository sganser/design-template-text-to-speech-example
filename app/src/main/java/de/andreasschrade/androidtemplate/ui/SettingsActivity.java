package de.andreasschrade.androidtemplate.ui;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;
import de.andreasschrade.androidtemplate.util.TTSStarter;

/**
 * This Activity provides several settings. Activity contains {@link PreferenceFragment} as inner class.
 * <p>
 * Created by Andreas Schrade on 14.12.2015.
 */
public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupToolbar();
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
        return R.id.nav_settings;
    }

    @Override
    public boolean providesActivityToolbar() {
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }

    public static class SettingsFragment extends PreferenceFragment implements AdapterView.OnItemLongClickListener {

        public SettingsFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_prefs);
        }

        //https://stackoverflow.com/questions/29115216/how-to-make-a-preference-long-clickable-in-preferencefragment
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View result = super.onCreateView(inflater, container, savedInstanceState);

            if (result != null) {
                View lv = result.findViewById (android.R.id.list);
                if (lv instanceof ListView) {
                    ((ListView) lv).setOnItemLongClickListener(this);
                }
            }
            return result;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Object o = adapterView.getItemAtPosition(i);
            if(o instanceof CheckBoxPreference) {
                CheckBoxPreference cbp = (CheckBoxPreference) o;
                String text = cbp.getTitle().toString()+"<break />"+cbp.getSummary().toString();
                TTSStarter.startTTS(getActivity(), text);
            }
            return true;
        }
    }
}
