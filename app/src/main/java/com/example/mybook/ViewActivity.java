package com.example.mybook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class ViewActivity extends AppCompatActivity {

    public PDFView pdfView;
    public float zoomValue=1;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        pdfView = (PDFView)findViewById(R.id.pdf_viewer);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);


        if (getIntent() != null)
        {

            String viewType = getIntent().getStringExtra("viewType");
            if (viewType != null || !TextUtils.isEmpty(viewType))
            {
                if (viewType.equals("assets"))
                {
                    pdfView.fromAsset ("Exercise_Realtime_Classification.pdf")
                            .password(null) // if have password
                    .defaultPage(0) // open default page, one can remember this value to open from last time
                    .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true) // double tap to zoom
                    .onDraw(new OnDrawListener() {
                        @Override
                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                        }
                    })
                            .onDrawAll(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                }
                            })
                            .onPageError(new OnPageErrorListener() {
                                @Override
                                public void onPageError(int page, Throwable t) {
                                    Toast.makeText(ViewActivity.this, "Error while open page"+page, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {

                                }
                            })
                            .onTap(new OnTapListener() {
                                @Override
                                public boolean onTap(MotionEvent e) {
                                    return true;
                                }
                            }).onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages) {
                            pdfView.fitToWidth(0);
                        }
                    })
                            .enableAnnotationRendering(true)
                            .load();

                }
                else if (viewType.equals("storage")){
                    Uri pdfFile = Uri.parse(getIntent().getStringExtra("FileUri"));
                    pdfView.fromUri (pdfFile)
                            .password(null) // if have password
                            .defaultPage(0) // open default page, one can remember this value to open from last time
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true) // double tap to zoom
                            .onDraw(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                }
                            })
                            .onDrawAll(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                }
                            })
                            .onPageError(new OnPageErrorListener() {
                                @Override
                                public void onPageError(int page, Throwable t) {
                                    Toast.makeText(ViewActivity.this, "Error while open page"+page, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {

                                }
                            })
                            .onTap(new OnTapListener() {
                                @Override
                                public boolean onTap(MotionEvent e) {
                                    return true;
                                }
                            }).onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages) {
                            pdfView.fitToWidth(0);
                        }
                    })
                            .enableAnnotationRendering(true)
                            .load();


                }

                else if (viewType.equals("internet")){
                    progressBar.setVisibility(View.VISIBLE); // to show progress bar
                    FileLoader.with(this)
                            .load("url")
                            .fromDirectory("PDFFiles", FileLoader.DIR_EXTERNAL_PUBLIC)
                            .asFile(new FileRequestListener<File>() {
                                @Override
                                public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                                    progressBar.setVisibility(View.GONE); // Hide Progress bar

                                    File pdfFile = response.getBody();

                                    pdfView.fromFile (pdfFile)
                                            .password(null) // if have password
                                            .defaultPage(0) // open default page, one can remember this value to open from last time
                                            .enableSwipe(true)
                                            .swipeHorizontal(false)
                                            .enableDoubletap(true) // double tap to zoom
                                            .onDraw(new OnDrawListener() {
                                                @Override
                                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                                }
                                            })
                                            .onDrawAll(new OnDrawListener() {
                                                @Override
                                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                                }
                                            })
                                            .onPageError(new OnPageErrorListener() {
                                                @Override
                                                public void onPageError(int page, Throwable t) {
                                                    Toast.makeText(ViewActivity.this, "Error while open page"+page, Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .onPageChange(new OnPageChangeListener() {
                                                @Override
                                                public void onPageChanged(int page, int pageCount) {

                                                }
                                            })
                                            .onTap(new OnTapListener() {
                                                @Override
                                                public boolean onTap(MotionEvent e) {
                                                    return true;
                                                }
                                            }).onRender(new OnRenderListener() {
                                        @Override
                                        public void onInitiallyRendered(int nbPages) {
                                            pdfView.fitToWidth(0);
                                        }
                                    })
                                            .enableAnnotationRendering(true)
                                            .load();


                                }

                                @Override
                                public void onError(FileLoadRequest request, Throwable t) {
                                    Toast.makeText(ViewActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE); // Hide Progress bar

                                }
                            });

                }
            }
        }

        }

        public void nextPage (View view){
        pdfView.jumpTo(pdfView.getCurrentPage()+1, true);
        }

        public void prevPage (View view){
        pdfView.jumpTo(pdfView.getCurrentPage()-1,true);
        }

        public void zoomIn (View view){
        ++zoomValue;
        pdfView.zoomTo(zoomValue);
        pdfView.loadPages();
        }

        public void zoomOut (View view){
         if (zoomValue!=1){
             --zoomValue;
             pdfView.zoomTo(zoomValue);
             pdfView.loadPages();
         }
        }
    }

