package com.flxrs.dankchat.preferences

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.flxrs.dankchat.BuildConfig
import com.flxrs.dankchat.MainActivity
import com.flxrs.dankchat.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        findPreference<Preference>(getString(R.string.preference_about_key))
            ?.summary = getString(R.string.preference_about_summary, BuildConfig.VERSION_NAME)

        val isLoggedIn = DankChatPreferenceStore(requireContext()).isLoggedin()
        findPreference<Preference>(getString(R.string.preference_logout_key))?.apply {
            isEnabled = isLoggedIn
            setOnPreferenceClickListener {
                Intent().apply {
                    putExtra(MainActivity.LOGOUT_REQUEST_KEY, true)
                    requireActivity().let {
                        it.setResult(Activity.RESULT_OK, this)
                        it.finish()
                    }
                }
                true
            }
        }
    }
}