package com.PP.LunarTabsAndroid.Dialogs;

import java.io.File;

import android.app.Dialog;
import android.content.Context;

import com.PP.LunarTabsAndroid.APIs.TextToSpeechAPI;
import com.PP.LunarTabsAndroid.APIs.TuxGuitarUtil;
import com.PP.LunarTabsAndroid.Activities.MainActivity;
import com.PP.LunarTabsAndroid.UI.GUIDataModel;
import com.PP.LunarTabsAndroid.UI.SpeechConst;
import com.daidalos.afiledialog.FileChooserDialog;
import com.tuxguitar.song.models.TGSong;

public class GuitarFileLoaderDialog extends FileChooserDialog {

	public GuitarFileLoaderDialog(Context context, final MainActivity mainActivity) {
		super(context);
		setCanCreateFiles(false);
		setFilter(".*gp1|.*gp2|.*gp3|.*gp4|.*gp5|.*gpx|.*ptb");
	    addListener(new FileChooserDialog.OnFileSelectedListener() {
	         @Override
			public void onFileSelected(Dialog source, File file) {
	        	 
	        	 //finish dialog
	             source.dismiss();
	             	             
	             //attempt file load and populate tracks
	             try {
	            	 
		             //populate GUI with selection       
//		             fileField.setText(file.getName());
//	            	 fileField.setContentDescription(file.getName());
	            	 
	            	 //load song and store in gui data model
	            	 TGSong song = TuxGuitarUtil.loadSong(file.getPath());
//	            	 fileField.setText(song.getName());
//	            	 fileField.setContentDescription(song.getName());
	            	 GUIDataModel dataModel = GUIDataModel.getInstance();
	            	 dataModel.setFilePath(file.getPath());
		             dataModel.setFileName(song.getName());	            	 
	            	 if(song!=null) {
	            		 dataModel.setSong(song);
	            	 }
	            	 
	            	 //create tracks
	            	 mainActivity.createTrackOptions();
	            	 
	             	//set first segment selected and load instructions
	             	if(dataModel.getTracksList().size() >0) {
	             		
	             		//set params
	             		dataModel.setTrackNum(0);
	             		dataModel.setCurrentSegment(0);
	             		
		             	//enable instructions list
		             	mainActivity.getInstructionsList().setHilightEnabled(true);
	             		
		             	//perform load and show on GUI
	         			mainActivity.loadInstructions();				    	
	         			GUIDataModel.getInstance().clearSelectedInstructionIndex();
	         			mainActivity.getInstructionsList().refreshGUI();	         			
	             	}	            	 
	             		            	 
	            	 //notify user that track successfully loaded
	            	 TextToSpeechAPI.speak(SpeechConst.FILE_LOADED);
	             }
	             catch(Exception e) {
	            	 //say could not be loaded
	            	 TextToSpeechAPI.speak(SpeechConst.ERROR_FILE_NOT_LOADED);
	             }	             
	         }
	         @Override
			public void onFileSelected(Dialog source, File folder, String name) {}
	     });		    
		
	}

}