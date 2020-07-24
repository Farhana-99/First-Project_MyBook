package com.example.mybook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

public class Library extends AppCompatActivity {

    private static final int PICK_PDF_CODE = 1000;

    private Button btn_open_asset;
    private Button btn_open_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // Request Read & Write External storage
        Dexter.withActivity(this)
                .withPermission(permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE) // PERSONAL NOTE--> WRITE_EXTERNAL_STORAGE ta thik hoar kotha.
                .withListener(new BaseMultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }
                }).check();


        btn_open_asset = (Button)findViewById(R.id.btn_open_asset);
        btn_open_storage = (Button)findViewById(R.id.btn_open_storage);

        btn_open_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Library.this,ViewActivity.class);
                intent.putExtra("ViewType","asset");
                startActivity(intent);
            }
        });

        btn_open_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browsePDF = new Intent(Intent.ACTION_GET_CONTENT);
                browsePDF.setType("application/pdf");
                browsePDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(browsePDF, "Select PDF"),PICK_PDF_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_PDF_CODE && requestCode == RESULT_OK && data != null){
            Uri selectedPDF = data.getData();
            Intent intent = new Intent(Library.this,ViewActivity.class);
            intent.putExtra("ViewType","storage");
            intent.putExtra("FileUri", selectedPDF.toString());
            startActivity(intent);

    }
}
}
