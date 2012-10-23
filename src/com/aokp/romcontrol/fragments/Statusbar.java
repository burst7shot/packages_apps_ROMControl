
package com.aokp.romcontrol.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;

import com.aokp.romcontrol.AOKPPreferenceFragment;
import com.aokp.romcontrol.R;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

public class StatusBar extends AOKPPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String STATUS_BAR_COLOR = "status_bar_color";

    ColorPickerPreference mStatusBarColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_statusbar);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_statusbar);

        mStatusBarColor = (ColorPickerPreference) prefSet.findPreference(STATUS_BAR_COLOR);
        mStatusBarColor.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean result = false;

        if (preference == mStatusBarColor) {
            String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                    .valueOf(newValue)));
            preference.setSummary(hex);

            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_COLOR, intHex);
            Log.e("ROMAN", intHex + "");
        }
        return result;
    }
}
