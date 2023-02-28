package com.example.aut2_03;

import android.Manifest;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;

public class MicrophoneFragment extends Fragment {

    private static int MICROPHONE_PERMISSION_CODE = 200;

    private MediaRecorder mediaRecorder;

    private boolean play = false;

    private ImageButton playButton;
    TextView contText;
    CounterTime counterTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.microfone_view,container,false);

        if(isMicrophonePresented())
            getMicrophonePermission();

        contText = view.findViewById(R.id.time_record);

        counterTime = new CounterTime(contText);

        playButton = view.findViewById(R.id.play_pauseButton);
        ImageButton stopButton = view.findViewById(R.id.stopButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onPlayAction();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStopAction();
            }
        });

        return view;
    }

    private void onPlayAction() throws Exception{
        if(!play){
            if(mediaRecorder == null){
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(getRecordingFilePath());
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.prepare();
                mediaRecorder.start();
                this.counterTime.startThread();
            }else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mediaRecorder.resume();
                }
            }

            playButton.setImageResource(R.drawable.pause_button);
            play = true;
            counterTime.setPause(false);
            Toast.makeText(MicrophoneFragment.this.getContext(), "Recording Start", Toast.LENGTH_SHORT).show();
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mediaRecorder.pause();
                counterTime.setPause(true);
                play = false;
                playButton.setImageResource(R.drawable.play_button);
                Toast.makeText(MicrophoneFragment.this.getContext(), "Recording Pause", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void onStopAction(){
        if(play){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            play = false;
            this.counterTime.stopThread();
            playButton.setImageResource(R.drawable.play_button);
            Toast.makeText(MicrophoneFragment.this.getContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();

        }
    }


    private boolean isMicrophonePresented(){
        if(this.getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        return false;
    }

    private void getMicrophonePermission(){
        if(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this.getActivity(),new String[] {Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE);
        }
    }

    private String getRecordingFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(this.getContext().getApplicationContext());
        File recordDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(recordDirectory,"testRecordingFile" + ".mp3");
        return file.getPath();
    }
}
