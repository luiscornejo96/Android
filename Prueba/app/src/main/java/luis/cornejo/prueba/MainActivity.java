package luis.cornejo.prueba;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button noti;
    private int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noti = findViewById(R.id.Btn_Notificacion);
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                Log.i("SDK:", " " + Build.VERSION.SDK_INT);
                NotificationCompat.Builder mBuilder =  new NotificationCompat.Builder(MainActivity.this, null);
                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelId = "Channel_Id";
                    CharSequence name = "Ofertas";
                    String description = "Hola mundo on Android";
                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel notificationChannel =  new NotificationChannel(channelId, name, importance);
                    notificationChannel.setDescription(description);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.GREEN);
                    notificationChannel.enableVibration(true);
                    notificationChannel.setVibrationPattern(new long[] {100, 200, 300, 400, 500, 400, 300, 200, 400});

                    notificationManager.createNotificationChannel(notificationChannel);

                    mBuilder = new NotificationCompat.Builder(MainActivity.this, channelId);
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pendingIntent)
                            .setContentTitle("Modificación de Embarque")
                            .setContentText("La modificación de X, fue exitosa.")
                            .setAutoCancel(true);

                    notificationManager.notify( cont++, mBuilder.build());


                }
                else
                {
                    mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pendingIntent)
                            .setContentTitle("Modificación de Embarque")
                            .setContentText("La modificación de X, fue exitosa.")
                            .setAutoCancel(true);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                    notificationManagerCompat.notify(cont++, mBuilder.build());
                }
            }
        });
    }
}
