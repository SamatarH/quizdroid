package edu.uw.ischool.samatar.quizdroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, SettingsFragment())
            .commit()
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            findPreference<EditTextPreference>("questions_url")?.setOnPreferenceChangeListener { preference, newValue ->
                // Handle the new URL value here
                true
            }

            findPreference<EditTextPreference>("update_frequency")?.setOnPreferenceChangeListener { preference, newValue ->
                // Handle the new update frequency value here
                true
            }
        }
    }
}
