package com.example.stickynotes;

import static android.app.PendingIntent.FLAG_MUTABLE;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.example.stickynotes.R.id.textView1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId : appWidgetIds){
            Intent launchIntent = new Intent(context,MainActivity.class);
            PendingIntent PendingIntent = android.app.PendingIntent.getActivity(context,1,launchIntent,FLAG_MUTABLE);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.id.widget_layout1);
            remoteViews.setOnClickPendingIntent(textView1,PendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
        }
    }
}
