package com.PP.LunarTabsAndroid.Activities;

import com.PP.LunarTabsAndroid.Dialogs.SetHomeDirectoryDialog;
import com.example.lunartabsandroid.R;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;


public class GeneralSettingsPrefActivity extends PreferenceActivity {
	
	//on create
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
    	//theme
//    	this.setTheme(android.R.style.Theme_Black);        
    	
    	//create
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }
    
    //fragment for preferences
    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
        	
        	//create
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.general_settings);
            
            //set up tab home directorty preference
            final Preference dialogPreference = (Preference) getPreferenceScreen().findPreference("set_tab_home_dir_pref");
            if(dialogPreference!=null) {
	            dialogPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	                public boolean onPreferenceClick(Preference preference) {
	            		SetHomeDirectoryDialog dialog = new SetHomeDirectoryDialog(dialogPreference.getContext());
	            		dialog.show();                	
	                    return true;
	                }
	            });            
            }
        }
    }
}
